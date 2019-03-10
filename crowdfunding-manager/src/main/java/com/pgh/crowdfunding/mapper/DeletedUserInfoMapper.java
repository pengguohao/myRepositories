package com.pgh.crowdfunding.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;

import com.pgh.crowdfunding.pojo.DeletedUserInfo;

public interface DeletedUserInfoMapper {

	/**
	 * ���뱻ɾ�����û���Ϣ
	 */
	@Insert("insert into user_deleteinfo values(default,#{uid},#{username},#{password},#{pname},#{salt},#{email},#{birthday},#{rid})")
	int insDeletedUser(DeletedUserInfo duser);
	
	/**
	 * �������뱻ɾ�����û���Ϣ
	 */
	int insDeletedUsers(List<DeletedUserInfo> dusers);
	
	
	
}
