package per.stu.pms.admin.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import per.stu.pms.admin.model.vo.tag.*;
import per.stu.pms.admin.model.vo.task.UpdateTaskRequestVO;
import per.stu.pms.admin.service.AdminTagService;
import per.stu.pms.common.domain.dos.TagDO;
import per.stu.pms.common.domain.mapper.TagMapper;
import per.stu.pms.common.enums.ResponseCodeEnum;
import per.stu.pms.common.excption.BizException;
import per.stu.pms.common.model.vo.SelectResVO;
import per.stu.pms.common.utils.PageResponse;
import per.stu.pms.common.utils.Response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AdminTagServiceImpl extends ServiceImpl<TagMapper, TagDO> implements AdminTagService {

    @Autowired
    private TagMapper tagMapper;

    @Override
    public Response addTagList(AddTagReqVO addTagReqVO) {
        try {
            // vo 转 do
            List<TagDO> collect = addTagReqVO.getTagNames().stream().map(tagName ->
                    TagDO.builder().name(tagName).createTime(LocalDateTime.now()).updateTime(LocalDateTime.now()).isDeleted(false).build()
            ).collect(Collectors.toList());
            // 批量插入
            saveBatch(collect);
        }catch (Exception e){
            log.warn("add tag list error, {}", e.getMessage());
        }
        return Response.success();
    }


    /**
     * 批量插入并返回 ID 列表
     *
     * @param addTagReqVO
     * @return
     */
    public List<Long> saveBatchAndReturnIds(AddTagReqVO addTagReqVO) {
            // vo 转 do
            List<TagDO> collect = addTagReqVO.getTagNames().stream().map(tagName ->
                    TagDO.builder().name(tagName).createTime(LocalDateTime.now()).updateTime(LocalDateTime.now()).isDeleted(false).build()
            ).collect(Collectors.toList());
            // 批量插入
            boolean isSuccess = saveBatch(collect);
            if (isSuccess) {
                // 提取插入后的 ID 列表
                return collect.stream()
                        .map(TagDO::getId)
                        .collect(Collectors.toList());
            }
            throw new BizException(ResponseCodeEnum.TAG_ADD_ERROR);
    }

    @Override
    public PageResponse findTagList(FindTagPageListReqVO findTagPageListReqVO) {

        // 获取分页参数
        Long  pageNum = findTagPageListReqVO.getCurrent();
        Long  pageSize = findTagPageListReqVO.getSize();
        String name = findTagPageListReqVO.getName();
        LocalDate startDate = findTagPageListReqVO.getStartDate();
        LocalDate endDate = findTagPageListReqVO.getEndDate();
        Page<TagDO> tagList = tagMapper.findTagList(pageNum, pageSize, name, startDate, endDate);
        List<TagDO> records = tagList.getRecords();

        //DO转VO
        List<FindTagPageListResVO> vos = null;
        if (CollectionUtils.isNotEmpty(records)){
            vos = records.stream().map(record ->
                    FindTagPageListResVO.builder()
                            .id(record.getId())
                            .name(record.getName())
                            .build()
            ).collect(Collectors.toList());
        }
        return PageResponse.success(tagList,vos);
    }

    @Override
    public Response deleteTag(DeleteTagReqVO deleteTagReqVO) {
        // 分类 ID
        Long categoryId = deleteTagReqVO.getId();

        // 删除分类
        int count = tagMapper.deleteById(categoryId);

        return count > 0 ? Response.success() : Response.fail(ResponseCodeEnum.TAG_IS_NOT_EXIST);
    }

    @Override
    public Response searchTagList(FindTagReqVO findTagReqVO) {
        String key = findTagReqVO.getKey();
        List<TagDO> tagList = tagMapper.searchTagList(key);
        // DO 转 VO
        List<SelectResVO> vos = null;
        if (CollectionUtils.isNotEmpty(tagList)){
            vos = tagList.stream().map(tag ->
                    SelectResVO.builder()
                            .label(tag.getName())
                            .value(tag.getId())
                            .build()
            ).collect(Collectors.toList());
        }
        return Response.success(vos);
    }


}
