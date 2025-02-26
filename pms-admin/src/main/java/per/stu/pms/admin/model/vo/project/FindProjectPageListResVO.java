package per.stu.pms.admin.model.vo.project;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import per.stu.pms.common.enums.ProjectStatus;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "项目分页查询 VO")
public class FindProjectPageListResVO {
    private String projectId;

    private String projectName;
    private String ownerId;
    private ProjectStatus status;
    private Integer priority;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDateTime completionTime;
    private Integer version;
    private Boolean isDeleted;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
