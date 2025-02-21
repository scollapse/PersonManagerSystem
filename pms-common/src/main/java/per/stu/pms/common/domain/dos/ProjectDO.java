package per.stu.pms.common.domain.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @description: 项目表实体类
 * @author: syl
 * @create: 2024-12-20 17:39
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("projects")
public class ProjectDO {

    @TableId(type = IdType.ASSIGN_UUID) // 使用 UUID 作为主键
    private String projectId;

    private String projectName; // 项目名称
    private String ownerId; // 负责人ID
    private String status; // 状态
    private Integer priority; // 优先级
    private String description; // 项目描述
    private Date startTime; // 计划开始时间
    private Date endTime; // 计划截止时间
    private Date completionTime; // 实际完成时间
    private Integer version; // 乐观锁版本号
    private Boolean isDeleted; // 软删除标记
    private Date createTime; // 创建时间
    private Date updateTime; // 更新时间
}