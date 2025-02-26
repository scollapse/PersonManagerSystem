package per.stu.pms.admin.service;

import per.stu.pms.admin.model.vo.project.*;
import per.stu.pms.common.utils.PageResponse;
import per.stu.pms.common.utils.Response;

public interface AdminProjectService {
    Response addProject(AddProjectRequestVO addProjectRequestVO);

    PageResponse findProjectList(FindProjectPageListReqVO findProjectPageListReqVO);

    Response deleteProject(DeleteProjectReqVO deleteProjectReqVO);

    Response searchProjectList(FindProjectReqVO findProjectReqVO);

    Response updateProject(UpdateProjectRequestVO addProjectRequestVO);
}
