package com.pgh.crowdfunding.service;

import java.util.List;


import com.pgh.crowdfunding.pojo.PageInfo;
import com.pgh.crowdfunding.pojo.User;

public interface UserService {
	/**
	 * �û������½
	 */
	User selByNamePwd(String username,String password);
	
	/**
	 * ��ҳ��ѯ
	 */
	List<User> selForPage(PageInfo pageInfo);
	
	/**
	 * ��ҳ��ѯ,������
	 */
	long selTotal(PageInfo pageInfo);
	
	/**
	 * ע���û���ѯ-У���˺�
	 */
	User selByName(String username);

	/**
	 * ע���û���ѯ-У���û���
	 */
	User selByPersonName(String pname);
	
	/**
	 * ע���û���ѯ-У������
	 */
	User selByEmail(String email);
	
	/**
	 * ��̨���������û�
	 */
	int insByManager(User user);
	
	/**
	 * ��̨������ɾ���û�
	 */
	int updLifeStatusByUid(int uid);
	
	/**
	 * ��̨����ɾ��
	 * @return
	 */
	int updLifeStatusByUids(Integer[] uids);
	
	/**
	 * ����id��ѯ�û���Ϣ�����޸�
	 * @return
	 */
	User selByUid(Integer uid);
	
	/**
	 * �޸��û���Ϣ
	 */
	int updUserInfo(User user);

	/**
	 * �޸��û����
	 */
	int updUserInfoByinfo(String info,Integer uid);

	/**
	 * һ���ע��
	 * @param user
	 * @return
	 */
	int insByRegularUser(User user);

	/**
	 * һ���ע��
	 * @param user
	 * @return
	 */
	String selEmailByUsername(String username);

	/**
	 * һ��������޸�
	 * @param user
	 * @return
	 */
	int updPassword4User(User user);
}
