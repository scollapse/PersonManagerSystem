package per.stu.pms.admin.convert;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import per.stu.pms.admin.model.vo.project.AddProjectRequestVO;
import per.stu.pms.admin.model.vo.project.FindProjectPageListResVO;
import per.stu.pms.admin.model.vo.task.AddTaskRequestVO;
import per.stu.pms.admin.model.vo.task.FindTaskPageListResVO;
import per.stu.pms.admin.model.vo.task.UpdateTaskRequestVO;
import per.stu.pms.common.domain.dos.ProjectDO;
import per.stu.pms.common.domain.dos.TaskDO;
import per.stu.pms.common.domain.dtos.task.TaskDTO;
import per.stu.pms.common.enums.TaskStatus;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Mapper(imports = {Date.class})
public interface TaskConvert {

    TaskConvert INSTANCE = Mappers.getMapper(TaskConvert.class);

    @Mapping(target = "projectId", source = "projectId", qualifiedByName = "mapProjectId")
    @Mapping(target = "startTime", source = "startTime", qualifiedByName = "mapLocalDateTime")
    @Mapping(target = "endTime", source = "endTime", qualifiedByName = "mapLocalDateTime")
    @Mapping(target = "completionTime", ignore = true) // 忽略 completionTime
    @Mapping(target = "version", constant = "0") // 默认版本号
    @Mapping(target = "isDeleted", constant = "false") // 默认未删除
    @Mapping(target = "createTime", expression = "java(new java.util.Date())")
    @Mapping(target = "updateTime", expression = "java(new java.util.Date())")
    TaskDO convertVOToDO(AddTaskRequestVO addTaskRequestVO);



    @Mapping(target = "taskId", ignore = true)
    @Mapping(target = "status", source = "status", qualifiedByName = "mapStatus")
    @Mapping(target = "priority", source = "priority", qualifiedByName = "mapPriority")
    @Mapping(target = "startTime", source = "startTime", qualifiedByName = "mapLocalDateTime")
    @Mapping(target = "endTime", source = "endTime", qualifiedByName = "mapLocalDateTime")
    @Mapping(target = "completionTime", ignore = true)
    @Mapping(target = "version", ignore = true) // 忽略版本号由MP自动处理
    @Mapping(target = "isDeleted", ignore = true)
    @Mapping(target = "createTime", ignore = true) // 保持创建时间不变
    @Mapping(target = "updateTime", ignore = true) // 由Service层手动设置
    void updateVOToDO(UpdateTaskRequestVO source, @MappingTarget TaskDO target); // 改为操作TaskDO


    // 修改后的单个对象转换（增加时间字段映射）
    @Mapping(target = "startTime", source = "startTime", qualifiedByName = "dateToLocalDateTime")
    @Mapping(target = "endTime", source = "endTime", qualifiedByName = "dateToLocalDateTime")
    @Mapping(target = "completionTime", source = "completionTime", qualifiedByName = "dateToLocalDateTime")
    @Mapping(target = "createTime", source = "createTime", qualifiedByName = "dateToLocalDateTime")
    @Mapping(target = "updateTime", source = "updateTime", qualifiedByName = "dateToLocalDateTime")
    FindTaskPageListResVO convertDOToVO(TaskDTO taskDTO);


    List<FindTaskPageListResVO> convertDTOToVOList(List<TaskDTO> records);



    // 自定义转换方法
    @Named("mapProjectId")
    default String mapProjectId(String projectId) {
        if (projectId == null || projectId.isEmpty()) {
            return null;
        }
        return projectId;
    }

    // 将 String 类型的 status 转换为 TaskStatus 枚举（假设有 TaskStatus 枚举）
    @Named("mapStatus")
    default TaskStatus mapStatus(String status) {
        if (status == null || status.isEmpty()) {
            return null;
        }
        return TaskStatus.fromValue(status);
    }

    @Named("mapPriority")
    default Integer mapPriority(String priority) {
        if (priority == null || priority.isEmpty()) {
            return 2;
        }
        return Integer.parseInt(priority);
    }

    // 将 LocalDateTime 转换为 Date
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