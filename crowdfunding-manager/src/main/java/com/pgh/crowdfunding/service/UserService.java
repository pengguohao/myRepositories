package com.pgh.crowdfunding.service;

import java.util.List;


import com.pgh.crowdfunding.pojo.PageInfo;
import com.pgh.crowdfunding.pojo.User;

public interface UserService {
	/**
	 * 用户请求登陆
	 */
	User selByNamePwd(String username,String password);
	
	/**
	 * 分页查询
	 */
	List<User> selForPage(PageInfo pageInfo);
	
	/**
	 * 分页查询,查总数
	 */
	long selTotal(PageInfo pageInfo);
	
	/**
	 * 注册用户查询-校验账号
	 */
	User selByName(String username);

	/**
	 * 注册用户查询-校验用户名
	 */
	User selByPersonName(String pname);
	
	/**
	 * 注册用户查询-校验邮箱
	 */
	User selByEmail(String email);
	
	/**
	 * 后台管理增加用户
	 */
	int insByManager(User user);
	
	/**
	 * 后台管理单次删除用户
	 */
	int updLifeStatusByUid(int uid);
	
	/**
	 * 后台批量删除
	 * @return
	 */
	int updLifeStatusByUids(Integer[] uids);
	
	/**
	 * 根据id查询用户信息用于修改
	 * @return
	 */
	User selByUid(Integer uid);
	
	/**
	 * 修改用户信息
	 */
	int updUserInfo(User user);

	/**
	 * 修改用户身份
	 */
	int updUserInfoByinfo(String info,Integer uid);

	/**
	 * 一般的注册
	 * @param user
	 * @return
	 */
	int insByRegularUser(User user);

	/**
	 * 一般的注册
	 * @param user
	 * @return
	 */
	String selEmailByUsername(String username);

	/**
	 * 一般的密码修改
	 * @param user
	 * @return
	 */
	int updPassword4User(User user);
}
