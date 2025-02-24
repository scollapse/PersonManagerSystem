package per.stu.pms.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import per.stu.pms.admin.convert.ProjectConvert;
import per.stu.pms.admin.model.vo.project.*;
import per.stu.pms.admin.service.AdminProjectService;
import per.stu.pms.common.domain.dos.ProjectDO;
import per.stu.pms.common.domain.mapper.ProjectMapper;
import per.stu.pms.common.enums.ResponseCodeEnum;
import per.stu.pms.common.excption.BizException;
import per.stu.pms.common.utils.PageResponse;
import per.stu.pms.common.utils.Response;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class AdminProjectServiceImpl implements AdminProjectService {

    @Autowired
    private ProjectMapper projectMapper;

    @Override
    public Response addProject(AddProjectRequestVO addProjectRequestVO) {
        // 检查项目名称是否存在
        ProjectDO existProject = projectMapper.selectByName(addProjectRequestVO.getProjectName());
        if (Objects.nonNull(existProject)) {
            log.warn("项目名称已存在：{}", addProjectRequestVO.getProjectName());
            throw new BizException(ResponseCodeEnum.PROJECT_NAME_EXIST);
        }
        // 转换VO到DO并插入
        ProjectDO projectDO = ProjectConvert.INSTANCE.convertVOToDO(addProjectRequestVO);
        int insert = projectMapper.insert(projectDO);
        if (insert == 0) {
            log.error("项目新增失败：{}", addProjectRequestVO.getProjectName());
            throw new BizException(ResponseCodeEnum.PROJECT_ADD_ERROR);
        }
        return Response.success();
    }

    @Override
    public PageResponse findProjectList(FindProjectPageListReqVO reqVO) {
        // 构建分页查询
        Page<ProjectDO> page = new Page<>(reqVO.getCurrent(), reqVO.getSize());
        Page<ProjectDO> projectPage = projectMapper.findProjectList(page);
        // 转换结果
        List<FindProjectPageListResVO> vos = ProjectConvert.INSTANCE.convertDOToVOList(projectPage.getRecords());
        return PageResponse.success(projectPage, vos);
    }

    @Override
    public Response deleteProject(DeleteProjectReqVO deleteReqVO) {
        // 1. 检查项目是否存在
        ProjectDO project = projectMapper.selectById(deleteReqVO.getProjectId());
        if (project == null) {
            log.warn("删除失败，项目不存在：ID={}", deleteReqVO.getProjectId());
            throw new BizException(ResponseCodeEnum.PROJECT_NOT_EXIST);
        }
        // 2. 执行删除
        int deleteCount = projectMapper.deleteById(deleteReqVO.getProjectId());
        if (deleteCount == 0) {
            log.error("项目删除失败：ID={}", deleteReqVO.getProjectId());
            throw new BizException(ResponseCodeEnum.PROJECT_DELETE_ERROR);
        }
        return Response.success();
    }

    @Override
    public Response searchProjectList(FindProjectReqVO findProjectReqVO) {
        return null;
    }

//    @Override
//    public Response searchProjectList(FindProjectReqVO searchReqVO) {
//        // 构建动态查询条件
//        QueryWrapper<ProjectDO> queryWrapper = new QueryWrapper<>();
//        if (searchReqVO.getProjectName() != null) {
//            queryWrapper.like("project_name", searchReqVO.getProjectName());
//        }
//        if (searchReqVO.getStatus() != null) {
//            queryWrapper.eq("status", searchReqVO.getStatus());
//        }
//        // 执行查询
//        List<ProjectDO> projects = projectMapper.selectList(queryWrapper);
//        // 转换结果
//        List<FindProjectPageListResVO> vos = ProjectConvert.INSTANCE.convertDOToVOList(projects);
//        return Response.success(vos);
//    }

    @Override
    public Response updateProject(AddProjectRequestVO updateReqVO) {
        // 1. 检查项目是否存在
        ProjectDO existProject = projectMapper.selectById(updateReqVO.getProjectId());
        if (existProject == null) {
            log.warn("更新失败，项目不存在：ID={}", updateReqVO.getProjectId());
            throw new BizException(ResponseCodeEnum.PROJECT_NOT_EXIST);
        }
        // 2. 检查名称是否重复（如果名称有修改）
        if (!existProject.getProjectName().equals(updateReqVO.getProjectName())) {
            ProjectDO sameNameProject = projectMapper.selectByName(updateReqVO.getProjectName());
            if (sameNameProject != null) {
                log.warn("项目名称重复：{}", updateReqVO.getProjectName());
                throw new BizException(ResponseCodeEnum.PROJECT_NAME_EXIST);
            }
        }
        // 3. 更新数据
        ProjectDO updateDO = ProjectConvert.INSTANCE.updateVOToDO(updateReqVO);
        int updateCount = projectMapper.updateById(updateDO);
        if (updateCount == 0) {
            log.error("项目更新失败：ID={}", updateReqVO.getProjectId());
            throw new BizException(ResponseCodeEnum.PROJECT_UPDATE_ERROR);
        }
        return Response.success();
    }
}