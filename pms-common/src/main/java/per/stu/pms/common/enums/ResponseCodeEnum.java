package per.stu.pms.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import per.stu.pms.common.excption.BaseExceptionInterface;

/**
 * Response code enum
 *
 * @author Stu
 * @date 2021/07/16
 */
@Getter
@AllArgsConstructor
public enum ResponseCodeEnum implements BaseExceptionInterface {

    // ========================== 系统异常 ==========================
    SYSTEM_ERROR("sys.error", "系统异常"),

    // ========================== 参数异常 ==========================
    PARAM_ERROR("param.error", "参数异常"),

    // ========================== 用户相关异常 ==========================
    LOGIN_FAIL("user.login.fail", "登录失败"),
    USERNAME_OR_PWD_ERROR("user.credentials.error", "用户名或密码错误"),
    UNAUTHORIZED("user.unauthorized", "无权限访问，请先登录"),
    ACCESS_DENIED("user.access.denied", "账号权限不足"),
    UPDATE_PASSWORD_FAILED("user.password.update.failed", "修改密码失败"),
    USER_NOT_FOUND("user.not.found", "用户不存在"),
    USER_ALREADY_EXIST("user.already.exist", "用户已存在"),

    // ========================== 用户角色相关异常 ==========================
    USER_ROLE_NOT_EXIST("role.not.found", "用户角色不存在"),
    USER_ROLE_ALREADY_EXIST("role.already.exist", "用户角色已存在"),
    USER_ROLE_CANNOT_DELETE("role.delete.failed", "用户角色不能删除"),
    USER_ROLE_CANNOT_UPDATE("role.update.failed", "用户角色不能更新"),
    USER_ROLE_CANNOT_CREATE("role.create.failed", "用户角色不能创建"),

    // ========================== 分类相关异常 ==========================
    CATEGORY_NAME_EXIST("category.name.exist", "分类名称已存在,请勿重复创建"),
    CATEGORY_ADD_ERROR("category.add.failed", "分类添加失败"),
    CATEGORY_IS_NOT_EXIST("category.not.found", "分类不存在"),
    CATEGORY_NOT_EXISTED("category.not.existed", "分类不存在"),

    // ========================== 标签相关异常 ==========================
    TAG_IS_NOT_EXIST("tag.not.found", "标签不存在"),
    TAG_ADD_ERROR("tag.add.failed", "标签添加失败"),

    // ========================== 文件相关异常 ==========================
    FILE_UPLOAD_FAILED("file.upload.failed", "文件上传失败"),

    // ========================== 文章相关异常 ==========================
    ARTICLE_NOT_FOUND("article.not.found", "文章不存在"),

    // ========================== 项目相关异常 ==========================
    PROJECT_NAME_EXIST("project.name.exist", "项目名称已存在,请勿重复创建"),
    PROJECT_ADD_ERROR("project.add.failed", "项目添加失败"),
    PROJECT_NOT_EXIST("project.not.found", "项目不存在"),
    PROJECT_UPDATE_ERROR("project.update.failed", "项目更新失败"),
    PROJECT_DELETE_ERROR("project.delete.failed", "项目删除失败"),

    // ========================== 任务相关异常 ==========================
    TASK_NAME_EXIST("task.name.exist", "任务名称已存在,请勿重复创建"),
    TASK_ADD_ERROR("task.add.failed", "任务添加失败"),
    TASK_NOT_EXIST("task.not.found", "任务不存在"),
    TASK_UPDATE_ERROR("task.update.failed", "任务更新失败"),
    TASK_DELETE_ERROR("task.delete.failed", "任务删除失败"),

    // ========================== 任务标签相关异常 ==========
    TASK_TAG_SAVE_ERROR("task.tag.save.failed","任务标签保存失败" );

    private final String errorCode; // 使用小写字母表示的错误码
    private final String errorMessage;
}