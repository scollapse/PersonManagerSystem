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

    // 注意返回类型改为 TaskVO
    // 后续拓展传递查询条件使用 QueryWrapper 传递参数
    Page<TaskDTO> findTaskList(@Param("page") Page<TaskQuery> page, @Param("query") TaskQuery query);

    StaticTaskDTO selectTaskStatistics();
}
