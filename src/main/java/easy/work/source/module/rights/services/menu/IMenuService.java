package easy.work.source.module.rights.services.menu;

import java.util.List;

import easy.work.source.commom.base.PageMode;
import easy.work.source.module.rights.pojo.BatchIds;
import easy.work.source.module.rights.pojo.Menu;
import easy.work.source.module.rights.pojo.MenuList;
import easy.work.source.module.rights.pojo.MenuTree;
import easy.work.source.module.rights.query.MenuPermissionQuery;

public interface IMenuService {

	/**
	 * @Auther: zhuwt
	 * @Description: 查询菜单和权限关联表
	 * @Date:2017年5月12日下午3:28:33
	 * @return
	 * @return List<MenuListModel>
	 */
	PageMode<MenuList> queryMenuAll(MenuPermissionQuery query);

	/**
	 * @Auther: zhuwt
	 * @Description: 插入一条记录
	 * @Date:2017年5月17日下午4:59:43
	 * @param menuModel
	 * @return void
	 */
	boolean insertMenu(Menu menuModel);

	/**
	 * @Auther: zhuwt
	 * @Description: 更新数据
	 * @Date:2017年5月17日下午4:59:43
	 * @param menuModel
	 * @return void
	 */
	boolean updateMenu(Menu menuModel);

	/**
	 * @Auther: zhuwt
	 * @Description:删除数据
	 * @Date:2017年5月19日上午10:25:31
	 * @param mid
	 * @return void
	 */
	boolean delete(Integer mid);

	/**
	 * @Auther: zhuwt
	 * @Description: 批量删除数据
	 * @Date:2017年5月27日下午4:32:52
	 * @param mids
	 * @return
	 * @return boolean
	 */
	boolean deleteBatch(BatchIds ids);

	/**
	 * @Auther: zhuwt
	 * @Description: 查询每隔menu下的所有列表
	 * @Date:2017年5月21日下午10:30:24
	 * @param query
	 * @return
	 * @return List<MenuCatalogTree>
	 */
	List<MenuTree> queryMenuTree();

}
