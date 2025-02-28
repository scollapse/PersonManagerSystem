package per.stu.pms.admin.model.vo.tag;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "标签分页查询返回 VO")
public class FindTagPageListResVO {

    private Long id;
    private String name;
    private Date createTime;
    private Date updateTime;
    private Boolean isDeleted;

}
