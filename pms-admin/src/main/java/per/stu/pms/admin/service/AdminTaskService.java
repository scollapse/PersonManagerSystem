package per.stu.pms.admin.service;

import per.stu.pms.admin.model.vo.task.*;
import per.stu.pms.common.utils.PageResponse;
import per.stu.pms.common.utils.Response;

public interface AdminTaskService {
    Response addTaskAndReturnId(AddTaskRequestVO addTaskRequestVO);

    PageResponse findTaskList(FindTaskPageListReqVO findTaskPageListReqVO);

    Response deleteTask(DeleteTaskReqVO deleteTaskReqVO);

    Response searchTaskList(FindTaskReqVO findTaskReqVO);

    Response updateTask(UpdateTaskRequestVO addTaskRequestVO);

    Response staticTask();
}
