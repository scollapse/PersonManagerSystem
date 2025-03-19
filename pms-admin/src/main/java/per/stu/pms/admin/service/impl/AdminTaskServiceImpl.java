package per.stu.pms.admin.service.impl;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import per.stu.pms.admin.convert.TaskConvert;
import per.stu.pms.admin.model.vo.tag.AddTagReqVO;
import per.stu.pms.admin.model.vo.tag.UpdateTagReqVO;
import per.stu.pms.admin.model.vo.task.*;
import per.stu.pms.admin.service.AdminTagService;
import per.stu.pms.admin.service.AdminTaskService;
import per.stu.pms.admin.service.AdminTaskTagService;
import per.stu.pms.common.domain.dos.TaskDO;
import per.stu.pms.common.domain.dtos.task.StaticTaskDTO;
import per.stu.pms.common.domain.dtos.task.TaskDTO;
import per.stu.pms.common.domain.dtos.task.TaskQuery;
import per.stu.pms.common.domain.mapper.TaskMapper;
import per.stu.pms.common.enums.ResponseCodeEnum;
import per.stu.pms.common.enums.TaskStatus;
import per.stu.pms.common.excption.BizException;
import per.stu.pms.common.utils.PageResponse;
import per.stu.pms.common.utils.Response;

import java.util.*;

/**
 * @description: 任务管理
 * @author: syl
 * @create: 2025-02-21 15:36
 **/
@Service
@Slf4j
public class AdminTaskServiceImpl extends ServiceImpl<TaskMapper, TaskDO> implements AdminTaskService {

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private AdminTagService tagService;

    @Autowired
    private AdminTaskTagService taskTagService;

    @Override
    @Transactional(rollbackFor = Exception.class) // 事务提升到 Service 层
    public Response addTaskAndReturnId(AddTaskRequestVO addTaskRequestVO) {
        // 1. 检查任务名称是否已存在
        TaskDO taskDO = taskMapper.isExistByName(addTaskRequestVO.getTaskName());
        if (Objects.nonNull(taskDO)) {
            log.warn("任务名称已存在：{}", addTaskRequestVO.getTaskName());
            throw new BizException(ResponseCodeEnum.TASK_NAME_EXIST);
        }

        // 2. 插入任务
        TaskDO insertTaskDO = TaskConvert.INSTANCE.convertVOToDO(addTaskRequestVO);
        // 项目ID为-1时，设置为null
        if (insertTaskDO.getProjectId().equals("-1")) {
            insertTaskDO.setProjectId(null);
        }
        insertTaskDO.setStatus(TaskStatus.todo);
        int insert = taskMapper.insert(insertTaskDO);
        if (insert == 0) {
            log.error("新增任务失败：{}", addTaskRequestVO.getTaskName());
            throw new BizException(ResponseCodeEnum.TASK_ADD_ERROR);
        }

        // 3. 处理标签
        List<UpdateTagReqVO> tags = addTaskRequestVO.getTags();
        if (!CollectionUtils.isEmpty(tags)) {
            List<Long> tagIds = processTagsAndReturnIds(tags);
            // 4. 插入任务-标签关联
            taskTagService.saveTaskTag(insertTaskDO.getTaskId(), tagIds);
        }
        return Response.success();
    }

    /**
     * 处理标签并返回标签ID列表
     */
    private List<Long> processTagsAndReturnIds(List<UpdateTagReqVO> tags) {
        List<Long> tagIds = new ArrayList<>();
        List<String> tagNames = new ArrayList<>();

        // 遍历标签，区分有ID和无ID的标签
        tags.forEach(tag -> {
            if (tag.getId() == null) {
                tagNames.add(tag.getName());
            } else {
                tagIds.add(tag.getId());
            }
        });

        // 插入新标签并获取ID
        if (!CollectionUtils.isEmpty(tagNames)) {
            AddTagReqVO addTagReqVO = new AddTagReqVO();
            addTagReqVO.setTagNames(tagNames);
            List<Long> newTagIds = tagService.saveBatchAndReturnIds(addTagReqVO);
            tagIds.addAll(newTagIds);
        }

        return tagIds;
    }


