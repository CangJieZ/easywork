package easy.work.source.commom.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import easy.work.source.commom.Constants;


/**
 * @author chenjh   
 * @Description: 全局异常处理类 
 * @ClassName: AppHandlerExceptionResolver.java   
 * @date 2017年5月11日 下午1:37:51
 */
public class AppHandlerExceptionResolver extends DefaultHandlerExceptionResolver {

	private Log log = LogFactory.getLog(AppHandlerExceptionResolver.class);

	@Override
	protected ModelAndView doResolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		// 异常编码
		String code = "-100000";
		// 提示内容
		String message = "";
		// 判断是否为系统自定义异常。
		if (ex instanceof BaseRuntimeException) {
			// 全部抛出来的异常只有异常代码(除了系统异常)
			code = ((BaseRuntimeException) ex).getMessage();
			message = "测试异常123";//InterfaceCodeMsg.mapCode.get(code);
		} else if (ex instanceof MissingServletRequestParameterException) {// 字段校验异常（系统异常）-暂时不起作用
			String exmsg = ((MissingServletRequestParameterException) ex)
					.getMessage();
			String param = exmsg.substring(exmsg.indexOf(" '") + 2,
					exmsg.lastIndexOf("' "));
			message = param + " 参数不能为空!";
		}else {
			message = "系统出错啦，稍后再试试！";
		}

		// 捕获到的异常写入日志
		log.error("code= " + code + " ; msg=" + message, ex);

		ModelAndView mv = new ModelAndView();
		if(Constants.SESSION_TIME_OUT.equals(code)){//超时跳转到登录页面
			mv.setViewName("redirect:/login/loginout.action?outmsg="+System.currentTimeMillis());
		}/*else if(Constants.SESSION_AJAX_TIME_OUT.equals(code)){//ajax请求超时异常
			// 使用FastJson提供的FastJsonJsonView视图返回，可以返回json数据/
			FastJsonJsonView view = new FastJsonJsonView();
			Map<String, Object> attributes = new HashMap<String, Object>();
			attributes.put("code", code);
			attributes.put("message", "登录超时，请重新登录！");
//			attributes.put("content", "");
			view.setAttributesMap(attributes);
			mv.setView(view);
		}*/else{
			mv.addObject("message", message);
			mv.setViewName("login");
		}
		
		return mv;
	}

}
