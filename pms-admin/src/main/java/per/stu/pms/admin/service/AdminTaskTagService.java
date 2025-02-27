package per.stu.pms.admin.service;

import java.util.List;

public interface AdminTaskTagService {

    void saveTaskTag(String taskId, List<Long> tagIds);
}
