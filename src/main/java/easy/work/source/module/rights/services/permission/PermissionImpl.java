package easy.work.source.module.rights.services.permission;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;

import easy.work.source.commom.base.PageMode;
import easy.work.source.commom.utils.PaginationUtils;
import easy.work.source.module.rights.dao.MenuDao;
import easy.work.source.module.rights.dao.MenuPermissionDao;
import easy.work.source.module.rights.dao.PermissionDao;
import easy.work.source.module.rights.dao.RolePremissionDao;
import easy.work.source.module.rights.pojo.Menu;
import easy.work.source.module.rights.pojo.MenuPermission;
import easy.work.source.module.rights.pojo.Permission;
import easy.work.source.module.rights.pojo.PermissionTree;
import easy.work.source.module.rights.query.PermissionQuery;

@Service("iPermissionService")
public class PermissionImpl implements IPermissionService {

	@Autowired
	private PermissionDao permissionDao;

	@Autowired
	private MenuPermissionDao menuPermissionDao;

	@Autowired
	private MenuDao menuDao;

	@Autowired
	private RolePremissionDao rolePremissionDao;

	/**
	 * 查询用户的权限
	 */
	@Override
	@Deprecated
	public List<Permission> queryPermissionByUid(Integer uid) {
		List<Permission> list = permissionDao.selectByUserId(uid);
		return list;
	}

	/**
	 * 查询所有权限
	 */
	@Override
	public PageMode<Permission> queryAll(PermissionQuery query) {
		PageHelper.startPage(PaginationUtils.getCurrPage(), PaginationUtils.getPageSize());
		List<Permission> list = permissionDao.selectAll(query);
		return new PageMode<Permission>(list);
	}

	/**
	 * 新增权限
	 */
	@Transactional
	@Override
	public boolean insertPermission(Permission permission) {
		int result;
		try {
			// 新增权限
			permissionDao.insertSelective(permission);
			// 插入权限与菜单表
			MenuPermission menuPermission = new MenuPermission();
			menuPermission.setMid(permission.getMid());
			menuPermission.setPid(permission.getPid());
			result = menuPermissionDao.insertSelective(menuPermission);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("新增权限出错");
		}
		return result < 0 ? false : true;
	}

	/**
	 * 修改权限
	 */
	@Transactional
	@Override
	public boolean updatePermission(Permission permission) {
		int result;
		try {
			// 修改权限
			permissionDao.updateByPrimaryKeySelective(permission);
			// 先删除 后添加
			MenuPermission menuPermission = new MenuPermission();
			menuPermission.setMid(permission.getMid());
			menuPermission.setPid(permission.getPid());
			result = menuPermissionDao.updateByPid(menuPermission);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("修改权限出错");
		}
		return result < 0 ? false : true;
	}

	/**
	 * 删除权限
	 */
	@Transactional
	@Override
	public boolean delete(Integer pid) {
		int result;
		try {
			// 删除权限
			permissionDao.deleteByPrimaryKey(pid);
			// 删除关联表
			result = menuPermissionDao.deleteByPid(pid);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("修改权限出错");
		}
		return result < 0 ? false : true;
	}

	/**
	 * 权限的树型菜单
	 */
	@Override
	public List<PermissionTree> queryPermissionTree() {
		// 查询所有一级菜单
		List<Menu> oneTree = menuDao.selectMenuTreeSuperior();
		// 查询所有菜单
		List<Menu> menuTrees = menuDao.selectMenuTreeSubordinate();
		List<PermissionTree> permissionTrees = new ArrayList<PermissionTree>();
		for (Menu menu : oneTree) {
			PermissionTree permissionTree = new PermissionTree();
			permissionTree.setMenuName(menu.getMenuName());
			permissionTree.setMid(menu.getMid());
			permissionTree.setParentId(menu.getParentId());
			permissionTree.setParentName(menu.getParentName());
			permissionTree = initPermissionTree(permissionTree, menuTrees);
			permissionTrees.add(permissionTree);
		}
		return permissionTrees;
	}

