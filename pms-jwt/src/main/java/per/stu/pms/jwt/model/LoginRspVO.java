package per.stu.pms.jwt.model;

import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRspVO {
    /**
     * Token 值
     */
    private String token;
}
