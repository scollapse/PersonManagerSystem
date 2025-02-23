package per.stu.pms.common.domain.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import per.stu.pms.common.domain.dos.ProjectDO;

/**
 * @description: Project Mapper
 * @author: syl
 * @create: 2024-12-24 14:42
 **/
@Mapper
public interface ProjectMapper extends BaseMapper<ProjectDO> {

   default ProjectDO selectByName(String projectName) {
       return selectOne(new QueryWrapper<ProjectDO>().eq("project_name", projectName));
   }

   default Page<ProjectDO> findProjectList(Long pageNum, Long pageSize){
       Page<ProjectDO> page = new Page<>(pageNum, pageSize);
       return selectPage(page, new QueryWrapper<ProjectDO>());
   }
}
