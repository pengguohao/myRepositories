package com.pgh.crowdfunding.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.pgh.crowdfunding.pojo.PageInfo;
import com.pgh.crowdfunding.pojo.User;

public interface UserMapper {
	/**
	 * 用户登陆
	 */
	@Select("select * from user where username=#{0} and password=#{1} and lifestatus='1'")
	User selByNamePwd(String username,String password);
	
	/**
	 * 分页查询所有用户的信息
	 */
	List<User> selAllForPage(PageInfo pageInfo);
	
	/**
	 * 查询所有数据的信息
	 */
	long selTotal(PageInfo pageInfo);
	
	/**
	 * 注册用户查询-校验账号
	 */
	@Select("select uid from user where username=#{0}")
	User selByName(String username);
	/**
	 * 注册用户查询-校验用户名
	 */
	@Select("select uid from user where pname=#{0}")
	User selByPersonName(String pname);
	/**
	 * 注册用户查询-校验邮箱
	 */
	@Select("select uid from user where email=#{0}")
	User selByEmail(String email);
	
	/**
	 * 后台管理增加用户
	 */
	@Insert("insert into user values (default,#{username},#{password},#{pname},#{salt},#{email},#{birthday},default,default,default)")
	int insByManager(User user);
	
	/**
	 * 后台管理单次删除用户
	 */
	@Update("update user set lifestatus='0' where uid=#{0}")
	int updLifeStatusByUid(int uid);
	
	/**
	 * 后台管理批量删除用户
	 */
	int updLifeStatusByUids(Integer[] array);

	/**
	 * 根据id查询用户信息用于修改
	 * @return
	 */
	@Select("select * from user where uid=#{0}")
	User selByUid(Integer uid);
	
	/**
	 * 修改用户信息
	 */
	@Update("update user set username=#{username},pname=#{pname},email=#{email} where uid=#{uid}")
	int updUserInfo(User user);
	
	/**
	 * 根据id查询用户信息用于修改
	 * @return
	 */
	List<User> selUsersByUids(Integer[] array);

	/**
	 * 修改用户身份
	 */
	@Update("update user set info=#{0} where uid=#{1}")
	int updUserInfoByinfo(String info,Integer uid);

	/**
	 * 一般的注册
	 * @param user
	 * @return
	 */
	@Insert("insert into user values(default,#{username},#{password},#{pname},#{salt},#{email},#{birthday},default,default,default)")
	int insByRegularUser(User user);

	/**
	 * 通过用户名查找邮箱
	 * @param username
	 * @return
	 */
	@Select("select email from user where username=#{0}")
	String selEmailByUsername(String username);

	/**
	 * 一般的密码修改
	 * @param user
	 * @return
	 */
	@Update("update user set password=#{password} where username=#{username} and email=#{email} and lifestatus='1'")
	int updPassword4User(User user);
	
}
