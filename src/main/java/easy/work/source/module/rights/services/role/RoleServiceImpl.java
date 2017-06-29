package easy.work.source.module.rights.services.role;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;

import easy.work.source.commom.base.PageMode;
import easy.work.source.commom.exception.BaseRuntimeException;
import easy.work.source.commom.utils.PaginationUtils;
import easy.work.source.module.rights.dao.RoleDao;
import easy.work.source.module.rights.dao.RolePremissionDao;
import easy.work.source.module.rights.dao.UserRoleDao;
import easy.work.source.module.rights.pojo.Role;
import easy.work.source.module.rights.pojo.RolePremission;
import easy.work.source.module.rights.query.RoleQuery;

@Service("iRoleService")
public class RoleServiceImpl implements IRoleService {

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private UserRoleDao userRoleDao;

	@Autowired
	private RolePremissionDao rolePremissionDao;

	/**
	 * 角色列表查询
	 */
	@Override
	public PageMode<Role> selectAll(RoleQuery query) {
		PageHelper.startPage(PaginationUtils.getCurrPage(), PaginationUtils.getPageSize());
		List<Role> list = roleDao.selectAll(query);
		return new PageMode<Role>(list);
	}

	/**
	 * 删除角色
	 */
	@Transactional
	@Override
	public boolean roleDel(Integer rid) {
		int result;
		try {
			// 删除角色
			roleDao.deleteByPrimaryKey(rid);
			// 删除用户和角色关联表
			userRoleDao.deleteByRid(rid);
			// 删除角色和权限关联表
			result = rolePremissionDao.deleteByRid(rid);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BaseRuntimeException("删除角色出错");
		}
		return result < 0 ? false : true;
	}

	/**
	 * 新增加角色
	 */
	@Transactional
	@Override
	public boolean insertRole(Role role) {
		int result;
		try {
			// 新增角色
			result = roleDao.insertSelective(role);
			// 新增角色和权限关联表
			List<Integer> permissions = role.getPermissions();
			int rid = role.getRid();
			if (permissions != null && permissions.size() > 0) {
				for (int i = 0; i < permissions.size(); i++) {
					RolePremission rolePremission = new RolePremission();
					// 判读是否为权限 >=100的都是权限 <100的是菜单
					int pid = permissions.get(i);
					if (pid >= 100) {
						// 1 是菜单 0 是权限
						rolePremission.setFlag(0);
						pid = pid / 100;
					} else {
						rolePremission.setFlag(1);
					}
					rolePremission.setPid(pid);
					rolePremission.setRid(rid);
					rolePremissionDao.insertSelective(rolePremission);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new BaseRuntimeException("新增角色出错");
		}
		return result < 0 ? false : true;
	}

	/**
	 * 修改角色
	 */
	@Transactional
	@Override
	public boolean updateRole(Role role) {
		int result;
		try {
			// 修改角色
			result = roleDao.updateByPrimaryKeySelective(role);
			// 先删除关联表数据
			int rid = role.getRid();
			rolePremissionDao.deleteByRid(rid);
			// 重新插入新数据
			List<Integer> permissions = role.getPermissions();
			if (permissions != null && permissions.size() > 0) {
				for (int i = 0; i < permissions.size(); i++) {
					RolePremission rolePremission = new RolePremission();
					// 判读是否为权限 >=100的都是权限 <100的是菜单
					int pid = permissions.get(i);
					if (pid >= 100) {
						// 1 是菜单 0 是权限
						rolePremission.setFlag(0);
						pid = pid / 100;
					} else {
						rolePremission.setFlag(1);
					}
					rolePremission.setPid(pid);
					rolePremission.setRid(rid);
					rolePremissionDao.insertSelective(rolePremission);
				}
			}
		} catch (Exception e) {
			throw new BaseRuntimeException("修改角色出错");
		}
		return result < 0 ? false : true;
	}

}