    @Override
    public PageResponse findTaskList(FindTaskPageListReqVO findTaskPageListReqVO) {
        // FindTaskPageListReqVO ---> TaskQuery
        TaskQuery taskQuery = TaskConvert.INSTANCE.convertVOToQuery(findTaskPageListReqVO);
        // 获取分页参数
        Page<TaskQuery> page = new Page<>(taskQuery.getCurrent(), taskQuery.getSize());
        Page<TaskDTO> taskDOPage = taskMapper.findTaskList(page,taskQuery);
        List<TaskDTO> records = taskDOPage.getRecords();
        //DO转VO
        List<FindTaskPageListResVO> vos = TaskConvert.INSTANCE.convertDTOToVOList(records);
        return PageResponse.success(taskDOPage,vos);
    }

    @Override
    public Response deleteTask(DeleteTaskReqVO deleteReqVO) {
        // 1. 直接通过 MyBatis-Plus 实现逻辑删除
        int deleteCount = taskMapper.deleteById(deleteReqVO.getTaskId());

        // 2. 检查删除结果（MP 会自动处理 is_deleted 字段）
        if (deleteCount == 0) {
            log.warn("删除失败，任务不存在或已被删除：ID={}", deleteReqVO.getTaskId());
            throw new BizException(ResponseCodeEnum.TASK_NOT_EXIST);
        }

        return Response.success();
    }

    @Override
    public Response searchTaskList(FindTaskReqVO findTaskReqVO) {
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class) // 添加事务管理
    public Response updateTask(UpdateTaskRequestVO updateReqVO) {
        // 1. 检查任务是否存在
        TaskDO existTask = taskMapper.selectById(updateReqVO.getTaskId());
        if (existTask == null) {
            log.warn("更新失败，任务不存在：ID={}", updateReqVO.getTaskId());
            throw new BizException(ResponseCodeEnum.TASK_NOT_EXIST);
        }

        // 2. 检查名称是否重复（如果名称有修改）
        if (!existTask.getTaskName().equals(updateReqVO.getTaskName())) {
            TaskDO sameNameTask = taskMapper.isExistByName(updateReqVO.getTaskName());
            if (sameNameTask != null && !sameNameTask.getTaskId().equals(updateReqVO.getTaskId())) {
                log.warn("任务名称重复：{}", updateReqVO.getTaskName());
                throw new BizException(ResponseCodeEnum.TASK_NAME_EXIST);
            }
        }

        // 3. 合并变更到现有对象
        TaskConvert.INSTANCE.updateVOToDO(updateReqVO, existTask);

        // 4. 处理完成时间（当状态变更为完成时）
        if (TaskStatus.completed.equals(existTask.getStatus())) {
            existTask.setCompletionTime(new Date());
        }

        // 5. 强制设置更新时间
        existTask.setUpdateTime(new Date());

        // 6. 执行任务更新
        int updateCount = taskMapper.updateById(existTask);
        if (updateCount == 0) {
            log.error("任务更新失败：ID={}", updateReqVO.getTaskId());
            throw new BizException(ResponseCodeEnum.TASK_UPDATE_ERROR);
        }

        // 7. 处理任务-标签关联
        List<UpdateTagReqVO> tags = updateReqVO.getTags();
        if (!CollectionUtils.isEmpty(tags)) { // 如果传入了标签信息，则更新任务-标签关联
            List<Long> tagIds = processTagsAndReturnIds(tags); // 处理标签并返回标签ID列表
            taskTagService.saveTaskTag(updateReqVO.getTaskId(), tagIds); // 更新任务-标签关联
        }

        log.info("任务更新成功：ID={}", updateReqVO.getTaskId());
        return Response.success();
    }


    @Override
    public Response staticTask() {
        StaticTaskDTO staticTaskDTO = taskMapper.selectTaskStatistics();
        StaticTaskVO staticTaskVO = TaskConvert.INSTANCE.convertDTOToStatisticVO(staticTaskDTO);
        return Response.success(staticTaskVO);
    }
}
