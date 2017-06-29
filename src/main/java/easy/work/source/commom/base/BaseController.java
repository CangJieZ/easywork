
package easy.work.source.commom.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import easy.work.source.commom.Constants;
import easy.work.source.commom.context.SessionContext;
import easy.work.source.commom.utils.HttpRequestUtils;
import easy.work.source.module.user.pojo.BackUserPojo;

/**
 * @author chenjh
 * @Description: 控制器父类
 * @ClassName: BaseController.java
 * @date 2017年5月11日 下午1:43:24
 * @param <T>
 */
public class BaseController/* <T extends BaseModel> */ {
	private Log log = LogFactory.getLog(BaseController.class);

	// 后台当前登录用户
	private BackUserPojo backCurrentUser;

	protected HttpServletRequest request;

	protected HttpServletResponse response;

	protected HttpSession session;

	public String ajax;

	public final static String RETURN_AJAX_HTML = "ajax-html";
	public final static String RETURN_AJAX_JSON = "ajax-json";

	public ModelAndView mv = new ModelAndView();

	/**
	 * ModelAttribute 的作用 1)放置在方法的形参上：表示引用Model中的数据
	 * 2)放置在方法上面：表示请求该类的每个Action前都会首先执行它， 也可以将一些准备数据的操作放置在该方法里面。
	 * 
	 */
	@ModelAttribute
	public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.session = request.getSession();
		boolean isAjax = HttpRequestUtils.isAjaxRequest(request);
		setAjax(isAjax);
	}

	/**
	 * @Auther: chenjh
	 * @Description: 获取后台用户信息
	 * @Date:2017年5月15日下午2:33:54
	 * @return
	 * @return BackUserPojo
	 */
	public BackUserPojo getBackCurrentUser() {
		if (backCurrentUser == null) {
			backCurrentUser = SessionContext.getBackCurrentUser();
		}
		return backCurrentUser;
	}

	/**
	 * @Auther: chenjh
	 * @Description: 获取 ModelAndView
	 * @Date:2017年5月12日下午2:42:44
	 * @param msg
	 * @param viewName
	 * @return
	 * @return ModelAndView
	 */
	public ModelAndView getModelAndView(String msg, String viewName) {
		mv.addObject("message", msg);
		mv.setViewName(viewName);
		return mv;
	}

	/**
	 * @Auther: chenjh
	 * @Description: 重定向跳转
	 * @Date:2017年5月24日下午2:40:46
	 * @param redirect
	 * @return
	 * @return ModelAndView
	 */
	public ModelAndView getModelAndViewRedirect(String redirect) {
		return new ModelAndView(redirect);
	}

	/**
	 * @Auther: chenjh
	 * @Description:
	 * @Date:2017年5月15日下午1:48:41
	 * @param viewName
	 * @return
	 * @return ModelAndView
	 */
	public ModelAndView getModelAndView(String viewName) {
		mv.setViewName(viewName);
		return mv;
	}

	/**
	 * @Auther: chenjh
	 * @Description: 默认成功信息
	 * @Date:2017年5月12日下午3:53:27
	 * @return
	 * @return GsonModel
	 */
	public GsonModel getSuccessResultGson() {
		GsonModel gson = new GsonModel();
		gson.setCode(Constants.JSON_SUCCESS);
		gson.setMsg("操作成功");
		// gson.setContent(content);
		return gson;
	}

	/**
	 * @Auther: chenjh
	 * @Description: 失败返回
	 * @Date:2017年5月15日上午10:27:51
	 * @return
	 * @return GsonModel
	 */
	public GsonModel getFaildResultGson() {
		GsonModel gson = new GsonModel();
		gson.setCode(Constants.JSON_FAILD);
		gson.setMsg("操作失败");
		// gson.setContent(content);
		return gson;
	}

	/**
	 * @Auther: chenjh
	 * @Description: 自定义成功信息
	 * @Date:2017年5月12日下午3:53:36
	 * @param code
	 * @param msg
	 * @return
	 * @return GsonModel
	 */
	public GsonModel getSuccessResultGson(String code, String msg) {
		GsonModel gson = new GsonModel();
		gson.setCode(Constants.JSON_SUCCESS);
		gson.setMsg(msg);
		// gson.setContent(content);
		return gson;
	}

	/**
	 * @Auther: chenjh
	 * @Description: 自定义成功信息
	 * @Date:2017年5月12日下午3:53:36
	 * @param msg
	 * @return
	 * @return GsonModel
	 */
	public GsonModel getSuccessResultGson(String msg) {
		GsonModel gson = new GsonModel();
		gson.setCode(Constants.JSON_SUCCESS);
		gson.setMsg(msg);
		return gson;
	}

	/**
	 * @Auther: chenjh
	 * @Description: 自定义成功信息
	 * @Date:2017年5月12日下午3:53:36
	 * @param msg
	 * @return
	 * @return GsonModel
	 */
	public GsonModel getFaildResultGson(String msg) {
		GsonModel gson = new GsonModel();
		gson.setCode(Constants.JSON_FAILD);
		gson.setMsg(msg);
		return gson;
	}

	public String getAjax() {
		return ajax;
	}

	private void setAjax(boolean isAjax) {
		if (isAjax)
			this.ajax = "ajax-html";
		log.debug("请求方式 isAjax = " + ajax);
	}

}
