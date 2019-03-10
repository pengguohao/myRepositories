package com.pgh.crowdfunding.service;


import java.util.List;
import java.util.Map;

import com.pgh.crowdfunding.pojo.PageInfo;
import com.pgh.crowdfunding.pojo.Role;


public interface RoleService {

	/**
	 * �������н�ɫ����Ϣ
	 */
	List<Role> selByAll();
	
	/**
	 * �����û����еĽ�ɫ��Ϣ
	 */
	List<Role> selHadRoles(String uid);
	
	/**
	 * ���û������ɫ
	 */
	int insAssignRoles(Map<String,Object> roleMap);
	
	/**
	 * ȡ���û���ɫ
	 */
	int delAssignRoles(Map<String,Object> roleMap);
	
	/**
	 * ��ҳ��ѯ
	 */
	List<Role> selForPage(PageInfo pageInfo);
	
	/**
	 * ��ҳ��ѯ,������
	 */
	long selTotal(PageInfo pageInfo);
	
	/**
	 * ��̨�������ӽ�ɫ
	 */
	int insByManager(Role role);
	
	/**
	 * ��̨������ɾ����ɫ
	 */
	int delByManager(int rid);
	
	/**
	 * ��̨����ɾ��
	 * @return
	 */
	int delRoles(Integer[] rids);
	
	/**
	 * ����id��ѯ��ɫ��Ϣ�����޸�
	 * @return
	 */
	Role selByRid(Integer rid);
	
	/**
	 * �޸Ľ�ɫ��Ϣ
	 */
	int updRoleInfo(Role role);

	/**
	 * ͨ��name��ѯ��ɫ��Ϣ
	 */
	Role selByName(String name);
}
