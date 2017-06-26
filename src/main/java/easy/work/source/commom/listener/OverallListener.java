package easy.work.source.commom.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import easy.work.source.commom.server.AppServer;

/**
 * 
 * @author zhuwt
 * @Description: tomcat初始化的时候 初始化css地址以及缓存等
 * @ClassName: OverallListener.java
 * @date 2017年6月20日 下午1:51:19
 */
public class OverallListener implements ServletContextListener {

	private ServletContext servlet;

	private ApplicationContext applicationContext;

	// 这个方法在Web应用服务被移除，没有能力再接受请求的时候被调用。
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		servlet = null;
		applicationContext = null;
	}

	// 这个方法在Web应用服务做好接受请求的时候被调用。
	@Override
	public void contextInitialized(ServletContextEvent sc) {
		servlet = sc.getServletContext();
		applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(servlet);
		// 初始化全局上下文
		AppServer appServer = (AppServer) applicationContext.getBean("appServer");
		servlet.setAttribute("appServer", appServer.bulidImageServerPath());
	}


}
