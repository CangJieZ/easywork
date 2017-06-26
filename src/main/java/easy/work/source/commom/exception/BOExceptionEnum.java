package easy.work.source.commom.exception;

/**
 * 
 * @Description : BoException的错误码和错误信息枚举
 * @version 1.0  
 */
public enum BOExceptionEnum {
	
	BO_USER_OR_PWD_ERROR("BO_USER_OR_PWD_ERROR", "用户名或密码错误!"),

	OUT_TIME_LOGIN("OUT_TIME_LOGIN", "登录超时，请重新登录!"),	
	NOT_LOGIN("NOT_LOGIN", "未登录，请先登录!"),	
	NOT_RIGHT("NOT_RIGHT","没有后台权限,请联系管理员授权!"),	
	IP_REJECT("IP_REJECT","后台访问ip限制!"),
	
	OLD_LOGIN_PWD_NOT_OK("OLD_LOGIN_PWD_NOT_OK","原密码不正确!"),
	
	BO_SYS_CONTROL_UPDATE_FAIL("BO_SYS_CONTROL_UPDATE_FAIL","更新系统参数失败!"),
	BO_BACK_USER_PWD_MODIFY_FAIL("BO_BACK_USER_PWD_MODIFY_FAIL","后台用户密码修改失败!"),
	BO_SYS_DICT_FAIL_ID_IS_EXTIST("BO_SYS_DICT_FAIL_ID_IS_EXTIST","ID已存在!"),
	BO_SYS_DICT_FAIL_ID_IS_NO_EXTIST("BO_SYS_DICT_FAIL_ID_IS_NO_EXTIST","ID不存在!"),
	
	;
	
	
	
	
	/**
	 * 错误码
	 */
	private String errorCode;
	
	/**
	 * 错误信息
	 */
	private String errorMsg;
	
	private BOExceptionEnum(String errorCode, String errorMsg){
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
