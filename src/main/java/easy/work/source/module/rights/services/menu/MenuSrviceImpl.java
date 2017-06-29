package easy.work.source.module.rights.services.menu;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;

import easy.work.source.commom.base.PageMode;
import easy.work.source.commom.exception.BaseRuntimeException;
import easy.work.source.commom.utils.PaginationUtils;
import easy.work.source.module.rights.dao.MenuDao;
import easy.work.source.module.rights.dao.MenuPermissionDao;
import easy.work.source.module.rights.pojo.BatchIds;
import easy.work.source.module.rights.pojo.Menu;
import easy.work.source.module.rights.pojo.MenuList;
import easy.work.source.module.rights.pojo.MenuTree;
import easy.work.source.module.rights.query.MenuPermissionQuery;

@Service("iMenuService")
public class MenuSrviceImpl implements IMenuService {

	@Autowired
	private MenuDao menuDao;
	
	@Autowired
	private MenuPermissionDao menuPermissionDao;

	/**
	 * 查询菜单和权限关联表
	 */
	@Override
	public PageMode<MenuList> queryMenuAll(MenuPermissionQuery query) {
		PageHelper.startPage(PaginationUtils.getCurrPage(), PaginationUtils.getPageSize());
		List<MenuList> list = menuDao.selectMenuList(query);
		return new PageMode<MenuList>(list);
	}

	/**
	 * 新增一条记录
	 */
	@Override
	public boolean insertMenu(Menu menuModel) {
		int result;
		try {
			result = menuDao.insertSelective(menuModel);
		} catch (Exception e) {
			throw new BaseRuntimeException("菜单新增出错");
		}
		return result < 0 ? false : true;
	}

	/**
	 * 删除数据
	 */
	@Override
	public boolean delete(Integer mid) {
		int result;
		try {
			result = menuDao.deleteByPrimaryKey(mid);
		} catch (Exception e) {
			throw new BaseRuntimeException("菜单删除出错");
		}
		return result < 0 ? false : true;
	}

	/**
	 * 修改数据
	 */
	@Override
	public boolean updateMenu(Menu menuModel) {
		int result;
		try {
			result = menuDao.updateByPrimaryKeySelective(menuModel);
		} catch (Exception e) {
			throw new BaseRuntimeException("菜单修改出错");
		}
		return result < 0 ? false : true;
	}

	/**
	 * 获取菜单列表
	 */
	@Override
	public List<MenuTree> queryMenuTree() {
		// 查询所有一级菜单
		List<Menu> oneTree = menuDao.selectMenuTreeSuperior();
		// 查询所有菜单
		List<Menu> list = menuDao.selectMenuTreeSubordinate();
		List<MenuTree> menuTrees = new ArrayList<MenuTree>();
		for (Menu menu : oneTree) {
			MenuTree menuTree = new MenuTree();
			menuTree.setMenuName(menu.getMenuName());
			menuTree.setMid(menu.getMid());
			menuTree.setParentId(menu.getParentId());
			menuTree.setParentName(menu.getParentName());
			menuTree = initMenuTree(menuTree, list);
			menuTrees.add(menuTree);
		}
		return menuTrees;
	}

	/**
	 * @Auther: zhuwt
	 * @Description: 获取菜单树型
	 * @Date:2017年5月26日下午4:15:22
	 * @param permissionTree
	 * @param list
	 * @return
	 * @return PermissionTree
	 */
	private MenuTree initMenuTree(MenuTree menuTree, List<Menu> list) {
		// 获取菜单ID
		// 如果PermissionTree下面没有菜单集合 那就拿当前的菜单ID 查询
		List<MenuTree> pTrees = menuTree.getSubordinate();
		if (null != pTrees && pTrees.size() > 0) {
			// 当前的菜单下面有子菜单 遍历子菜单
			for (MenuTree pTree : pTrees) {
				int mid = pTree.getMid();
				List<MenuTree> menuTrees = new ArrayList<MenuTree>();
				for (Menu menu : list) {
					// 判断当前的菜单是否在上级菜单下面
					if (menu.getParentId() == mid) {
						MenuTree p = new MenuTree();
						// 添加到菜单集合里面
						p.setMenuName(menu.getMenuName());
						p.setMid(menu.getMid());
						p.setParentId(menu.getParentId());
						p.setParentName(menu.getParentName());
						// 遍历子菜单
						initMenuTree(p, list);
						menuTrees.add(p);
					}
					if (menuTrees.size() != 0) {
						pTree.setSubordinate(menuTrees);
					}
				}
			}
			return menuTree;
		} else {
			// 直接查询当前菜单下的子菜单
			int mid = menuTree.getMid();
			List<MenuTree> menuTrees = new ArrayList<MenuTree>();
			for (Menu menu : list) {
				// 判断当前的菜单是否在上级菜单下面
				if (menu.getParentId() == mid) {
					MenuTree p = new MenuTree();
					// 添加到菜单集合里面
					p.setMenuName(menu.getMenuName());
					p.setMid(menu.getMid());
					p.setParentId(menu.getParentId());
					p.setParentName(menu.getParentName());
					menuTrees.add(p);
				}
			}
			if (menuTrees.size() == 0) {
				return menuTree;
			}
			menuTree.setSubordinate(menuTrees);
		}
		// 如果PermissionTree下面有菜单集合 就拿菜单集合遍历
		return initMenuTree(menuTree, list);
	}

	/**
	 * 批量删除
	 */
	@Transactional
	@Override
	public boolean deleteBatch(BatchIds ids) {
		int result = -1;
		try {
			//先删除菜单
			menuDao.deleteByMids(ids.getIds());
			//再删除关联表
			result = menuPermissionDao.deleteByMids(ids.getIds());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result == -1 ? false : true;
	}

}
