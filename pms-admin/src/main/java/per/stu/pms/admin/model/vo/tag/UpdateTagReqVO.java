package per.stu.pms.admin.model.vo.tag;


import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 更新标签 VO
 * @author: syl
 * @create: 2025-02-27 10:49
 **/


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "更新标签 VO")
public class UpdateTagReqVO {

    private Long id;

    private String name;
}
