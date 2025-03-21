package per.stu.pms.common.domain.dtos.project;


import lombok.Data;
import lombok.EqualsAndHashCode;
import per.stu.pms.common.domain.dos.ProjectDO;
import per.stu.pms.common.handler.JsonArrayTypeHandler;

import java.util.List;

/**
 * @description: TODO
 * @author: syl
 * @create: 2025-03-10 15:53
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class ProjectDTO extends ProjectDO {
    // 全部任务数
    private Integer allTaskCount;
    // 已完成任务数
    private Integer completedTaskCount;
    // 近七日任务数据
    private List<Integer> weekData;

}
