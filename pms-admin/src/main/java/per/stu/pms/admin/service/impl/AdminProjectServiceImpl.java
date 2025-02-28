package per.stu.pms.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import per.stu.pms.admin.convert.ProjectConvert;
import per.stu.pms.admin.model.vo.project.*;
import per.stu.pms.admin.service.AdminProjectService;
import per.stu.pms.common.domain.dos.ProjectDO;
import per.stu.pms.common.domain.mapper.ProjectMapper;
import per.stu.pms.common.enums.ProjectStatus;
import per.stu.pms.common.enums.ResponseCodeEnum;
import per.stu.pms.common.excption.BizException;
import per.stu.pms.common.utils.PageResponse;
import per.stu.pms.common.utils.Response;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class AdminProjectServiceImpl extends ServiceImpl<ProjectMapper, ProjectDO> implements AdminProjectService {

    @Autowired
    private ProjectMapper projectMapper;

    @Override
    public Response addProject(AddProjectRequestVO addProjectRequestVO) {
        // 检查项目名称是否存在 已废弃的除外
        ProjectDO existProject = projectMapper.isExistByName(addProjectRequestVO.getProjectName());
        if (Objects.nonNull(existProject)) {
            log.warn("项目名称已存在：{}", addProjectRequestVO.getProjectName());
            throw new BizException(ResponseCodeEnum.PROJECT_NAME_EXIST);
        }
        // 转换VO到DO并插入
        ProjectDO projectDO = ProjectConvert.INSTANCE.convertVOToDO(addProjectRequestVO);
        // 默认设置为待计划状态
        projectDO.setStatus(ProjectStatus.planning);
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
        QueryWrapper<ProjectDO> queryWrapper = new QueryWrapper<>();
        // 按状态查询
        if (Objects.nonNull(reqVO.getStatus())) {
            queryWrapper.eq("status", reqVO.getStatus());
        }
        // 按名称模糊查询
        Page<ProjectDO> projectPage = projectMapper.findProjectList(page, queryWrapper);
        // 转换结果
        List<FindProjectPageListResVO> vos = ProjectConvert.INSTANCE.convertDOToVOList(projectPage.getRecords());
        return PageResponse.success(projectPage, vos);
    }

    @Override
    public Response deleteProject(DeleteProjectReqVO deleteReqVO) {
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

    @Override
    public Response updateProject(UpdateProjectRequestVO updateReqVO) {
        // 1. 检查项目是否存在
        ProjectDO existProject = projectMapper.selectById(updateReqVO.getProjectId());
        if (existProject == null) {
            log.warn("更新失败，项目不存在：ID={}", updateReqVO.getProjectId());
            throw new BizException(ResponseCodeEnum.PROJECT_NOT_EXIST);
        }
        // 2. 检查名称是否重复（如果名称有修改）
        if (!existProject.getProjectName().equals(updateReqVO.getProjectName())) {
            ProjectDO sameNameProject = projectMapper.isExistByName(updateReqVO.getProjectName());
            if (sameNameProject != null) {
                log.warn("项目名称重复：{}", updateReqVO.getProjectName());
                throw new BizException(ResponseCodeEnum.PROJECT_NAME_EXIST);
            }
        }
        // 3. 将 VO 中的变更字段拷贝到 existProject（保持原有对象引用）
        ProjectConvert.INSTANCE.updateVOToDO(updateReqVO, existProject); // 修改点：使用对象引用传递
        if (ProjectStatus.completed.equals(existProject.getStatus())) {
            existProject.setCompletionTime(new Date());
        }
        // 4. 强制设置更新时间
        existProject.setUpdateTime(new Date());
        int updateCount = projectMapper.updateById(existProject);
        if (updateCount == 0) {
            log.error("项目更新失败：ID={}", updateReqVO.getProjectId());
            throw new BizException(ResponseCodeEnum.PROJECT_UPDATE_ERROR);
        }
        return Response.success();
    }
}