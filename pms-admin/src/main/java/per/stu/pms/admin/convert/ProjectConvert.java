package per.stu.pms.admin.convert;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import per.stu.pms.admin.model.vo.project.AddProjectRequestVO;
import per.stu.pms.admin.model.vo.project.FindProjectPageListResVO;
import per.stu.pms.common.domain.dos.ProjectDO;
import per.stu.pms.common.enums.ProjectStatus;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Mapper(imports = {Date.class})
public interface ProjectConvert {

    ProjectConvert INSTANCE = Mappers.getMapper(ProjectConvert.class);

    // VO -> DO（原有逻辑）
    @Mapping(target = "projectId", ignore = true)
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

    // 新增：DO -> VO
    FindProjectPageListResVO convertDOToVO(ProjectDO projectDO);

    // 列表转换（MapStruct 自动实现）
    List<FindProjectPageListResVO> convertDOToVOList(List<ProjectDO> projectDOList);


    // 新增：更新时的 VO -> DO 转换方法
    @Mapping(target = "projectId", ignore = true)
    @Mapping(target = "status", source = "status", qualifiedByName = "mapStatus")
    @Mapping(target = "priority", source = "priority", qualifiedByName = "mapPriority")
    @Mapping(target = "startTime", source = "startTime", qualifiedByName = "mapLocalDateTime")
    @Mapping(target = "endTime", source = "endTime", qualifiedByName = "mapLocalDateTime")
    @Mapping(target = "completionTime", ignore = true)
    @Mapping(target = "version", ignore = true) // 忽略版本号，如果需要更新版本号可以去掉这行
    @Mapping(target = "isDeleted", ignore = true) // 忽略删除状态
    @Mapping(target = "createTime", ignore = true) // 忽略创建时间
    @Mapping(target = "updateTime", expression = "java(new java.util.Date())") // 更新时间为当前时间
    ProjectDO updateVOToDO(AddProjectRequestVO bean);

    // ------------------------- 以下为转换方法 -------------------------
    @Named("mapStatus")
    default ProjectStatus mapStatus(String status) {
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
}