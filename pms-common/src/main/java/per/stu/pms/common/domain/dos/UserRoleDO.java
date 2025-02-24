package per.stu.pms.common.domain.dos;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import per.stu.pms.common.constants.TableConstants;

import java.util.Date;

/**
 * @description:  用户角色关系表
 * @author: syl
 * @create: 2024-12-24 14:40
 **/
@Data
@Builder
@TableName(TableConstants.TABLE_USER_ROLE)
public class UserRoleDO {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String username;
    private String role;
    private Date createTime;
}