	/**
	 * @Auther: zhuwt
	 * @Description: 权限的树型菜单
	 * @Date:2017年5月23日下午2:01:33
	 * @return
	 * @return List<MenuCatalogTree>
	 */
	private PermissionTree initPermissionTree(PermissionTree permissionTree, List<Menu> list) {
		// 获取菜单ID
		// 如果PermissionTree下面没有菜单集合 那就拿当前的菜单ID 查询
		List<PermissionTree> pTrees = permissionTree.getSubordinate();
		if (null != pTrees && pTrees.size() > 0) {
			// 当前的菜单下面有子菜单 遍历子菜单
			for (PermissionTree pTree : pTrees) {
				int mid = pTree.getMid();
				List<PermissionTree> permissionTrees = new ArrayList<PermissionTree>();
				for (Menu menu : list) {
					// 判断当前的菜单是否在上级菜单下面
					if (menu.getParentId() == mid) {
						PermissionTree p = new PermissionTree();
						// 添加到菜单集合里面
						p.setMenuName(menu.getMenuName());
						p.setMid(menu.getMid());
						p.setParentId(menu.getParentId());
						p.setParentName(menu.getParentName());
						// 查询菜单否有权限
						List<Permission> perList = permissionDao.selectByMenuId(menu.getMid());
						p.setPermissions(perList);
						// 遍历子菜单
						initPermissionTree(p, list);
						permissionTrees.add(p);
					}
					if (permissionTrees.size() != 0) {
						pTree.setSubordinate(permissionTrees);
					}
				}
			}
			return permissionTree;
		} else {
			// 直接查询当前菜单下的子菜单
			int mid = permissionTree.getMid();
			List<PermissionTree> permissionTrees = new ArrayList<PermissionTree>();
			for (Menu menu : list) {
				// 判断当前的菜单是否在上级菜单下面
				if (menu.getParentId() == mid) {
					PermissionTree p = new PermissionTree();
					// 添加到菜单集合里面
					p.setMenuName(menu.getMenuName());
					p.setMid(menu.getMid());
					p.setParentId(menu.getParentId());
					p.setParentName(menu.getParentName());
					// 查询菜单否有权限
					List<Permission> perList = permissionDao.selectByMenuId(menu.getMid());
					p.setPermissions(perList);
					permissionTrees.add(p);
				}
			}
			if (permissionTrees.size() == 0) {
				return permissionTree;
			}
			permissionTree.setSubordinate(permissionTrees);
		}
		// 如果PermissionTree下面有菜单集合 就拿菜单集合遍历
		return initPermissionTree(permissionTree, list);
	}

	/**
	 * 查询用户的所有权限和菜单详情
	 */
	@Override
	public List<PermissionTree> queryPermissionTreeByRid(Integer rid) {
		// 查询所有一级菜单
		List<Menu> oneTree = menuDao.selectMenuTreeSuperior();
		// 查询所有菜单
		List<Menu> menuTrees = menuDao.selectMenuTreeSubordinate();
		List<PermissionTree> permissionTrees = new ArrayList<PermissionTree>();
		for (Menu menu : oneTree) {
			PermissionTree permissionTree = new PermissionTree();
			permissionTree.setMenuName(menu.getMenuName());
			permissionTree.setMid(menu.getMid());
			permissionTree.setParentId(menu.getParentId());
			permissionTree.setParentName(menu.getParentName());
			permissionTree = initPermissionTreeByRid(permissionTree, menuTrees, rid);
			permissionTrees.add(permissionTree);
		}
		return permissionTrees;
	}

