package easy.work.source.commom.context;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import easy.work.source.commom.exception.BOException;
import easy.work.source.commom.exception.BOExceptionEnum;
import easy.work.source.commom.exception.BaseExceptionEnum;
import easy.work.source.commom.utils.DateTimeUtil;
import easy.work.source.commom.utils.ParameterUtil;
import easy.work.source.commom.utils.StringUtil;
import easy.work.source.module.user.pojo.BackUserPojo;


public class SessionContext {
	/**
	 * 日志
	 */
	private static final Log log = LogFactory.getLog(SessionContext.class);
	/**
	 * 后台用户标识
	 */
	private static final String BACK_USER = "BACK_USER";
	/**
	 * 前台用户标识
	 */
	private static final String FRONT_USER = "FRONT_USER";
	/**
	 * 前台两周免登录
	 */
	private static final String FRONT_USER_LONG = "FRONT_USER_LONG";
	/**
	 * 登录来源标识
	 */
	private static final String LOGIN_SOURCE = "LOGIN_SOURCE";
	/**
	 * 验证码标识
	 */
	private static final String CHECK_CODE = "CHECK_CODE";
	/**
	 * 请求表单的令牌
	 */
	public static final String FORM_TOKEN = "FORM_TOKEN";
	/**
	 * cookie有效时间，单位秒
	 */
	public static final long COOKIE_LIVE_TIME = 30*60;
	/**
	 * 线程变量
	 */
	private static final ThreadLocal<HttpSession> httpSessions = new ThreadLocal<HttpSession>();
	
	/**
	 * 清空SessionContext
	 * @Description  
	 *
	 */
	public static void clean(){
		httpSessions.set(null);
	}

	/**
	 * 设置当前线程中的session
	 * 
	 * @param session
	 */
	public static void setHttpSession(HttpSession session) {
		log.error("setHttpSession 已经设置了 session = "+session);
		httpSessions.set(session);
	}

	/**
	 * 返回当前线程中的session
	 * 
	 * @return HttpSession
	 */
	public static HttpSession getHttpSession() {
		HttpSession session = httpSessions.get();
		if (session == null) {
			if (log.isDebugEnabled()) {
				log.error("SessionContext上下文中HttpSession为空，threadLocal="
						+ httpSessions + ",threadId="
						+ Thread.currentThread().getId());
			}
		}
		return session;
	}

	/**
	 * 设置表单token
	 * 
	 * @Description
	 * 
	 */
	public static void setFormToken(String formToken) {
		getHttpSession().setAttribute(FORM_TOKEN, formToken);
	}

	/**
	 * 获取表单token
	 * 
	 * @Description
	 * @return
	 * 
	 */
	public static String getFormToken() {
		if (getHttpSession() == null) {
			return null;
		}
		return (String) getHttpSession().getAttribute(FORM_TOKEN);
	}

	/**
	 * 
	 * @Description 设置登录来源
	 * @param source
	 */
	/*public static void setLoginSource(String source) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("login_source", source);
		map.put("time", DateTimeUtil.getCurrDateTimeStr());
		getHttpSession()
				.setAttribute(LOGIN_SOURCE, ParameterUtil.toString(map));
	}*/

	/**
	 * 
	 * @Description 获取登录来源
	 * @return
	 */
	/*public static String getLoginSource() {
		String source = null;
		HttpSession session = getHttpSession();
		if (session != null) {
			String str = (String) session.getAttribute(LOGIN_SOURCE);
			Map<String, String> map = ParameterUtil.toMap(str);
			if (map != null) {
				source = map.get("login_source");
			}
		}

		return LoginSourceEnum.getLoginSourceEnum(source) == null ? LoginSourceEnum.CUSTOMER
				.code() : source;
	}*/

	/**
	 * 
	 * @Description 删除登录来源
	 */
	public static void removeLoginSource() {
		getHttpSession().removeAttribute(LOGIN_SOURCE);
	}

