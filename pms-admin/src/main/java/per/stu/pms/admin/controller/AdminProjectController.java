package per.stu.pms.admin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import per.stu.pms.admin.model.vo.project.*;
import per.stu.pms.admin.service.AdminProjectService;
import per.stu.pms.common.aspect.ApiOperationLog;
import per.stu.pms.common.utils.PageResponse;
import per.stu.pms.common.utils.Response;

@RestController
@RequestMapping("/interface/admin/project")
@Api(tags = "Admin 项目模块")
public class AdminProjectController {

    @Autowired
    private AdminProjectService projectService;

    @PostMapping("/add")
    @ApiOperation(value = "添加项目")
    @ApiOperationLog(description = "添加项目")
    public Response addProject(@RequestBody @Validated AddProjectRequestVO addProjectRequestVO) {
        return projectService.addProject(addProjectRequestVO);
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改项目")
    @ApiOperationLog(description = "修改项目")
    public Response updateProject(@RequestBody @Validated UpdateProjectRequestVO updateProjectRequestVO) {
        return projectService.updateProject(updateProjectRequestVO);
    }

    @PostMapping("/list")
    @ApiOperation(value = "项目分页数据获取")
    @ApiOperationLog(description = "项目分页数据获取")
    public PageResponse findProjectList(@RequestBody @Validated FindProjectPageListReqVO findProjectPageListReqVO) {
        return projectService.findProjectList(findProjectPageListReqVO);
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除项目")
    @ApiOperationLog(description = "删除项目")
    public Response deleteProject(@RequestBody @Validated DeleteProjectReqVO deleteProjectReqVO) {
        return projectService.deleteProject(deleteProjectReqVO);
    }

    @PostMapping("/search")
    @ApiOperation(value = "项目搜索")
    @ApiOperationLog(description = "项目搜索")
    public Response searchProjectList(@RequestBody @Validated FindProjectReqVO findProjectReqVO) {
        return projectService.searchProjectList(findProjectReqVO);
    }

}
