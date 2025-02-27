package per.stu.pms.common.domain.dos;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import per.stu.pms.common.constants.TableConstants;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName(TableConstants.TABLE_TASK_TAG)
public class TaskTagDO {

    @TableId(type = IdType.AUTO)
    private Long id; // 主键ID

    private String taskId; // 任务ID

    private Long tagId; // 标签ID

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime; // 创建时间

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime; // 更新时间

    @TableLogic(value = "false", delval = "true") // 软删除标记
    private Boolean isDeleted;
}