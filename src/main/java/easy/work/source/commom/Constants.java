
package easy.work.source.commom;
/**
 * @author chenjh   
 * @Description: 全局常量放在这里
 * @ClassName: Constants.java   
 * @date 2017年5月11日 下午1:42:24
 */
public class Constants {

	public static final String INSERT_SUCCESS = "插入成功";
	public static final String FAIL_SUCCESS = "添加成功";

	public static final String PAGE_SIZE = "10";
	//ajax异步返回成功 
	public static final String JSON_SUCCESS="100000";
	//ajax异步返回失败
	public static final String JSON_FAILD="-100000";
	//session 过期
	public static final String SESSION_TIME_OUT="-100001";
	//session 过期
	public static final String SESSION_AJAX_TIME_OUT="-100002";
	
	// 没有操作权限 
	public static final String SESSION_AJAX_NO_RIGHT="-100003";

	// session过期时间(单位毫秒)(web.xml里的过期时间会被shiro使用，shiro过期后会直接跳转到jsp,没有经过controller)
	public static final long SESSION_OUT_TIME = 58 * 60 *1000;//比web.xml 里的少2分
	

	
	
	
	
}
