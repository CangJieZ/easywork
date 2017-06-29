package easy.work.source.module.rights.services.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;

import easy.work.source.commom.base.PageMode;
import easy.work.source.commom.server.AppServer;
import easy.work.source.commom.utils.PaginationUtils;
import easy.work.source.module.rights.dao.UserDao;
import easy.work.source.module.rights.dao.UserRoleDao;
import easy.work.source.module.rights.pojo.BackUserPojo;
import easy.work.source.module.rights.pojo.MenuJs;
import easy.work.source.module.rights.pojo.Permission;
import easy.work.source.module.rights.pojo.PermissionTree;
import easy.work.source.module.rights.pojo.UserRole;
import easy.work.source.module.rights.pojo.UserRolePojo;
import easy.work.source.module.rights.query.RoleQuery;
import easy.work.source.module.rights.services.permission.IPermissionService;

/**
 * @author chenjh
 * @Description: 用户service
 * @ClassName: UserServiceImpl.java
 * @date 2017年5月12日 下午2:12:31
 */
@Service("iUserService")
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserDao userMapperDao;

	@Autowired
	private UserRoleDao userRoleDao;

	@Autowired
	private IPermissionService iPermissionService;

	/**
	 * @Auther: chenjh
	 * @Description: 通过账号查询用户
	 * @Date:2017年5月12日下午2:14:32
	 * @param account
	 * @return
	 * @return User
	 */
	@Override
	public BackUserPojo findByAccount(String account) {
		return userMapperDao.findByAccount(account);
	}

	/**
	 * 用户和角色关联查询
	 */
	@Override
	public PageMode<UserRolePojo> findByRole(RoleQuery query) {
		PageHelper.startPage(PaginationUtils.getCurrPage(), PaginationUtils.getPageSize());
		List<UserRolePojo> list = userMapperDao.selectFromRole(query);
		return new PageMode<UserRolePojo>(list);
	}

	/**
	 * 新增用户 添加角色
	 */
	@Transactional
	@Override
	public boolean addUserAndRole(UserRolePojo userRolePojo) {
		int result = -1;
		try {
			if (null != userRolePojo) {
				// 先插入用户的数据
				BackUserPojo backUserPojo = userRolePojo;
				userMapperDao.insertSelective(backUserPojo);
				// 插入用户和角色关联表 不能用insertSelective返回的uid
				UserRole userRole = new UserRole();
				userRole.setUid(backUserPojo.getUid());
				userRole.setRid(userRolePojo.getRid());
				result = userRoleDao.insertSelective(userRole);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("用户新增数据出错");
		}
		return result < 0 ? false : true;
	}

	/**
	 * 修改用户角色
	 */
	@Transactional
	@Override
	public boolean updateUserAndRole(UserRolePojo userRolePojo) {
		int result = -1;
		try {
			if (null != userRolePojo) {
				// 修改角色信息
				BackUserPojo backUserPojo = userRolePojo;
				result = userMapperDao.updateByPrimaryKeySelective(backUserPojo);
				// 修改关联表
				UserRole role = new UserRole();
				role.setRid(userRolePojo.getRid());
				role.setUid(userRolePojo.getUid());
				result = userRoleDao.updateByUid(role);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("用户修改数据出错");
		}
		return result < 0 ? false : true;
	}

	/**
	 * 删除用户
	 */
	@Transactional
	@Override
	public boolean delUserAndRole(UserRolePojo userRolePojo) {
		int result = -1;
		int uid = 0;
		try {
			if (null != userRolePojo) {
				uid = userRolePojo.getUid();
				// 删除用户
				userMapperDao.deleteByPrimaryKey(uid);
				// 删除关联表
				result = userRoleDao.deleteByUid(uid);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("用户删除数据出错,uid= " + uid);
		}
		return result < 0 ? false : true;
	}

	/**
	 * @Auther: chenjh
	 * @Description: 初始化用户菜单
	 * @Date:2017年5月19日下午5:26:28
	 * @param uid
	 * @return void
	 */
	@Override
	public List<MenuJs> initMenu(int rid) {
		List<MenuJs> menuList = new ArrayList<>();

		List<PermissionTree> allPerList = iPermissionService.queryPermissionTreeByRid(rid);
		for (PermissionTree permissionTree : allPerList) {
			MenuJs menu = new MenuJs();
			menu.setIsHeader("1");// 1为显示在最上面 ，0为菜单
			menu.setParentId(0);
			menu.setId(permissionTree.getMid());
			menu.setName(permissionTree.getMenuName());
			initMenu(menu, permissionTree.getSubordinate());
			menuList.add(menu);
		}
		return menuList;
	}

	/**
	 * @Auther: zhuwt
	 * @Description: 初始化菜单
	 * @Date:2017年5月27日上午10:30:57
	 * @param menuJs
	 * @param permissionTrees
	 * @return
	 * @return MenuJs
	 */
	private MenuJs initMenu(MenuJs menuJs, List<PermissionTree> permissionTrees) {
		List<MenuJs> menuTrees = new ArrayList<MenuJs>();
		if (null != permissionTrees && permissionTrees.size() > 0) {
			for (PermissionTree permissionTree : permissionTrees) {
				MenuJs menu = new MenuJs();
				menu.setIcon("&#xe604");
				menu.setId(permissionTree.getMid());
				menu.setParentId(permissionTree.getParentId());
				menu.setName(permissionTree.getMenuName());
				List<Permission> permissions = permissionTree.getPermissions();
				if (null != permissions && permissions.size() > 0) {
					menu.setUrl(AppServer.serverPath + permissions.get(0).getPermissionUrl());
				}
				initMenu(menu, permissionTree.getSubordinate());
				menuTrees.add(menu);
			}
			menuJs.setChildMenus(menuTrees);
		}
		return menuJs;
	}

	/**
	 * @Auther: chenjh
	 * @Description: 修改用户信息
	 * @Date:2017年5月25日下午1:33:02
	 * @param user
	 * @return void
	 */
	@Override
	public void updateByPrimaryKeySelective(BackUserPojo user) {
		userMapperDao.updateByPrimaryKeySelective(user);
	}

	/**
	 * 修改用户密码
	 */
	@Override
	public boolean updatePwd(BackUserPojo pojo) {
		int result = -1;
		try {
			result = userMapperDao.updateByPwd(pojo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result == -1 ? false : true;
	}

}
