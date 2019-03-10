package com.pgh.crowdfunding.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.pgh.crowdfunding.pojo.PageInfo;
import com.pgh.crowdfunding.pojo.User;

public interface UserMapper {
	/**
	 * �û���½
	 */
	@Select("select * from user where username=#{0} and password=#{1} and lifestatus='1'")
	User selByNamePwd(String username,String password);
	
	/**
	 * ��ҳ��ѯ�����û�����Ϣ
	 */
	List<User> selAllForPage(PageInfo pageInfo);
	
	/**
	 * ��ѯ�������ݵ���Ϣ
	 */
	long selTotal(PageInfo pageInfo);
	
	/**
	 * ע���û���ѯ-У���˺�
	 */
	@Select("select uid from user where username=#{0}")
	User selByName(String username);
	/**
	 * ע���û���ѯ-У���û���
	 */
	@Select("select uid from user where pname=#{0}")
	User selByPersonName(String pname);
	/**
	 * ע���û���ѯ-У������
	 */
	@Select("select uid from user where email=#{0}")
	User selByEmail(String email);
	
	/**
	 * ��̨���������û�
	 */
	@Insert("insert into user values (default,#{username},#{password},#{pname},#{salt},#{email},#{birthday},default,default,default)")
	int insByManager(User user);
	
	/**
	 * ��̨������ɾ���û�
	 */
	@Update("update user set lifestatus='0' where uid=#{0}")
	int updLifeStatusByUid(int uid);
	
	/**
	 * ��̨��������ɾ���û�
	 */
	int updLifeStatusByUids(Integer[] array);

	/**
	 * ����id��ѯ�û���Ϣ�����޸�
	 * @return
	 */
	@Select("select * from user where uid=#{0}")
	User selByUid(Integer uid);
	
	/**
	 * �޸��û���Ϣ
	 */
	@Update("update user set username=#{username},pname=#{pname},email=#{email} where uid=#{uid}")
	int updUserInfo(User user);
	
	/**
	 * ����id��ѯ�û���Ϣ�����޸�
	 * @return
	 */
	List<User> selUsersByUids(Integer[] array);

	/**
	 * �޸��û����
	 */
	@Update("update user set info=#{0} where uid=#{1}")
	int updUserInfoByinfo(String info,Integer uid);

	/**
	 * һ���ע��
	 * @param user
	 * @return
	 */
	@Insert("insert into user values(default,#{username},#{password},#{pname},#{salt},#{email},#{birthday},default,default,default)")
	int insByRegularUser(User user);

	/**
	 * ͨ���û�����������
	 * @param username
	 * @return
	 */
	@Select("select email from user where username=#{0}")
	String selEmailByUsername(String username);

	/**
	 * һ��������޸�
	 * @param user
	 * @return
	 */
	@Update("update user set password=#{password} where username=#{username} and email=#{email} and lifestatus='1'")
	int updPassword4User(User user);
	
}
