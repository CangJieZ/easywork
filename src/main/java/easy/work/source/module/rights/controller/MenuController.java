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
import easy.work.source.module.rights.pojo.BatchIds;
import easy.work.source.module.rights.pojo.Menu;
import easy.work.source.module.rights.pojo.MenuList;
import easy.work.source.module.rights.pojo.MenuTree;
import easy.work.source.module.rights.query.MenuPermissionQuery;
import easy.work.source.module.rights.services.menu.IMenuService;

/**
 * 
 * @author zhuwt
 * @Description: 菜单 controller
 * @ClassName: MenuController.java
 * @date 2017年5月12日 下午3:51:47
 */
@Controller
@RequestMapping("/menu")
@Scope("prototype")
public class MenuController extends BaseController {

	@Autowired
	private IMenuService iMenuService;

	/**
	 * @Auther: zhuwt
	 * @Description: 查询菜单和权限
	 * @Date:2017年5月12日下午3:53:32
	 * @param request
	 * @param response
	 * @return
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/menuList")
	public ModelAndView menuList(MenuPermissionQuery query) {
		ModelAndView mv = new ModelAndView();
		PageMode<MenuList> pageMode = iMenuService.queryMenuAll(query);
		mv.addObject("menuList", JSON.toJSON(pageMode.getList()));
		// 分页信息也返回
		query.setTotalNum(pageMode.getTotalPages());
		query.setCurrPage(pageMode.getCurrPage());
		mv.addObject("query", query);
		if (RETURN_AJAX_HTML.equals(ajax))
			mv.setViewName("rights/menu/menu_list");// ajax请求 就返回表格页面
		else
			mv.setViewName("rights/menu/menu_permission_manage");
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
	@RequestMapping(value = "/menuAddorUpdate")
	public @ResponseBody GsonModel menuAddorUpdate(Integer status, Menu menuModel) {
		boolean result;
		if (status == 1)// 新增
			result = iMenuService.insertMenu(menuModel);
		else
			result = iMenuService.updateMenu(menuModel);
		return result ? getSuccessResultGson() : getFaildResultGson();
	}

	/**
	 * @Auther: zhuwt
	 * @Description: 跳转到添加或者更新 status 表示操作类型
	 * @Date:2017年5月19日上午11:32:08
	 * @param menuModel
	 * @return
	 * @return GsonModel
	 */
	@RequestMapping(value = "/toAddorUpdate")
	public ModelAndView toAddorUpdate(Integer status) {
		mv.addObject("status", status);
		mv.setViewName("rights/menu/menu_permission_add");
		return mv;
	}

	/**
	 * @Auther: zhuwt
	 * @Description: 显示menu的树型菜单
	 * @Date:2017年5月21日下午10:32:25
	 * @param status
	 * @return
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/menuTree")
	public ModelAndView menuTree(Integer status) {
		List<MenuTree> list = iMenuService.queryMenuTree();
		mv.setViewName("rights/menu/menu_permission_tree");
		System.out.println(JSON.toJSONString(list));
		mv.addObject("menuTree", JSON.toJSONString(list));
		// 1 menu_add 调用 2 permissio_add调用
		mv.addObject("status", status);
		return mv;
	}

	@RequestMapping(value = "/menuTreeTest")
	public @ResponseBody List<MenuTree> menuTreeTest() {
		List<MenuTree> list = iMenuService.queryMenuTree();
		System.out.println(JSON.toJSONString(list));
		return list;
	}

	/**
	 * @Auther: zhuwt
	 * @Description: 菜单删除
	 * @Date:2017年5月19日上午10:30:28
	 * @param menuModel
	 * @return
	 * @return GsonModel
	 */
	@RequestMapping(value = "/menuDel")
	public @ResponseBody GsonModel menuDel(Integer mid) {
		return iMenuService.delete(mid) ? getSuccessResultGson() : getFaildResultGson();
	}

	/**
	 * @Auther: zhuwt
	 * @Description: 批量删除菜单
	 * @Date:2017年5月27日下午4:35:15
	 * @param mid
	 * @return
	 * @return GsonModel
	 */
	@RequestMapping(value = "/menuDelBatch")
	public @ResponseBody GsonModel menuDelBatch(BatchIds ids) {
		return iMenuService.deleteBatch(ids) ? getSuccessResultGson() : getFaildResultGson();
	}
}
