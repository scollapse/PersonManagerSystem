package per.stu.pms.admin.service;

import per.stu.pms.admin.model.vo.tag.AddTagReqVO;
import per.stu.pms.admin.model.vo.tag.DeleteTagReqVO;
import per.stu.pms.admin.model.vo.tag.FindTagPageListReqVO;
import per.stu.pms.admin.model.vo.tag.FindTagReqVO;
import per.stu.pms.common.utils.PageResponse;
import per.stu.pms.common.utils.Response;

public interface AdminTagService {

    Response addTagList(AddTagReqVO addTagReqVO);

    PageResponse findTagList(FindTagPageListReqVO findTagPageListReqVO);

    Response deleteTag(DeleteTagReqVO deleteTagReqVO);

    Response searchTagList(FindTagReqVO findTagReqVO);
}
