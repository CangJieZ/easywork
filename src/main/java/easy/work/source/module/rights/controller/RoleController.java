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
import easy.work.source.module.rights.pojo.PermissionTree;
import easy.work.source.module.rights.pojo.Role;
import easy.work.source.module.rights.query.RoleQuery;
import easy.work.source.module.rights.services.permission.IPermissionService;
import easy.work.source.module.rights.services.role.IRoleService;

/**
 * 
 * @author zhuwt
 * @Description: 角色列表
 * @ClassName: RoleController.java
 * @date 2017年5月20日 下午10:42:07
 */
@Controller
@RequestMapping("/role")
@Scope("prototype")
public class RoleController extends BaseController {

	@Autowired
	private IRoleService iRoleService;

	@Autowired
	private IPermissionService iPermissionService;

	/**
	 * @Auther: zhuwt
	 * @Description: 查询菜单和权限
	 * @Date:2017年5月12日下午3:53:32
	 * @param request
	 * @param response
	 * @return
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/roleList")
	public ModelAndView roleList(RoleQuery query) {
		ModelAndView mv = new ModelAndView();
		PageMode<Role> pageMode = iRoleService.selectAll(query);
		mv.addObject("roleList", JSON.toJSON(pageMode.getList()));
		// 分页信息也返回
		query.setTotalNum(pageMode.getTotalPages());
		query.setCurrPage(pageMode.getCurrPage());
		mv.addObject("query", query);
		if (RETURN_AJAX_HTML.equals(ajax))
			mv.setViewName("rights/role/role_list");// ajax请求 就返回表格页面
		else
			mv.setViewName("rights/role/role_manage");
		return mv;
	}

	/**
	 * @Auther: zhuwt
	 * @Description: 删除角色
	 * @Date:2017年5月20日下午10:52:08
	 * @param rid
	 * @return
	 * @return GsonModel
	 */
	@RequestMapping(value = "/roleDel")
	public @ResponseBody GsonModel roleDel(Integer rid) {
		return iRoleService.roleDel(rid) ? getSuccessResultGson() : getFaildResultGson();
	}

	/**
	 * @Auther: zhuwt
	 * @Description: 新增or 修改角色
	 * @Date:2017年5月20日下午10:52:08
	 * @param rid
	 * @return
	 * @return GsonModel
	 */
	@RequestMapping(value = "/roleAddorUpdate")
	public @ResponseBody GsonModel roleAddorUpdate(Role role, Integer status) {
		boolean result;
		if (status == 1)// 新增
			result = iRoleService.insertRole(role);
		else
			result = iRoleService.updateRole(role);
		return result ? getSuccessResultGson() : getFaildResultGson();
	}

	/**
	 * @Auther: zhuwt
	 * @Description: 打开新增修改界面
	 * @Date:2017年5月20日下午11:43:02
	 * @param status
	 * @return
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/toAddorUpdate")
	public ModelAndView toAddorUpdate(Integer status) {
		List<PermissionTree> list = iPermissionService.queryPermissionTree();
		mv.setViewName("rights/role/role_add");
		mv.addObject("status", status);
		mv.addObject("permissionTree", list);
		return mv;
	}
}
