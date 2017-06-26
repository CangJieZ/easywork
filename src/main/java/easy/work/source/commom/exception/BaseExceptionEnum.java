package easy.work.source.commom.exception;

/**
 * 
 * @Description : 基本错误的错误码和错误信息枚举
 * @version 1.0  
 *
 */
public enum BaseExceptionEnum {
	
	DAO_ERROR				("DAO_ERROR", 				"DAO层系统异常!"),
	SYSTEM_ERROR			("SYSTEM_ERROR",			"系统异常!"),
	LOGIC_ERROR				("LOGIC_ERROR",				"逻辑异常!"),
	DATE_TIME_PARSE_ERROR	("DATE_TIME_PARSE_ERROR",	"日期时间解析异常!"),	
		
	;
	
	
	
	
	/**
	 * 错误码
	 */
	private String errorCode;
	
	/**
	 * 错误信息
	 */
	private String errorMsg;
	
	private BaseExceptionEnum(String errorCode, String errorMsg){
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
	
	public String errorCode(){
		return errorCode;
	}
	
	public String errorMsg(){
		return errorMsg;
	}
}
