package per.stu.pms.common.domain.dos;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import per.stu.pms.common.constants.TableConstants;
import per.stu.pms.common.enums.ProjectStatus;;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName(TableConstants.TABLE_PROJECT)
public class ProjectDO {

    @TableId(type = IdType.ASSIGN_UUID)
    private String projectId;

    private String projectName;
    private String ownerId;
    private ProjectStatus status; // 使用枚举类型
    private Integer priority;
    private String description;
    private Date startTime;
    private Date endTime;
    private Date completionTime;
    private Integer version;

    @TableLogic(value = "false", delval = "true") // 关键配置
    private Boolean isDeleted;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
