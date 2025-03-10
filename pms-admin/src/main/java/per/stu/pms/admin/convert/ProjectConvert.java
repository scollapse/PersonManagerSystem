package per.stu.pms.admin.convert;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import per.stu.pms.admin.model.vo.project.AddProjectRequestVO;
import per.stu.pms.admin.model.vo.project.FindProjectPageListReqVO;
import per.stu.pms.admin.model.vo.project.FindProjectPageListResVO;
import per.stu.pms.admin.model.vo.project.UpdateProjectRequestVO;
import per.stu.pms.common.domain.dos.ProjectDO;
import per.stu.pms.common.domain.dtos.project.ProjectDTO;
import per.stu.pms.common.domain.dtos.project.ProjectQuery;
import per.stu.pms.common.enums.ProjectStatus;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Mapper(imports = {Date.class})
public interface ProjectConvert {

    ProjectConvert INSTANCE = Mappers.getMapper(ProjectConvert.class);

    // VO -> DO（原有逻辑）
    @Mapping(target = "status", source = "status", qualifiedByName = "mapStatus")
    @Mapping(target = "priority", source = "priority", qualifiedByName = "mapPriority")
    @Mapping(target = "startTime", source = "startTime", qualifiedByName = "mapLocalDateTime")
    @Mapping(target = "endTime", source = "endTime", qualifiedByName = "mapLocalDateTime")
    @Mapping(target = "completionTime", ignore = true)
    @Mapping(target = "version", constant = "0")
    @Mapping(target = "isDeleted", constant = "false")
    @Mapping(target = "createTime", expression = "java(new java.util.Date())")
    @Mapping(target = "updateTime", expression = "java(new java.util.Date())")
    ProjectDO convertVOToDO(AddProjectRequestVO bean);

    // 修改后的单个对象转换（增加时间字段映射）
    @Mapping(target = "startTime", source = "startTime", qualifiedByName = "dateToLocalDateTime")
    @Mapping(target = "endTime", source = "endTime", qualifiedByName = "dateToLocalDateTime")
    @Mapping(target = "completionTime", source = "completionTime", qualifiedByName = "dateToLocalDateTime")
    @Mapping(target = "createTime", source = "createTime", qualifiedByName = "dateToLocalDateTime")
    @Mapping(target = "updateTime", source = "updateTime", qualifiedByName = "dateToLocalDateTime")
    FindProjectPageListResVO convertDTOToVO(ProjectDTO projectDTO);

    // 列表转换（MapStruct 自动实现）
    List<FindProjectPageListResVO> convertDTOToVOList(List<ProjectDTO> projectDTOList);


    // 新增：更新时的 VO -> DO 转换方法
    // 修改后的转换方法：将 VO 的变更字段映射到现有 DO 对象
    @Mapping(target = "projectId", ignore = true) // 防止覆盖已有ID
    @Mapping(target = "status", source = "status", qualifiedByName = "mapStatus")
    @Mapping(target = "priority", source = "priority", qualifiedByName = "mapPriority")
    @Mapping(target = "startTime", source = "startTime", qualifiedByName = "mapLocalDateTime")
    @Mapping(target = "endTime", source = "endTime", qualifiedByName = "mapLocalDateTime")
    @Mapping(target = "completionTime", ignore = true)
    @Mapping(target = "description", source = "description")
    @Mapping(target = "projectName", source = "projectName")
    void updateVOToDO(UpdateProjectRequestVO source, @MappingTarget ProjectDO target); // 关键注解 @MappingTarget


    /**
     * VO -> Query
     * @param reqVO
     * @return
     */
    ProjectQuery convertVOToQuery(FindProjectPageListReqVO reqVO);


    // ------------------------- 以下为转换方法 -------------------------
    @Named("mapStatus")
    default ProjectStatus mapStatus(String status) {
        if (status == null || status.isEmpty()) {
            return null;
        }
        return ProjectStatus.fromValue(status);
    }

    @Named("mapPriority")
    default Integer mapPriority(String priority) {
        if (priority == null || priority.isEmpty()) {
            return 2;
        }
        return Integer.parseInt(priority);
    }

    @Named("mapLocalDateTime")
    default Date mapLocalDateTime(LocalDateTime localDateTime) {
        return localDateTime != null
                ? Date.from(localDateTime.atZone(java.time.ZoneId.systemDefault()).toInstant())
                : null;
    }

    // 新增 Date 转 LocalDateTime 方法
    @Named("dateToLocalDateTime")
    default LocalDateTime dateToLocalDateTime(Date date) {
        if (date == null) return null;
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

}