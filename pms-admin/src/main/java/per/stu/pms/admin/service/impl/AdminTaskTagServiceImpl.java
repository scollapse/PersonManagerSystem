package per.stu.pms.admin.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import per.stu.pms.admin.service.AdminTaskTagService;
import per.stu.pms.common.domain.dos.TaskTagDO;
import per.stu.pms.common.domain.mapper.TaskTagMapper;
import per.stu.pms.common.enums.ResponseCodeEnum;
import per.stu.pms.common.excption.BizException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: TaskTagServiceImpl
 * @author: syl
 * @create: 2025-02-27 17:12
 **/
@Service
@Slf4j
public class AdminTaskTagServiceImpl extends ServiceImpl<TaskTagMapper,TaskTagDO > implements AdminTaskTagService {


    @Autowired
    private TaskTagMapper taskTagMapper;

    @Override
    public void saveTaskTag(String taskId, List<Long> tagIds) {
        if (StringUtils.isEmpty(taskId) || CollectionUtils.isEmpty(tagIds)) {
            log.warn("任务ID或标签ID列表为空，taskId: {}, tagIds: {}", taskId, tagIds);
            throw new BizException(ResponseCodeEnum.PARAM_ERROR);
        }

        // 1. 删除原有关联
        LambdaQueryWrapper<TaskTagDO> deleteWrapper = new LambdaQueryWrapper<>();
        deleteWrapper.eq(TaskTagDO::getTaskId, taskId);
        taskTagMapper.delete(deleteWrapper);

        // 2. 批量新增关联
        List<TaskTagDO> taskTagList = tagIds.stream()
                .map(tagId -> {
                    TaskTagDO taskTagDO = new TaskTagDO();
                    taskTagDO.setTaskId(taskId);
                    taskTagDO.setTagId(tagId);
                    return taskTagDO;
                })
                .collect(Collectors.toList());

        // 批量插入
        boolean isSuccess = saveBatch(taskTagList);
        if (!isSuccess) {
            log.error("批量插入任务-标签关联失败，taskId: {}, tagIds: {}", taskId, tagIds);
            throw new BizException(ResponseCodeEnum.TASK_TAG_SAVE_ERROR);
        }

        log.info("任务-标签关联保存成功，taskId: {}, tagIds: {}", taskId, tagIds);
    }}
