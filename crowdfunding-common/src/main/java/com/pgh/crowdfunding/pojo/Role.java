package com.pgh.crowdfunding.pojo;

import java.io.Serializable;

public class Role implements Serializable{
	private int rid;
	private String name;
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Role(int rid, String name) {
		super();
		this.rid = rid;
		this.name = name;
	}
	public Role() {
		super();
	}
	@Override
	public String toString() {
		return "Role [rid=" + rid + ", name=" + name + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + rid;
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
		Role other = (Role) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (rid != other.rid)
			return false;
		return true;
	}
	
	

}
