package com.pgh.crowdfunding.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Menu implements Serializable{
	
	private int id;
	private String name;
	private String url;
	private int pid;
	private String icon;
	private boolean open=true;
	private boolean checked=false;
	private boolean chkDisabled=true;
	private List<Menu> children=new ArrayList<Menu>();
	private String manager="1";
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public boolean isChkDisabled() {
		return chkDisabled;
	}
	public void setChkDisabled(boolean chkDisabled) {
		this.chkDisabled = chkDisabled;
	}
	public List<Menu> getChildren() {
		return children;
	}
	public void setChildren(List<Menu> children) {
		this.children = children;
	}
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (checked ? 1231 : 1237);
		result = prime * result + ((children == null) ? 0 : children.hashCode());
		result = prime * result + (chkDisabled ? 1231 : 1237);
		result = prime * result + ((icon == null) ? 0 : icon.hashCode());
		result = prime * result + id;
		result = prime * result + ((manager == null) ? 0 : manager.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (open ? 1231 : 1237);
		result = prime * result + pid;
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Menu other = (Menu) obj;
		if (checked != other.checked)
			return false;
		if (children == null) {
			if (other.children != null)
				return false;
		} else if (!children.equals(other.children))
			return false;
		if (chkDisabled != other.chkDisabled)
			return false;
		if (icon == null) {
			if (other.icon != null)
				return false;
		} else if (!icon.equals(other.icon))
			return false;
		if (id != other.id)
			return false;
		if (manager == null) {
			if (other.manager != null)
				return false;
		} else if (!manager.equals(other.manager))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (open != other.open)
			return false;
		if (pid != other.pid)
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Menu [id=" + id + ", name=" + name + ", url=" + url + ", pid=" + pid + ", icon=" + icon + ", open="
				+ open + ", checked=" + checked + ", chkDisabled=" + chkDisabled + ", children=" + children
				+ ", manager=" + manager + "]";
	}
	public Menu(int id, String name, String url, int pid, String icon, boolean open, boolean checked,
			boolean chkDisabled, List<Menu> children, String manager) {
		super();
		this.id = id;
		this.name = name;
		this.url = url;
		this.pid = pid;
		this.icon = icon;
		this.open = open;
		this.checked = checked;
		this.chkDisabled = chkDisabled;
		this.children = children;
		this.manager = manager;
	}
	public Menu() {
		super();
	}
	

}
