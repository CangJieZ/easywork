package easy.work.source.module.rights.services.user;

import java.util.List;

import easy.work.source.commom.base.PageMode;
import easy.work.source.module.rights.pojo.BackUserPojo;
import easy.work.source.module.rights.pojo.MenuJs;
import easy.work.source.module.rights.pojo.UserRolePojo;
import easy.work.source.module.rights.query.RoleQuery;

public interface IUserService {

	/**
	 * @Auther: zhuwt
	 * @Description: 修改用户密码
	 * @Date:2017年5月30日下午10:18:52
	 * @param pojo
	 * @return
	 * @return boolean
	 */
	boolean updatePwd(BackUserPojo pojo);

	/**
	 * @Auther: chenjh
	 * @Description: 通过账号查询用户
	 * @Date:2017年5月12日下午2:14:32
	 * @param account
	 * @return
	 * @return User
	 */
	public BackUserPojo findByAccount(String account);

	/**
	 * @Auther: chenjh
	 * @Description: 初始化用户菜单
	 * @Date:2017年5月19日下午5:26:28
	 * @param uid
	 * @return void
	 */
	public List<MenuJs> initMenu(int uid);

	/**
	 * @Auther: zhuwt
	 * @Description:用户和角色关联查询
	 * @Date:2017年5月21日下午1:26:01
	 * @param query
	 * @return
	 * @return PageMode<BackUserPojo>
	 */
	PageMode<UserRolePojo> findByRole(RoleQuery query);

	/**
	 * @Auther: zhuwt
	 * @Description: 新增用户 关联角色
	 * @Date:2017年5月21日下午3:40:47
	 * @param userRolePojo
	 * @return
	 * @return boolean
	 */
	boolean addUserAndRole(UserRolePojo userRolePojo);

	/**
	 * @Auther: zhuwt
	 * @Description: 修改用户角色
	 * @Date:2017年5月21日下午8:09:35
	 * @param userRolePojo
	 * @return
	 * @return boolean
	 */
	boolean updateUserAndRole(UserRolePojo userRolePojo);

	/**
	 * @Auther: zhuwt
	 * @Description: 删除用户角色
	 * @Date:2017年5月21日下午8:49:23
	 * @param userRolePojo
	 * @return
	 * @return boolean
	 */
	boolean delUserAndRole(UserRolePojo userRolePojo);

	/**
	 * @Auther: chenjh
	 * @Description: 修改用户信息
	 * @Date:2017年5月25日下午1:33:02
	 * @param user
	 * @return void
	 */
	public void updateByPrimaryKeySelective(BackUserPojo user);

}
