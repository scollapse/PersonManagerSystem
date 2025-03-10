package per.stu.pms.common.domain.dtos.project;


import lombok.Data;
import per.stu.pms.common.model.BasePageQuery;

/**
 * @description: ProjectQuery
 * @author: syl
 * @create: 2025-03-10 15:55
 **/
@Data
public class ProjectQuery extends BasePageQuery {
    private String projectName;
    private String status;
}
