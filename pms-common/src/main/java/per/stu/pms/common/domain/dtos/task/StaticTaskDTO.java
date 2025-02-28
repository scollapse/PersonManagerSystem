package per.stu.pms.common.domain.dtos.task;


import lombok.Data;
import lombok.EqualsAndHashCode;
import per.stu.pms.common.domain.dos.TagDO;
import per.stu.pms.common.domain.dos.TaskDO;

import java.util.List;

/**
 * @description: 任务返回DTO
 * @author: syl
 * @create: 2025-02-24 12:33
 **/
@Data
public class StaticTaskDTO {
    private Integer total;
    private Integer unfinished;
    private Integer finished;
    private String  trendData;
}