	/**
	 * @Auther: maomy  
	 * @Description:设置验证码
	 * @Date:2017-3-4下午3:00:10
	 * @param value  
	 * @return void 
	 */
	public static void setCheckCode(String value) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("check_code", value);
		map.put("check_code_time", DateTimeUtil.getCurrDateTimeStr());
		getHttpSession().setAttribute(CHECK_CODE, ParameterUtil.toString(map));
	}

	/**
	 * 
	 * @Description 删除验证码
	 */
	public static void removeCheckCode() {
		getHttpSession().removeAttribute(CHECK_CODE);
	}

	
	
	/**
	 * @Auther: maomy  
	 * @Description: 获取验证码
	 * @Date:2016-3-4下午2:59:58
	 * @return  
	 * @return String 
	 */
	public static String getCheckCode() {
		String str = (String) getHttpSession().getAttribute(CHECK_CODE);
		Map<String, String> map = ParameterUtil.toMap(str);
		if (map == null)
			BOException.throwz("验证码错误");

		str = map.get("check_code");
		if (StringUtil.isBlank(str))
			BOException.throwz( "验证码错误");

		return str;
	}

	/**
	 * 获取前台用户
	 * 
	 * @param user
	 */
	/*public static User getFrontCurrentUser() {
		HttpSession session = getHttpSession();
		if (null == session) {
			return null;
		}

		String str = (String) session.getAttribute(FRONT_USER);
		if (str == null) {
			str = (String) session.getAttribute(FRONT_USER_LONG);
		}
		return userStringToUser(str);
	}*/

	/**
	 * 设置前台用户
	 * 
	 * @param user
	 */
	/*public static void setFrontCurrentUser(User user) {
		removeFrontCurrentUser();
		HttpSession session = getHttpSession();
		if (user.getOut_time() > COOKIE_LIVE_TIME) {
			session.setAttribute(FRONT_USER_LONG, userToUserString(user));
		} else {
			session.setAttribute(FRONT_USER, userToUserString(user));
		}
	}*/

	/**
	 * @Description
	 */
	public static void removeFrontCurrentUser() {
		// 删除两周免登录
		getHttpSession().removeAttribute(FRONT_USER_LONG);
		// 删除内存cookie
		getHttpSession().removeAttribute(FRONT_USER);
	}

	/**
	 * 获取后台用户
	 * 
	 * @param user
	 */
	public static BackUserPojo getBackCurrentUser() {
		String str = (String) getHttpSession().getAttribute(BACK_USER);
		return userStringToUser(str);
	}

	/**
	 * 设置后台用户
	 * 
	 * @param user
	 */
	public static void setBackCurrentUser(BackUserPojo user) {
		HttpSession session = getHttpSession();
		session.setAttribute(BACK_USER, userToUserString(user));
	}

	/**
	 * @Description
	 */
	public static void removeBackCurrentUser() {
		getHttpSession().removeAttribute(BACK_USER);
	}

	/**
	 * 
	 * @功能：把cookie中的用户信息封装成User对象
	 * @param userStr
	 * @return
	 * @throws ParseException
	 */
	private static BackUserPojo userStringToUser(String userStr) {
		Map<String, String> map = ParameterUtil.toMap(userStr);
		if (map == null) {
			if (log.isDebugEnabled())
				log.debug("string信息转换出错");
			return null;
		}

		Date userDate = null;
		try {
			userDate = DateTimeUtil.getFormatDateTime(map.get("login_time"));
		} catch (java.text.ParseException e) {
			log.error(BaseExceptionEnum.DATE_TIME_PARSE_ERROR, e);
		}

		long diff = Math.abs(DateTimeUtil.compareSeconds(userDate, new Date()));
		// 判断超时
		if (diff > COOKIE_LIVE_TIME) {
			if (log.isDebugEnabled())
				log.debug(BOExceptionEnum.OUT_TIME_LOGIN.errorMsg());
			return null;
		}

		return BackUserPojo.create(map);
	}

	/**
	 * 
	 * @功能：把User对象转换成存入cookie中的字符串
	 * @param userStr
	 * @return
	 */
	private static String userToUserString(BackUserPojo user) {
		if (user == null) {
			if (log.isDebugEnabled())
				log.debug("后台用户转换出错");
			return null;
		}
		return ParameterUtil.toString(user.toMap());
	}
}
