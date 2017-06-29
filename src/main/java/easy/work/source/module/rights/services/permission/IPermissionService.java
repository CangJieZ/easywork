package easy.work.source.module.rights.services.permission;

import java.util.List;

import easy.work.source.commom.base.PageMode;
import easy.work.source.module.rights.pojo.Permission;
import easy.work.source.module.rights.pojo.PermissionTree;
import easy.work.source.module.rights.query.PermissionQuery;

public interface IPermissionService {
	
	/**
	 * @Auther: zhuwt  
	 * @Description: 查询pids
	 * @Date:2017年5月31日下午4:52:12
	 * @param rid
	 * @return  
	 * @return List<Integer>
	 */
	List<Integer> selectPidsByRidMenu(Integer rid);
	
	/**
	 * @Auther: zhuwt  
	 * @Description: 查询mid
	 * @Date:2017年5月31日下午4:52:12
	 * @param rid
	 * @return  
	 * @return List<Integer>
	 */
	List<Integer> selectPidsByRidPer(Integer rid);

	/**
	 * @Auther: zhuwt
	 * @Description: 批量删除
	 * @Date:2017年5月27日下午6:23:17
	 * @param ids
	 * @return
	 * @return boolean
	 */
	boolean deleteBatchIds(Integer[] ids);

	/**
	 * @Auther: zhuwt
	 * @Description:获取所有权限 树型菜单
	 * @Date:2017年5月23日下午1:59:56
	 * @return
	 * @return List<MenuPermissionTree>
	 */
	List<PermissionTree> queryPermissionTree();

	/**
	 * @Auther: zhuwt
	 * @Description:查询该用户下的所有菜单和权限
	 * @Date:2017年5月23日下午1:59:56
	 * @return
	 * @return List<MenuPermissionTree>
	 */
	List<PermissionTree> queryPermissionTreeByRid(Integer rid);

	/**
	 * 根据uid查询用户权限
	 * 
	 * @param uid
	 * @return
	 */
	List<Permission> queryPermissionByUid(Integer uid);

	/**
	 * @Auther: zhuwt
	 * @Description: 查询所有权限
	 * @Date:2017年5月23日上午1:04:14
	 * @param query
	 * @return
	 * @return PageMode<Permission>
	 */
	PageMode<Permission> queryAll(PermissionQuery query);

	/**
	 * @Auther: zhuwt
	 * @Description: 插入一条记录
	 * @Date:2017年5月17日下午4:59:43
	 * @param permission
	 * @return void
	 */
	boolean insertPermission(Permission permission);

	/**
	 * @Auther: zhuwt
	 * @Description: 更新数据
	 * @Date:2017年5月17日下午4:59:43
	 * @param permission
	 * @return void
	 */
	boolean updatePermission(Permission permission);

	/**
	 * @Auther: zhuwt
	 * @Description: 删除数据
	 * @Date:2017年5月23日上午1:19:55
	 * @param pid
	 * @return
	 * @return boolean
	 */
	boolean delete(Integer pid);
	
	/**
	 * @Auther: zhuwt
	 * @Description:Role rid 查询
	 * @Date:2017年5月24日上午10:42:28
	 * @param query
	 * @return
	 * @return List<Permission>
	 */
	List<Permission> selectByRid(Integer rid);

}
