package com.pgh.crowdfunding.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.pgh.crowdfunding.pojo.PageInfo;
import com.pgh.crowdfunding.pojo.Role;

public interface RoleMapper {
	
	/**
	 * �������ݿ��ɫ��Ϣ
	 */
	@Select("select * from role order by rid")
	List<Role> selAllRole();
	
	/**
	 * �����û����еĽ�ɫ��Ϣ
	 */
	@Select("select * from role where rid in (select rid from user_role where uid=#{0})")
	List<Role> selHadRoles(String uid);
	
	/**
	 * ���û����ý�ɫ
	 */
	int insAssignRoles(Map<String,Object> roleMap);
	/**
	 * ȡ���û���ɫ
	 * @param roleMap
	 * @return
	 */
	int delAssignRoles(Map<String, Object> roleMap);
	
	/**
	 * ��ҳ��ѯ���н�ɫ����Ϣ
	 */
	List<Role> selAllForPage(PageInfo pageInfo);
	
	/**
	 * ��ѯ�������ݵ���Ϣ
	 */
	long selTotal(PageInfo pageInfo);
	
	/**
	 * ��̨�������ӽ�ɫ
	 */
	@Insert("insert into role values (default,#{name})")
	int insByManager(Role role);
	
	/**
	 * ��̨������ɾ����ɫ
	 */
	@Delete("delete from role where rid=#{0}")
	int delByManager(int rid);
	
	/**
	 * ��̨��������ɾ����ɫ
	 */
	int delRoles(Integer[] array);

	/**
	 * ����id��ѯ��ɫ��Ϣ�����޸�
	 * @return
	 */
	@Select("select * from role where rid=#{0}")
	Role selByRid(Integer rid);
	
	/**
	 * �޸Ľ�ɫ��Ϣ
	 */
	@Update("update role set name=#{name} where rid=#{rid}")
	int updRoleInfo(Role role);
	
	/**
	 * ��ѯ��ɫ��Ϣ
	 */
	@Select("select * from role where name=#{0}")
	Role selByName(String name);

	/**
	 * �����û�idɾ������еĽ�ɫ��Ϣ
	 * @param uid
	 * @return
	 */
	@Delete("delete from user_role where uid=#{0}")
	int delUserRolesByUid(int uid);
	
	/**
	 * ����ɾ���û���ɫ��Ϣ
	 * @param uids
	 * @return
	 */
	int delUserRolesByUids(Integer[] uids);

	
	/**
	 * ���ݽ�ɫidɾ��������Ľ�ɫ��Ϣ
	 * @param uid
	 * @return
	 */
	@Delete("delete from user_role where rid=#{0}")
	int delUsersRolesByRid(int rid);

	
	/**
	 * ����ɾ�����û������ݽ�ɫidɾ�����ǹ����Ľ�ɫ��Ϣ
	 * @param uid
	 * @return
	 */
	int delUsersRolesByRids(Integer[] rids);
	
	
}
