package per.stu.pms.admin.service.impl;


import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import per.stu.pms.admin.convert.ProjectConvert;
import per.stu.pms.admin.model.vo.project.*;
import per.stu.pms.admin.model.vo.tag.FindTagPageListResVO;
import per.stu.pms.admin.service.AdminProjectService;
import per.stu.pms.common.domain.dos.ProjectDO;
import per.stu.pms.common.domain.dos.TagDO;
import per.stu.pms.common.domain.mapper.ProjectMapper;
import per.stu.pms.common.enums.ResponseCodeEnum;
import per.stu.pms.common.excption.BizException;
import per.stu.pms.common.utils.PageResponse;
import per.stu.pms.common.utils.Response;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @description: 项目管理业务实现类
 * @author: syl
 * @create: 2025-02-21 15:37
 **/
@Service
@Slf4j
public class AdminProjectServiceImpl implements AdminProjectService {

    @Autowired
    private ProjectMapper projectMapper;


    @Override
    public Response addProject(AddProjectRequestVO addProjectRequestVO) {
        //VO 转 DO
        ProjectDO projectDO = projectMapper.selectByName(addProjectRequestVO.getProjectName());
        if (Objects.nonNull(projectDO)){
            log.warn("项目名称已存在：{}", addProjectRequestVO.getProjectName());
            throw new BizException(ResponseCodeEnum.PROJECT_NAME_EXIST);
        }
        // 构建DO
        ProjectDO insertProjectDO = ProjectConvert.INSTANCE.convertVOToDO(addProjectRequestVO);
        // 插入数据库
        int insert = projectMapper.insert(insertProjectDO);
        if (insert == 0){
            log.error("项目新增失败：{}", addProjectRequestVO.getProjectName());
            throw new BizException(ResponseCodeEnum.PROJECT_ADD_ERROR);
        }
        return Response.success();
    }

    @Override
    public PageResponse findProjectList(FindProjectPageListReqVO findProjectPageListReqVO) {
        // 获取分页参数
        Long  pageNum = findProjectPageListReqVO.getCurrent();
        Long  pageSize = findProjectPageListReqVO.getSize();

        Page<ProjectDO> projectPage = projectMapper.findProjectList(pageNum,pageSize);
        List<ProjectDO> records = projectPage.getRecords();
        //DO转VO
        List<FindProjectPageListResVO> vos = ProjectConvert.INSTANCE.convertDOToVOList(records);

        return PageResponse.success(projectPage,vos);
    }

    @Override
    public Response deleteProject(DeleteProjectReqVO deleteProjectReqVO) {
        return null;
    }

    @Override
    public Response searchProjectList(FindProjectReqVO findProjectReqVO) {
        return null;
    }
}
