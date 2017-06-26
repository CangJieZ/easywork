package easy.work.source.commom.exception;

/**
 * 运行时异常的基类
 * 
 * @Description :
 * @version 1.0
 * 
 */
public class BaseRuntimeException extends RuntimeException {

	/**
	 * @Description
	 * @author
	 * 
	 */
	private static final long serialVersionUID = 9051275901375243959L;
	/**
	 * 错误码
	 */
	private String errorCode;

	@Deprecated
	public BaseRuntimeException() {
		super();
	}

	/**
	 * 
	 * 构造函数
	 * 
	 * @param errorCode
	 *            错误码
	 * @param errorMsg
	 *            错误信息
	 * 
	 */
	public BaseRuntimeException(String errorCode, String errorMsg) {
		super(errorMsg);
		this.errorCode = errorCode;
	}
	
	
	
	public BaseRuntimeException(String errorCode) {
		super(errorCode);
	}

	/**
	 * 取错误码
	 * 
	 * @Description
	 * @return
	 * 
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * 取错误信息
	 * 
	 * @Description
	 * @return
	 * 
	 */
	public String getErrorMsg() {
		return getMessage();
	}
}