	/**
	 * @Auther: zhuwt
	 * @Description: 查询用户下的所有权限和菜单
	 * @Date:2017年5月26日下午4:56:30
	 * @param permissionTree
	 * @param list
	 * @param permissions
	 * @return
	 * @return PermissionTree
	 */
	private PermissionTree initPermissionTreeByRid(PermissionTree permissionTree, List<Menu> list, int rid) {
		// 获取菜单ID
		// 如果PermissionTree下面没有菜单集合 那就拿当前的菜单ID 查询
		List<PermissionTree> pTrees = permissionTree.getSubordinate();
		if (null != pTrees && pTrees.size() > 0) {
			// 当前的菜单下面有子菜单 遍历子菜单
			for (PermissionTree pTree : pTrees) {
				int mid = pTree.getMid();
				List<PermissionTree> permissionTrees = new ArrayList<PermissionTree>();
				for (Menu menu : list) {
					// 判断当前的菜单是否在上级菜单下面
					if (menu.getParentId() == mid) {
						// 查询这个菜单下面的用户权限
						List<Permission> perList = permissionDao.selectPermissionByRidAndMid(rid, menu.getMid());
						// 如果没有权限 说明这个菜单不在该角色下
						if (perList.size() <= 0) {
							continue;
						}
						PermissionTree p = new PermissionTree();
						// 添加到菜单集合里面
						p.setMenuName(menu.getMenuName());
						p.setMid(menu.getMid());
						p.setParentId(menu.getParentId());
						p.setParentName(menu.getParentName());
						p.setPermissions(perList);
						// 遍历子菜单
						initPermissionTree(p, list);
						permissionTrees.add(p);
					}
					if (permissionTrees.size() != 0) {
						pTree.setSubordinate(permissionTrees);
					}
				}
			}
			return permissionTree;
		} else {
			// 直接查询当前菜单下的子菜单
			int mid = permissionTree.getMid();
			List<PermissionTree> permissionTrees = new ArrayList<PermissionTree>();
			for (Menu menu : list) {
				// 判断当前的菜单是否在上级菜单下面
				if (menu.getParentId() == mid) {
					// 查询这个菜单下面的用户权限和菜单
					// 因为在角色选择权限时，讲权限的所属菜单也添加到了 role_permission表 所有这里只要查询关联表的数据
					// 其中菜单的mid 在关联表里变成了pid 只要有数据 说明当前上级菜单下面的子菜单有分配过权限
					// 需要加到数据里
					int count = rolePremissionDao.selectByRidAndPid(rid, menu.getMid());
					// 如果没有 说明这个菜单不在该角色下
					if (count <= 0) {
						continue;
					}
					PermissionTree p = new PermissionTree();
					// 添加到菜单集合里面
					p.setMenuName(menu.getMenuName());
					p.setMid(menu.getMid());
					p.setParentId(menu.getParentId());
					p.setParentName(menu.getParentName());
					// 如果有 则查询权限 满足二级菜单可添加权限
					List<Permission> perList = permissionDao.selectPermissionByRidAndMid(rid, menu.getMid());
					if (perList.size() > 0) {
						p.setPermissions(perList);
					}
					permissionTrees.add(p);
				}
			}
			if (permissionTrees.size() == 0) {
				return permissionTree;
			}
			permissionTree.setSubordinate(permissionTrees);
		}
		// 如果PermissionTree下面有菜单集合 就拿菜单集合遍历
		return initPermissionTreeByRid(permissionTree, list, rid);
	}

	/**
	 * 批量删除
	 */
	@Transactional
	@Override
	public boolean deleteBatchIds(Integer[] ids) {
		int result = -1;
		try {
			// 先删除菜单
			permissionDao.deleteByIds(ids);
			// 再删除关联表
			result = menuPermissionDao.deleteByPids(ids);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result == -1 ? false : true;
	}

	@Override
	public List<Integer> selectPidsByRidMenu(Integer rid) {
		return rolePremissionDao.selectPidsByRidMenu(rid);
	}

	@Override
	public List<Integer> selectPidsByRidPer(Integer rid) {
		return rolePremissionDao.selectPidsByRidPer(rid);
	}

	/**
	 * @Auther: zhuwt
	 * @Description:Role rid 查询
	 * @Date:2017年5月24日上午10:42:28
	 * @param query
	 * @return
	 * @return List<Permission>
	 */
	@Override
	public List<Permission> selectByRid(Integer rid) {
		return permissionDao.selectByRid(rid);
	}

}
