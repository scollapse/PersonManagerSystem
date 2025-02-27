package per.stu.pms.common.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import per.stu.pms.common.domain.dos.TaskTagDO;

@Mapper
public interface TaskTagMapper extends BaseMapper<TaskTagDO> {
}
