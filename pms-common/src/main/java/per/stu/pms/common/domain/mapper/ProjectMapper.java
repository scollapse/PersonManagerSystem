package per.stu.pms.common.domain.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import per.stu.pms.common.domain.dos.ProjectDO;
import per.stu.pms.common.enums.ProjectStatus;

/**
 * @description: Project Mapper
 * @author: syl
 * @create: 2024-12-24 14:42
 **/
@Mapper
public interface ProjectMapper extends BaseMapper<ProjectDO> {

   default ProjectDO isExistByName(String projectName) {
       // 根据项目名称查询项目信息
       QueryWrapper<ProjectDO> queryWrapper = new QueryWrapper<>();
       queryWrapper.eq("project_name", projectName);
       //状态不为废弃和完成的项目
       queryWrapper.ne("status", ProjectStatus.completed);
       queryWrapper.ne("status", ProjectStatus.deprecated);
       return selectOne(queryWrapper);
   }

   default Page<ProjectDO> findProjectList(Page<ProjectDO> page, QueryWrapper<ProjectDO> queryWrapper){
       return selectPage(page, queryWrapper);
   }
}
