package easy.work.source.module.user.dao;

import easy.work.source.module.user.pojo.BackUserPojo;

public interface BackUserDao {
	int deleteByPrimaryKey(Integer uid);

	int insert(BackUserPojo record);

	int insertSelective(BackUserPojo record);

	BackUserPojo selectByPrimaryKey(Integer uid);

	int updateByPrimaryKeySelective(BackUserPojo record);

	int updateByPrimaryKey(BackUserPojo record);

}