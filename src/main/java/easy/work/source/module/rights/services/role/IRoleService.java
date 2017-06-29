package easy.work.source.module.rights.services.role;

import easy.work.source.commom.base.PageMode;
import easy.work.source.module.rights.pojo.Role;
import easy.work.source.module.rights.query.RoleQuery;

public interface IRoleService {

	/**
	 * @Auther: zhuwt
	 * @Description: 查询角色列表
	 * @Date:2017年5月12日下午3:24:45
	 * @return
	 * @return List<MenuListModel>
	 */
	PageMode<Role> selectAll(RoleQuery query);

	/**
	 * @Auther: zhuwt
	 * @Description:删除角色
	 * @Date:2017年5月20日下午10:48:35
	 * @param rid
	 * @return
	 * @return boolean
	 */
	boolean roleDel(Integer rid);

	/**
	 * @Auther: zhuwt
	 * @Description: 新增
	 * @Date:2017年5月20日下午10:56:18
	 * @param role
	 * @return
	 * @return boolean
	 */
	boolean insertRole(Role role);
	
	/**
	 * @Auther: zhuwt
	 * @Description: 修改
	 * @Date:2017年5月20日下午10:56:18
	 * @param role
	 * @return
	 * @return boolean
	 */
	boolean updateRole(Role role);
}
