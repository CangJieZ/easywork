package easy.work.source.module.rights.dao;

import java.util.List;

import easy.work.source.module.rights.pojo.Role;
import easy.work.source.module.rights.query.RoleQuery;

public interface RoleDao {

	/**
	 * @Auther: zhuwt
	 * @Description: 查询角色列表
	 * @Date:2017年5月12日下午3:24:45
	 * @return
	 * @return List<MenuListModel>
	 */
	List<Role> selectAll(RoleQuery query);

	/**
	 * @Auther: zhuwt
	 * @Description: 删除角色
	 * @Date:2017年5月20日下午10:47:56
	 * @param rid
	 * @return
	 * @return int
	 */
	int deleteByPrimaryKey(Integer rid);

	/**
	 * @Auther: zhuwt
	 * @Description: 新增数据
	 * @Date:2017年5月20日下午10:55:51
	 * @param record
	 * @return
	 * @return int
	 */
	int insertSelective(Role record);

	/**
	 * @Auther: zhuwt
	 * @Description: 修改角色
	 * @Date:2017年5月20日下午10:59:32
	 * @param record
	 * @return
	 * @return int
	 */
	int updateByPrimaryKeySelective(Role record);

}