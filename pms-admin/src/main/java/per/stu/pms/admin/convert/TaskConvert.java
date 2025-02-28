package per.stu.pms.admin.convert;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.springframework.util.StringUtils;
import per.stu.pms.admin.model.vo.task.AddTaskRequestVO;
import per.stu.pms.admin.model.vo.task.FindTaskPageListResVO;
import per.stu.pms.admin.model.vo.task.StaticTaskVO;
import per.stu.pms.admin.model.vo.task.UpdateTaskRequestVO;
import per.stu.pms.common.domain.dos.TaskDO;
import per.stu.pms.common.domain.dtos.task.StaticTaskDTO;
import per.stu.pms.common.domain.dtos.task.TaskDTO;
import per.stu.pms.common.enums.TaskStatus;

import java.util.Date;
import java.util.List;

@Mapper(uses = {DateConverter.class, TagConvert.class}) // 引入 DateConverter 和 TagConvert
public interface TaskConvert {

    TaskConvert INSTANCE = Mappers.getMapper(TaskConvert.class);

    @Mapping(target = "projectId", source = "projectId", qualifiedByName = "mapProjectId")
    @Mapping(target = "startTime", source = "startTime", qualifiedByName = "localDateTimeToDate") // 使用 localDateTimeToDate
    @Mapping(target = "endTime", source = "endTime", qualifiedByName = "localDateTimeToDate")     // 使用 localDateTimeToDate
    @Mapping(target = "completionTime", ignore = true)
    @Mapping(target = "version", constant = "0")
    @Mapping(target = "isDeleted", constant = "false")
    @Mapping(target = "createTime", expression = "java(new java.util.Date())")
    @Mapping(target = "updateTime", expression = "java(new java.util.Date())")
    TaskDO convertVOToDO(AddTaskRequestVO addTaskRequestVO);

    @Mapping(target = "taskId", ignore = true)
    @Mapping(target = "status", source = "status", qualifiedByName = "mapStatus")
    @Mapping(target = "priority", source = "priority", qualifiedByName = "mapPriority")
    @Mapping(target = "startTime", source = "startTime", qualifiedByName = "localDateTimeToDate") // 使用 localDateTimeToDate
    @Mapping(target = "endTime", source = "endTime", qualifiedByName = "localDateTimeToDate")     // 使用 localDateTimeToDate
    @Mapping(target = "completionTime", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "isDeleted", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    void updateVOToDO(UpdateTaskRequestVO source, @MappingTarget TaskDO target);

    @Mapping(target = "startTime", source = "startTime", qualifiedByName = "dateToLocalDateTime") // 使用 dateToLocalDateTime
    @Mapping(target = "endTime", source = "endTime", qualifiedByName = "dateToLocalDateTime")     // 使用 dateToLocalDateTime
    @Mapping(target = "completionTime", source = "completionTime", qualifiedByName = "dateToLocalDateTime")
    @Mapping(target = "createTime", source = "createTime", qualifiedByName = "dateToLocalDateTime")
    @Mapping(target = "updateTime", source = "updateTime", qualifiedByName = "dateToLocalDateTime")
    @Mapping(target = "tags", source = "tags") // 映射标签列表（需要 TagConvert 支持）
    FindTaskPageListResVO convertDOToVO(TaskDTO taskDTO);

    List<FindTaskPageListResVO> convertDTOToVOList(List<TaskDTO> records);


    @Mapping(target = "total", source = "total")
    @Mapping(target = "unfinished", source = "unfinished")
    @Mapping(target = "finished", source = "finished")
    @Mapping(target = "trendData", source = "trendData",qualifiedByName = "convertTrendData")
    StaticTaskVO convertDTOToStatisticVO(StaticTaskDTO staticTaskDTO);


    // 自定义转换方法
    @Named("mapProjectId")
    default String mapProjectId(String projectId) {
        return (projectId == null || projectId.isEmpty()) ? null : projectId;
    }

    @Named("mapStatus")
    default TaskStatus mapStatus(String status) {
        return TaskStatus.fromValue(status);
    }

    @Named("mapPriority")
    default Integer mapPriority(String priority) {
        return (priority == null || priority.isEmpty()) ? 2 : Integer.parseInt(priority);
    }

    @Named("convertTrendData")
    default List<Integer> convertTrendData(String trendData) {
        List<Integer> trendList = new java.util.ArrayList<>();
        //trendData 不为null，且长度大于0
        if (StringUtils.hasLength(trendData)) {
            String[] trendArr = trendData.split(",");
            //将字符串数组转换为List<Integer>
            for (String str : trendArr) {
                trendList.add(Integer.parseInt(str));
            }
        }
        return trendList;
    }

}