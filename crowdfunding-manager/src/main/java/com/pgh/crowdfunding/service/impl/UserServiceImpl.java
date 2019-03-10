package com.pgh.crowdfunding.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pgh.crowdfunding.mapper.DeletedUserInfoMapper;
import com.pgh.crowdfunding.mapper.RoleMapper;
import com.pgh.crowdfunding.mapper.UserMapper;
import com.pgh.crowdfunding.pojo.PageInfo;
import com.pgh.crowdfunding.pojo.User;
import com.pgh.crowdfunding.service.UserService;
@Service
public class UserServiceImpl implements UserService{
	@Resource
	private UserMapper userMapper;
	
	@Resource 
	private RoleMapper roleMapper;
	
	@Resource
	private DeletedUserInfoMapper deleatedUserInfoMapper;
	
	public User selByNamePwd(String username, String password) {
		return userMapper.selByNamePwd(username, password);
	}
	
	public List<User> selForPage(PageInfo pageInfo) {
		return userMapper.selAllForPage(pageInfo);
	}

	public long selTotal(PageInfo pageInfo) {
		return userMapper.selTotal(pageInfo);
	}

	public User selByName(String username) {
		return userMapper.selByName(username);
	}

	public User selByPersonName(String pname) {
		return userMapper.selByPersonName(pname);
	}

	public User selByEmail(String email) {
		return userMapper.selByEmail(email);
	}

	public int insByManager(User user) {
		return userMapper.insByManager(user);
	}

	public int updLifeStatusByUid(int uid) {
		//ɾ���û���Ϣǰ�Ȱ���Ϣת�Ƶ�ɾ���û���Ϣ��ɾ���������û���ɫ�����Ϣ
			//ɾ���û���ɫ���������Ϣ
		roleMapper.delUserRolesByUid(uid);
		return userMapper.updLifeStatusByUid(uid);
	}

	public int updLifeStatusByUids(Integer[] uids) {
		//�ȸ���id�����е��û���Ϣ�������
		
		int index=0;
		index=userMapper.updLifeStatusByUids(uids);
		if(index!=uids.length) {
			throw new RuntimeException("�����û����״̬��Ϣʧ�ܣ�");
		}else {
			roleMapper.delUserRolesByUids(uids);
		}
		return index;
	}

	public User selByUid(Integer uid) {
		return userMapper.selByUid(uid);
	}

	public int updUserInfo(User user) {
		return userMapper.updUserInfo(user);
	}

	public int updUserInfoByinfo(String info,Integer uid) {
		return userMapper.updUserInfoByinfo(info,uid);
	}

	public int insByRegularUser(User user) {
		return userMapper.insByRegularUser(user);
	}

	public String selEmailByUsername(String username) {
		return userMapper.selEmailByUsername(username);
	}

	public int updPassword4User(User user) {
		return userMapper.updPassword4User(user);
	}
	
	


}
