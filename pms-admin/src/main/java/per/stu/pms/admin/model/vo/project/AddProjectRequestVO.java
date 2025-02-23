package per.stu.pms.admin.model.vo.project;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "新增项目请求 VO")
public class AddProjectRequestVO {

    private String projectId; // 项目ID（编辑时需要）
    private String projectName; // 项目名称
    private String ownerId; // 负责人ID
    private String status; // 项目状态（未开始、进行中、已完成、已暂停）
    private String priority; // 项目优先级（1: 高, 2: 中, 3: 低）
    private String description; // 项目描述
    private LocalDateTime startTime; // 开始时间
    private LocalDateTime endTime; // 截止时间
}