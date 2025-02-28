package per.stu.pms.admin.model.vo.task;


import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @description: 统计任务 VO
 * @author: syl
 * @create: 2025-02-26 11:08
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "统计任务 VO")
public class StaticTaskVO {
    private Integer total;
    private Integer unfinished;
    private Integer finished;
    private List<Integer> trendData;
}
