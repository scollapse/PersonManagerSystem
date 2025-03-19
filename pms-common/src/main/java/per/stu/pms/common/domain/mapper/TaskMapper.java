package per.stu.pms.common.domain.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import per.stu.pms.common.domain.dos.TaskDO;
import org.apache.ibatis.annotations.Mapper;
import per.stu.pms.common.domain.dtos.task.StaticTaskDTO;
import per.stu.pms.common.domain.dtos.task.TaskDTO;
import per.stu.pms.common.domain.dtos.task.TaskQuery;
import per.stu.pms.common.enums.TaskStatus;

import java.util.List;

/**
 * @description: 任务表 Mapper 接口
 * @author: syl
 * @create: 2024-12-20 17:39
 **/
@Mapper
public interface TaskMapper extends BaseMapper<TaskDO> {

    default TaskDO isExistByName(String taskName) {
        // 根据任务名称查询任务
        QueryWrapper<TaskDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("task_name", taskName);
        queryWrapper.ne("status", TaskStatus.completed);
        queryWrapper.ne("status", TaskStatus.deprecated);
        return selectOne(queryWrapper);
    }

    StaticTaskDTO selectTaskStatistics();

    // 分页查询ID列表
    Page<String> selectTaskIds(@Param("page") Page<TaskQuery> page, @Param("query") TaskQuery query);

    // 根据ID列表查询详情
    List<TaskDTO> selectTasksByIds(@Param("taskIds") List<String> taskIds);

}
