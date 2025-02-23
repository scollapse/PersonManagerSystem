package per.stu.pms.admin.convert;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import per.stu.pms.admin.model.vo.task.AddTaskRequestVO;
import per.stu.pms.admin.model.vo.task.FindTaskPageListResVO;
import per.stu.pms.common.domain.dos.TaskDO;
import per.stu.pms.common.enums.TaskStatus;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Mapper(imports = {Date.class})
public interface TaskConvert {

    TaskConvert INSTANCE = Mappers.getMapper(TaskConvert.class);

    @Mapping(target = "taskId", ignore = true) // 忽略 taskId，由数据库生成
    @Mapping(target = "status", source = "status", qualifiedByName = "mapStatus")
    @Mapping(target = "startTime", source = "startTime", qualifiedByName = "mapLocalDateTime")
    @Mapping(target = "endTime", source = "endTime", qualifiedByName = "mapLocalDateTime")
    @Mapping(target = "completionTime", ignore = true) // 忽略 completionTime
    @Mapping(target = "version", constant = "0") // 默认版本号
    @Mapping(target = "isDeleted", constant = "false") // 默认未删除
    @Mapping(target = "createTime", expression = "java(new java.util.Date())")
    @Mapping(target = "updateTime", expression = "java(new java.util.Date())")
    TaskDO convertVOToDO(AddTaskRequestVO addTaskRequestVO);

    // 将 String 类型的 status 转换为 TaskStatus 枚举（假设有 TaskStatus 枚举）
    @Named("mapStatus")
    default TaskStatus mapStatus(String status) {
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

    List<FindTaskPageListResVO> convertDOToVOList(List<TaskDO> records);
}