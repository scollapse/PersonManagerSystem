package per.stu.pms.common.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import per.stu.pms.common.domain.dos.ProjectDO;

/**
 * @description: Project Mapper
 * @author: syl
 * @create: 2024-12-24 14:42
 **/
@Mapper
public interface ProjectMapper extends BaseMapper<ProjectDO> {
}
