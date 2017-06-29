package easy.work.source.module.rights.dao;

import java.util.List;

import easy.work.source.module.rights.pojo.BackUserPojo;
import easy.work.source.module.rights.pojo.UserRolePojo;
import easy.work.source.module.rights.query.RoleQuery;

public interface UserDao {
    int deleteByPrimaryKey(Integer uid);

    int insert(BackUserPojo record);

    int insertSelective(BackUserPojo record);

    BackUserPojo selectByPrimaryKey(Integer uid);

    int updateByPrimaryKeySelective(BackUserPojo record);

    int updateByPrimaryKey(BackUserPojo record);
    /**
     * @Auther: zhuwt  
     * @Description: 修改用户密码 
     * @Date:2017年5月31日上午9:51:08
     * @param backUserPojo
     * @return  
     * @return int
     */
    int updateByPwd(BackUserPojo backUserPojo);
    
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
	 * @Auther: zhuwt  
	 * @Description: 角色和用户关联表查询数据
	 * @Date:2017年5月21日下午1:25:09
	 * @param query
	 * @return  
	 * @return List<BackUserPojo>
	 */
	List<UserRolePojo> selectFromRole(RoleQuery query);
}