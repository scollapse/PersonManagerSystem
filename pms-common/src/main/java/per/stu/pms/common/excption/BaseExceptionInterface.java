package per.stu.pms.common.excption;

/**
 * Base exception interface
 * @author Stu
 * @date 2021/05/08
 * @since 1.0.0
 * @see BaseExceptionInterface
 */
public interface BaseExceptionInterface {
    String getErrorCode();
    String getErrorMessage();
}
