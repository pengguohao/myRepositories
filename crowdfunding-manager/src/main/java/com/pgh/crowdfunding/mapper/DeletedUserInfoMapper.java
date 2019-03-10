package com.pgh.crowdfunding.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;

import com.pgh.crowdfunding.pojo.DeletedUserInfo;

public interface DeletedUserInfoMapper {

	/**
	 * 插入被删除的用户信息
	 */
	@Insert("insert into user_deleteinfo values(default,#{uid},#{username},#{password},#{pname},#{salt},#{email},#{birthday},#{rid})")
	int insDeletedUser(DeletedUserInfo duser);
	
	/**
	 * 批量插入被删除的用户信息
	 */
	int insDeletedUsers(List<DeletedUserInfo> dusers);
	
	
	
}
