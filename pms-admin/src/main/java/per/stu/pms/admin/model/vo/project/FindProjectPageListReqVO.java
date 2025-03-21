package per.stu.pms.admin.model.vo.project;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import per.stu.pms.common.domain.dtos.project.ProjectQuery;
import per.stu.pms.common.model.BasePageQuery;


@Data
@AllArgsConstructor
@Builder
@ApiModel(value = "查询项目列表 VO")
public class FindProjectPageListReqVO  extends ProjectQuery {

}
