package per.stu.pms.admin.convert;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import per.stu.pms.admin.model.vo.tag.FindTagPageListResVO;
import per.stu.pms.common.domain.dos.TagDO;

import java.util.List;

@Mapper(uses = {DateConverter.class})
public interface TagConvert {
    TagConvert INSTANCE = Mappers.getMapper(TagConvert.class);

    @Mapping(target = "createTime", source = "createTime", qualifiedByName = "localDateTimeToDate")
    @Mapping(target = "updateTime", source = "updateTime", qualifiedByName = "localDateTimeToDate")
    FindTagPageListResVO convertDOToVO(TagDO tagDO);

    List<FindTagPageListResVO> convertDOToVOList(List<TagDO> tags);
}