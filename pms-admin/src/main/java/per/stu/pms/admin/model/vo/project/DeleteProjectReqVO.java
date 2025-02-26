package per.stu.pms.admin.model.vo.project;


import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "删除项目 VO")
public class DeleteProjectReqVO {
    private String projectId;
}
