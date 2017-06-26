package easy.work.source.commom.exception;

/**
 * 
 * @Description : 业务异常
 * @version 1.0
 */
public class BOException extends BaseRuntimeException {
	private static final long serialVersionUID = -8246635855921697781L;

	/**
	 * 
	 * BOExceptionEnum构造业务层异常
	 * 
	 * @param en
	 */
	public BOException(BOExceptionEnum en) {
		super(en.errorCode(), en.errorMsg());
	}


	public BOException(String errorMsg) {
		super("", errorMsg);
	}

	/**
	 * 
	 * 构造函数
	 * 
	 * @param errorCode
	 *            错误码
	 * @param errorMsg
	 *            错误信息
	 */
	@Deprecated
	public BOException(String errorCode, String errorMsg) {
		super(errorCode, errorMsg);
	}

	/**
	 * 抛出BOException异常
	 * 
	 * @Description
	 * @param en
	 *            异常枚举
	 * 
	 */
	public static void throwz(BOExceptionEnum en) {
		throw new BOException(en);
	}
	
	/**
	 * @Auther: maomy  
	 * @Date:2016-3-4下午2:59:38
	 * @param msg  
	 * @return void 
	 */
	public static void throwz(String msg) {
		throw new BOException(msg);
	}
}
