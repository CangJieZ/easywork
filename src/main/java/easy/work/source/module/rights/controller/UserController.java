package easy.work.source.module.rights.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

import easy.work.source.commom.base.BaseController;
import easy.work.source.commom.base.GsonModel;
import easy.work.source.commom.base.PageMode;
import easy.work.source.module.rights.pojo.MenuJs;
import easy.work.source.module.rights.pojo.Role;
import easy.work.source.module.rights.pojo.UserRolePojo;
import easy.work.source.module.rights.query.RoleQuery;
import easy.work.source.module.rights.services.role.IRoleService;
import easy.work.source.module.rights.services.user.IUserService;

/**
 * 
 * @author zhuwt
 * @Description: 角色和用户关联列表
 * @ClassName: RoleController.java
 * @date 2017年5月20日 下午10:42:07
 */
@Controller
@RequestMapping("/user")
@Scope("prototype")
public class UserController extends BaseController {

	@Autowired
	private IUserService iUserService;

	@Autowired
	private IRoleService iRoleService;

	/**
	 * @Auther: zhuwt
	 * @Description: 查询角色和用户关联表
	 * @Date:2017年5月21日下午1:32:29
	 * @param query
	 * @return
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/manageList")
	public ModelAndView roleUserList(RoleQuery query) {
		ModelAndView mv = new ModelAndView();
		PageMode<UserRolePojo> pageMode = iUserService.findByRole(query);
		mv.addObject("manageList", JSON.toJSON(pageMode.getList()));
		// 分页信息也返回
		query.setTotalNum(pageMode.getTotalPages());
		query.setCurrPage(pageMode.getCurrPage());
		mv.addObject("query", query);
		if (RETURN_AJAX_HTML.equals(ajax))
			mv.setViewName("rights/manage/manage_list");// ajax请求 就返回表格页面
		else
			mv.setViewName("rights/manage/manage_manage");
		return mv;
	}

	/**
	 * @Auther: zhuwt
	 * @Description:跳转倒新增or修改界面 返回所有角色
	 * @Date:2017年5月21日下午3:42:32
	 * @param status
	 * @param query
	 * @return
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/toAddOrUpdate")
	public ModelAndView toAddOrUpdate(Integer status, RoleQuery query) {
		ModelAndView mv = new ModelAndView();
		List<Role> list = iRoleService.selectAll(null).getList();
		mv.addObject("roleList", JSON.toJSON(list));
		mv.addObject("status", status);
		mv.addObject("query", query);
		mv.setViewName("rights/manage/manage_add");
		return mv;
	}

	/**
	 * @Auther: zhuwt
	 * @Description: 新增or修改用户数据 关联角色表
	 * @Date:2017年5月21日下午4:00:04
	 * @param status
	 * @param menuModel
	 * @return
	 * @return GsonModel
	 */
	@RequestMapping(value = "/userAddOrUpdate")
	public @ResponseBody GsonModel userAddorUpdate(Integer status, UserRolePojo userRolePojo) {
		boolean result = false;
		if (1 == status)// 新增
			result = iUserService.addUserAndRole(userRolePojo);
		if (3 == status)// 修改
			result = iUserService.updateUserAndRole(userRolePojo);
		return result ? getSuccessResultGson() : getFaildResultGson();
	}

	/**
	 * @Auther: zhuwt
	 * @Description: 删除用户
	 * @Date:2017年5月21日下午8:57:05
	 * @param status
	 * @param userRolePojo
	 * @return
	 * @return GsonModel
	 */
	@RequestMapping(value = "/delUserRole")
	public @ResponseBody GsonModel delUserRole(UserRolePojo userRolePojo) {
		return iUserService.delUserAndRole(userRolePojo) ? getSuccessResultGson() : getFaildResultGson();
	}

	/**
	 * @Auther: zhuwt
	 * @Description: 修改用户密码
	 * @Date:2017年5月30日下午10:20:45
	 * @param userRolePojo
	 * @return
	 * @return GsonModel
	 */
	@RequestMapping(value = "/updatePwd")
	public @ResponseBody GsonModel updatePwd(UserRolePojo userRolePojo) {
		return iUserService.updatePwd(userRolePojo) ? getSuccessResultGson() : getFaildResultGson();
	}

	@RequestMapping(value = "/initMenuTest")
	public @ResponseBody List<MenuJs> initMenuTest(Integer rid) {
		return iUserService.initMenu(rid);
	}
}
