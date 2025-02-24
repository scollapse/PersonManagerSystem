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
import per.stu.pms.common.excption.BizException;
import per.stu.pms.common.utils.PageResponse;
import per.stu.pms.common.utils.Response;

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
       TaskDO taskDO = taskMapper.selectByName(addTaskRequestVO.getTaskName());
        if (Objects.nonNull(taskDO)){
            log.warn("任务名称已存在：{}", addTaskRequestVO.getTaskName());
            throw new BizException(ResponseCodeEnum.TASK_NAME_EXIST);
        }
        //如果不存在，则插入新任务
        //构建DO
        TaskDO insertTaskDO = TaskConvert.INSTANCE.convertVOToDO(addTaskRequestVO);
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
    public Response deleteTask(DeleteTaskReqVO deleteTaskReqVO) {
        return null;
    }

    @Override
    public Response searchTaskList(FindTaskReqVO findTaskReqVO) {
        return null;
    }
}
