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
import easy.work.source.module.rights.pojo.Permission;
import easy.work.source.module.rights.pojo.PermissionTree;
import easy.work.source.module.rights.query.PermissionQuery;
import easy.work.source.module.rights.services.permission.IPermissionService;

/**
 * 
 * @author zhuwt
 * @Description: 菜单 controller
 * @ClassName: PermissionController.java
 * @date 2017年5月12日 下午3:51:47
 */
@Controller
@RequestMapping("/permission")
@Scope("prototype")
public class PermissionController extends BaseController {

	@Autowired
	private IPermissionService iPermissionService;

	@Deprecated
	@RequestMapping(value = "/queryPmByUid")
	public @ResponseBody String permissionList(Integer uid) {
		List<Permission> list = iPermissionService.queryPermissionByUid(uid);
		return JSON.toJSONString(list);
	}

	/**
	 * @Auther: zhuwt
	 * @Description: 查询所有权限
	 * @Date:2017年5月23日上午1:07:21
	 * @param query
	 * @return
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/permissionList")
	public ModelAndView permissionList(PermissionQuery query) {
		ModelAndView mv = new ModelAndView();
		PageMode<Permission> pageMode = iPermissionService.queryAll(query);
		mv.addObject("permissionList", JSON.toJSON(pageMode.getList()));
		// 分页信息也返回
		query.setTotalNum(pageMode.getTotalPages());
		query.setCurrPage(pageMode.getCurrPage());
		mv.addObject("query", query);
		if (RETURN_AJAX_HTML.equals(ajax))
			mv.setViewName("rights/permission/permission_list");// ajax请求 就返回表格页面
		else
			mv.setViewName("rights/permission/permission_manage");
		return mv;
	}

	/**
	 * @Auther: zhuwt
	 * @Description: 添加菜单 or 更新菜单
	 * @Date:2017年5月17日下午4:53:57
	 * @param query
	 * @return
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/permissionAddorUpdate")
	public @ResponseBody GsonModel permissionAddorUpdate(Integer status, Permission permissionModel) {
		boolean result;
		if (status == 1)// 新增
			result = iPermissionService.insertPermission(permissionModel);
		else
			result = iPermissionService.updatePermission(permissionModel);
		return result ? getSuccessResultGson() : getFaildResultGson();
	}

	/**
	 * @Auther: zhuwt
	 * @Description: 跳转到添加或者更新 status 表示操作类型
	 * @Date:2017年5月19日上午11:32:08
	 * @param permissionModel
	 * @return
	 * @return GsonModel
	 */
	@RequestMapping(value = "/toAddorUpdate")
	public ModelAndView toAddorUpdate(Integer status) {
		mv.setViewName("rights/permission/permission_add");
		mv.addObject("status", status);
		return mv;
	}

	/**
	 * @Auther: zhuwt
	 * @Description: 打开权限的树型菜单
	 * @Date:2017年5月23日下午2:49:35
	 * @param status
	 * @return
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/toPermissionTree")
	public ModelAndView toPermissionTree(Integer rid) {
		List<PermissionTree> list = iPermissionService.queryPermissionTree();
		mv.setViewName("permission_tree");
		List<Integer> pids = iPermissionService.selectPidsByRidPer(rid);
		List<Integer> mids = iPermissionService.selectPidsByRidMenu(rid);
		mv.addObject("permissionTree", JSON.toJSONString(list));
		mv.addObject("pids", pids);
		mv.addObject("mids", mids);
		System.out.println(JSON.toJSONString(list));
		return mv;
	}

	@RequestMapping(value = "/permissionTreeTest")
	public @ResponseBody List<PermissionTree> permissionTreeTest() {
		List<PermissionTree> list = iPermissionService.queryPermissionTree();
		return list;
	}

	/**
	 * @Auther: zhuwt
	 * @Description: 权限删除
	 * @Date:2017年5月23日上午1:41:20
	 * @param mid
	 * @return
	 * @return GsonModel
	 */
	@RequestMapping(value = "/permissionDel")
	public @ResponseBody GsonModel permissionDel(Integer mid) {
		return iPermissionService.delete(mid) ? getSuccessResultGson() : getFaildResultGson();
	}

	/**
	 * @Auther: zhuwt
	 * @Description:根据角色id查询所有权限和菜单
	 * @Date:2017年5月26日下午5:26:06
	 * @param rid
	 * @return
	 * @return String
	 */
	@RequestMapping(value = "/permissionByUid")
	public @ResponseBody List<PermissionTree> permissionByUid(Integer rid) {
		return iPermissionService.queryPermissionTreeByRid(rid);
	}

	/**
	 * @Auther: zhuwt
	 * @Description: 批量删除
	 * @Date:2017年5月27日下午6:29:59
	 * @param ids
	 * @return
	 * @return GsonModel
	 */
	@RequestMapping(value = "/pidDelBatch")
	public @ResponseBody GsonModel menuDelBatch(Integer[] ids) {
		return iPermissionService.deleteBatchIds(ids) ? getSuccessResultGson() : getFaildResultGson();
	}

}
