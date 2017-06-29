package easy.work.source.module.rights.dao;

import java.util.List;

import easy.work.source.module.rights.pojo.Menu;
import easy.work.source.module.rights.pojo.MenuList;
import easy.work.source.module.rights.pojo.MenuTree;
import easy.work.source.module.rights.query.MenuPermissionQuery;

public interface MenuDao {

	/**
	 * @Auther: zhuwt
	 * @Description: 批量删除菜单
	 * @Date:2017年5月27日下午4:31:32
	 * @param mids
	 * @return
	 * @return int
	 */
	int deleteByMids(List<Integer> mids);

	/**
	 * @Auther: zhuwt
	 * @Description: 查询用户菜单mid集合
	 * @Date:2017年5月25日下午1:47:24
	 * @param pids
	 * @return
	 * @return List<Integer>
	 */
	List<Integer> selectByPidForMids(List<Integer> pids);

	/**
	 * @Auther: zhuwt
	 * @Description: 查询菜单和权限关联数据
	 * @Date:2017年5月12日下午3:24:45
	 * @return
	 * @return List<MenuListModel>
	 */
	List<MenuList> selectMenuList(MenuPermissionQuery query);

	/**
	 * @Auther: zhuwt
	 * @Description: 查询菜单名称(上级菜单)
	 * @Date:2017年5月17日下午1:39:58
	 * @param parentId
	 * @return
	 * @return String
	 */
	String selectMenuNameByparentId(Integer parentId);

	/**
	 * @Auther: zhuwt
	 * @Description: 根据权限pid查询menu
	 * @Date:2017年5月25日上午11:57:29
	 * @param pid
	 * @return
	 * @return List<Menu>
	 */
	Menu selectByPid(Integer pid);

	/**
	 * @Auther: zhuwt
	 * @Description: 查询所有菜单
	 * @Date:2017年5月25日下午1:29:05
	 * @param pids
	 * @return
	 * @return List<Menu>
	 */
	List<Menu> selectByPidList(List<Integer> pids);

	/**
	 * @Auther: zhuwt
	 * @Description: 新增加菜单
	 * @Date:2017年5月17日下午1:39:58
	 * @param parentId
	 * @return
	 * @return String
	 */
	int insertSelective(Menu record);

	/**
	 * @Auther: zhuwt
	 * @Description: 删除数据
	 * @Date:2017年5月19日上午10:24:36
	 * @param mid
	 * @return
	 * @return int
	 */
	int deleteByPrimaryKey(Integer mid);

	/**
	 * @Auther: zhuwt
	 * @Description: 修改数据
	 * @Date:2017年5月19日上午10:24:36
	 * @param mid
	 * @return
	 * @return int
	 */
	int updateByPrimaryKeySelective(Menu record);

	/**
	 * @Auther: zhuwt
	 * @Description: parent_id 查询menu列表
	 * @Date:2017年5月21日下午10:27:27
	 * @param parentId
	 * @return
	 * @return List<Menu>
	 */
	List<Menu> selectByParentId(Integer parentId);

	/**
	 * @Auther: zhuwt
	 * @Description: 查询每个menu下的所有列表
	 * @Date:2017年5月21日下午10:28:51
	 * @param query
	 * @return
	 * @return List<MenuCatalogTree>
	 */
	List<MenuTree> selectMenuTree(MenuPermissionQuery query);

	/**
	 * @Auther: zhuwt
	 * @Description:查询上级菜单(一级菜单)
	 * @Date:2017年5月22日上午10:50:54
	 * @return
	 * @return List<Menu>
	 */
	List<Menu> selectMenuTreeSuperior();

	/**
	 * @Auther: zhuwt
	 * @Description:查询上级菜单(一级菜单)
	 * @Date:2017年5月22日上午10:50:54
	 * @return
	 * @return List<Menu>
	 */
	List<Menu> selectMenuTreeSubordinate();

}