package com.pgh.crowdfunding.pojo;

import java.io.Serializable;
import java.util.List;

public class PageInfo implements Serializable{
	/**
	 * ���ݿ�ʼ��ѯ��
	 */
	private int pageStart;
	/**
	 * ÿҳ��ʾ��������
	 */
	private int pageSize;
	/**
	 * �ܹ��м�������
	 */
	private long total;
	/**
	 * ��ǰҳ��
	 */
	private int pageNum;
	/**
	 * �ܹ��м�ҳ
	 */
	private int pageNums;
	/**
	 * �Ƿ�ɹ������ݿ��и�����Ϣ
	 */
	private boolean success;
	/**
	 * Ҫ��ģ����ѯ����Ϣ
	 * @return
	 */
	private String selAsked;
	
	/**
	 * Ҫչʾ��user��Ϣ
	 * @return
	 */
	private List<User> pageDisp;
	
	/**
	 * Ҫչʾ��role��Ϣ
	 * @return
	 */
	private List<Role> pageDispRoles;
	
	
	
	public List<Role> getPageDispRoles() {
		return pageDispRoles;
	}
	public void setPageDispRoles(List<Role> pageDispRoles) {
		this.pageDispRoles = pageDispRoles;
	}
	public String getSelAsked() {
		return selAsked;
	}
	public void setSelAsked(String selAsked) {
		this.selAsked = selAsked;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public int getPageNums() {
		return pageNums;
	}
	public void setPageNums(int pageNums) {
		this.pageNums = pageNums;
	}
	
	public int getPageStart() {
		return pageStart;
	}
	public void setPageStart(int pageStart) {
		this.pageStart = pageStart;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public List<User> getPageDisp() {
		return pageDisp;
	}
	public void setPageDisp(List<User> pageDisp) {
		this.pageDisp = pageDisp;
	}


	@Override
	public String toString() {
		return "PageInfo [pageStart=" + pageStart + ", pageSize=" + pageSize + ", total=" + total + ", pageNum="
				+ pageNum + ", pageNums=" + pageNums + ", success=" + success + ", selAsked=" + selAsked + ", pageDisp="
				+ pageDisp + ", pageDispRoles=" + pageDispRoles + "]";
	}
	public PageInfo(int pageStart, int pageSize, long total, int pageNum, int pageNums, List<User> pageDisp) {
		super();
		this.pageStart = pageStart;
		this.pageSize = pageSize;
		this.total = total;
		this.pageNum = pageNum;
		this.pageNums = pageNums;
		this.pageDisp = pageDisp;
	}
	public PageInfo() {
		super();
	}
	

}
