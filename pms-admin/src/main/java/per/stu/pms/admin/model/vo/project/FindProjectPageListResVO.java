package per.stu.pms.admin.model.vo.project;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import per.stu.pms.common.enums.ProjectStatus;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindProjectPageListResVO {
    private String projectId;

    private String projectName;
    private String ownerId;
    private ProjectStatus status;
    private Integer priority;
    private String description;
    private Date startTime;
    private Date endTime;
    private Date completionTime;
    private Integer version;
    private Boolean isDeleted;
    private Date createTime;
    private Date updateTime;
}
