package per.stu.pms.common.domain.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import per.stu.pms.common.domain.dos.ProjectDO;
import per.stu.pms.common.domain.dos.TaskDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description: 任务表 Mapper 接口
 * @author: syl
 * @create: 2024-12-20 17:39
 **/
@Mapper
public interface TaskMapper extends BaseMapper<TaskDO> {

    default TaskDO selectByName(String taskName) {
        return selectOne(new QueryWrapper<TaskDO>().eq("task_name", taskName));
    }

   default Page<TaskDO> finTaskList(Long pageNum, Long pageSize){
        Page<TaskDO> page = new Page<>(pageNum, pageSize);
        return selectPage(page, new QueryWrapper<TaskDO>());
   }
}
