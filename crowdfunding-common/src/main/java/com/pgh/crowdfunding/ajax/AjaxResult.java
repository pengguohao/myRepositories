package com.pgh.crowdfunding.ajax;

public class AjaxResult {
	/**
	 * ajax响应代码
	 */
	private boolean ajaxCode;
	
	/**
	 * ajax响应码
	 * @return
	 */
	/**
	 * ajax状态码
	 */
	private int numCode;
	
	/**
	 * ajax信息码
	 */
	private int infoCode;
	
	public int getInfoCode() {
		return infoCode;
	}

	public void setInfoCode(int infoCode) {
		this.infoCode = infoCode;
	}

	public boolean getAjaxCode() {
		return ajaxCode;
	}

	public void setAjaxCode(boolean ajaxCode) {
		this.ajaxCode = ajaxCode;
	}

	public int getNumCode() {
		return numCode;
	}

	public void setNumCode(int numCode) {
		this.numCode = numCode;
	}

	
	
	

}
