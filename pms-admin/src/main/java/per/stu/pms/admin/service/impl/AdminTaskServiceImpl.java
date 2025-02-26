package per.stu.pms.admin.service.impl;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import per.stu.pms.admin.convert.TaskConvert;
import per.stu.pms.admin.model.vo.task.*;
import per.stu.pms.admin.service.AdminTaskService;
import per.stu.pms.common.domain.dos.TaskDO;
import per.stu.pms.common.domain.dtos.task.TaskDTO;
import per.stu.pms.common.domain.mapper.TaskMapper;
import per.stu.pms.common.enums.ResponseCodeEnum;
import per.stu.pms.common.enums.TaskStatus;
import per.stu.pms.common.excption.BizException;
import per.stu.pms.common.utils.PageResponse;
import per.stu.pms.common.utils.Response;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @description: 任务管理
 * @author: syl
 * @create: 2025-02-21 15:36
 **/
@Service
@Slf4j
public class AdminTaskServiceImpl implements AdminTaskService {

    @Autowired
    private TaskMapper taskMapper;


    @Override
    public Response addTask(AddTaskRequestVO addTaskRequestVO) {
        //从库中查询任务名称是否存在，如果存在，则返回错误信息
       TaskDO taskDO = taskMapper.isExistByName(addTaskRequestVO.getTaskName());
        if (Objects.nonNull(taskDO)){
            log.warn("任务名称已存在：{}", addTaskRequestVO.getTaskName());
            throw new BizException(ResponseCodeEnum.TASK_NAME_EXIST);
        }
        //如果不存在，则插入新任务
        //构建DO
        TaskDO insertTaskDO = TaskConvert.INSTANCE.convertVOToDO(addTaskRequestVO);
        //设置默认状态
        insertTaskDO.setStatus(TaskStatus.todo);
        int insert = taskMapper.insert(insertTaskDO);
        if (insert == 0){
            log.error("新增任务失败：{}", addTaskRequestVO.getTaskName());
            throw new BizException(ResponseCodeEnum.TASK_ADD_ERROR);
        }
        return Response.success();
    }


    @Override
    public PageResponse findTaskList(FindTaskPageListReqVO findTaskPageListReqVO) {
        // 获取分页参数
        Page<TaskDTO> page = new Page<>(findTaskPageListReqVO.getCurrent(), findTaskPageListReqVO.getSize());
        Page<TaskDTO> taskDOPage = taskMapper.findTaskList(page);
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

        // 6. 执行更新
        int updateCount = taskMapper.updateById(existTask);
        if (updateCount == 0) {
            log.error("任务更新失败：ID={}", updateReqVO.getTaskId());
            throw new BizException(ResponseCodeEnum.TASK_UPDATE_ERROR);
        }

        return Response.success();
    }
}
