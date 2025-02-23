package per.stu.pms.admin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import per.stu.pms.admin.model.vo.task.AddTaskRequestVO;
import per.stu.pms.admin.model.vo.task.DeleteTaskReqVO;
import per.stu.pms.admin.model.vo.task.FindTaskPageListReqVO;
import per.stu.pms.admin.model.vo.task.FindTaskReqVO;
import per.stu.pms.admin.service.AdminTaskService;
import per.stu.pms.common.aspect.ApiOperationLog;
import per.stu.pms.common.utils.PageResponse;
import per.stu.pms.common.utils.Response;

@RestController
@RequestMapping("/interface/admin/task")
@Api(tags = "Admin 任务模块")
public class AdminTaskController {

    @Autowired
    private AdminTaskService taskService;

    @PostMapping("/add")
    @ApiOperation(value = "添加任务")
    @ApiOperationLog(description = "添加任务")
    public Response addTask(@RequestBody @Validated AddTaskRequestVO addTaskRequestVO) {
        return taskService.addTask(addTaskRequestVO);
    }

    @PostMapping("/list")
    @ApiOperation(value = "任务分页数据获取")
    @ApiOperationLog(description = "任务分页数据获取")
    public PageResponse findTaskList(@RequestBody @Validated FindTaskPageListReqVO findTaskPageListReqVO) {
        return taskService.findTaskList(findTaskPageListReqVO);
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除任务")
    @ApiOperationLog(description = "删除任务")
    public Response deleteTask(@RequestBody @Validated DeleteTaskReqVO deleteTaskReqVO) {
        return taskService.deleteTask(deleteTaskReqVO);
    }

    @PostMapping("/search")
    @ApiOperation(value = "任务搜索")
    @ApiOperationLog(description = "任务搜索")
    public Response searchTaskList(@RequestBody @Validated FindTaskReqVO findTaskReqVO) {
        return taskService.searchTaskList(findTaskReqVO);
    }
}