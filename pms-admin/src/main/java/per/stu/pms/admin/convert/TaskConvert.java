package per.stu.pms.admin.convert;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.springframework.util.StringUtils;
import per.stu.pms.admin.model.vo.task.*;
import per.stu.pms.common.domain.dos.TaskDO;
import per.stu.pms.common.domain.dtos.task.StaticTaskDTO;
import per.stu.pms.common.domain.dtos.task.TaskDTO;
import per.stu.pms.common.domain.dtos.task.TaskQuery;
import per.stu.pms.common.enums.TaskStatus;

import java.util.Date;
import java.util.List;

@Mapper(uses = {DateConverter.class, TagConvert.class}) // 引入 DateConverter 和 TagConvert
public interface TaskConvert {

    TaskConvert INSTANCE = Mappers.getMapper(TaskConvert.class);

    /**
     * 新增VO 转为数据库 DTO
     * @param addTaskRequestVO
     * @return
     */
    @Mapping(target = "projectId", source = "projectId", qualifiedByName = "mapProjectId")
    @Mapping(target = "startTime", source = "startTime", qualifiedByName = "localDateTimeToDate") // 使用 localDateTimeToDate
    @Mapping(target = "endTime", source = "endTime", qualifiedByName = "localDateTimeToDate")     // 使用 localDateTimeToDate
    @Mapping(target = "completionTime", ignore = true)
    @Mapping(target = "version", constant = "0")
    @Mapping(target = "isDeleted", constant = "false")
    @Mapping(target = "createTime", expression = "java(new java.util.Date())")
    @Mapping(target = "updateTime", expression = "java(new java.util.Date())")
    TaskDO convertVOToDO(AddTaskRequestVO addTaskRequestVO);

    /**
     * 更新VO 转为数据库 DTO
     * @param source
     * @param target
     */
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

    /**
     * 数据库 DTO 转为 VO
     * @param taskDTO
     * @return
     */
    @Mapping(target = "startTime", source = "startTime", qualifiedByName = "dateToLocalDateTime") // 使用 dateToLocalDateTime
    @Mapping(target = "endTime", source = "endTime", qualifiedByName = "dateToLocalDateTime")     // 使用 dateToLocalDateTime
    @Mapping(target = "completionTime", source = "completionTime", qualifiedByName = "dateToLocalDateTime")
    @Mapping(target = "createTime", source = "createTime", qualifiedByName = "dateToLocalDateTime")
    @Mapping(target = "updateTime", source = "updateTime", qualifiedByName = "dateToLocalDateTime")
    @Mapping(target = "tags", source = "tags") // 映射标签列表（需要 TagConvert 支持）
    FindTaskPageListResVO convertDOToVO(TaskDTO taskDTO);

    /**
     * 数据库 DTO 列表 转为 VO 列表
     * @param records
     * @return
     */
    List<FindTaskPageListResVO> convertDTOToVOList(List<TaskDTO> records);


    /**
     * 数据库 统计 DTO 转为 VO
     * @param staticTaskDTO
     * @return
     */
    @Mapping(target = "total", source = "total")
    @Mapping(target = "unfinished", source = "unfinished")
    @Mapping(target = "finished", source = "finished")
    @Mapping(target = "trendData", source = "trendData",qualifiedByName = "convertTrendData")
    StaticTaskVO convertDTOToStatisticVO(StaticTaskDTO staticTaskDTO);


    /**
     * 查询条件 VO 转为数据库查询条件 DTO
     * @param findTaskPageListReqVO
     * @return
     */
    TaskQuery convertVOToQuery(FindTaskPageListReqVO findTaskPageListReqVO);


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