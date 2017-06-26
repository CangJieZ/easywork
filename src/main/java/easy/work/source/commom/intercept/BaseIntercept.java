package easy.work.source.commom.intercept;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class BaseIntercept implements HandlerInterceptor{

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse arg1, Object arg2) throws Exception {
		System.out.println(" InterfaceInterceptor 拦截到的url = "+request.getRequestURI());
		//request 里可以获取参数
		String url = request.getRequestURI();
		//参数""字符串判断
		Map<String, String[]> map = request.getParameterMap();
		System.out.println(url+"\n"+map);
		return true;
	}

}
