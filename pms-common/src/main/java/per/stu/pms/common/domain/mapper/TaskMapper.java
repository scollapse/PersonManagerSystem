package per.stu.pms.common.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import per.stu.pms.common.domain.dos.TaskDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description: 任务表 Mapper 接口
 * @author: syl
 * @create: 2024-12-20 17:39
 **/
@Mapper
public interface TaskMapper extends BaseMapper<TaskDO> {
    // 这里可以根据需要添加自定义的 SQL 方法
}
