package com.company.beans;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Organization {
	
	@Id
	@GeneratedValue
	private int id;
	private String name;
	
	private Date doj;
	
	private Date dol;
	
	private boolean isCurrentOrg;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonIgnore
	User user;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDoj() {
		return doj;
	}

	public void setDoj(Date doj) {
		this.doj = doj;
	}

	public Date getDol() {
		return dol;
	}

	public void setDol(Date dol) {
		this.dol = dol;
	}

	public boolean isCurrentOrg() {
		return isCurrentOrg;
	}

	public void setCurrentOrg(boolean isCurrentOrg) {
		this.isCurrentOrg = isCurrentOrg;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Organization [name=" + name + ", doj=" + doj + ", dol=" + dol + ", isCurrentOrg=" + isCurrentOrg + "]";
	}
	
	
}
