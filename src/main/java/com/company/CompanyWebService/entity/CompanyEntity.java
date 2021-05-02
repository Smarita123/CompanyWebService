package com.company.CompanyWebService.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class CompanyEntity {
	

	//@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
    @GeneratedValue (generator = "UUID")
    @GenericGenerator(name = "UUID", strategy ="org.hibernate.id.UUIDGenerator")
	private String id;
	
	@Column(updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdAt;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date updatedAt;
	
	@Column (name="name")
	String name;
	@Column (name="address")
	String address;
	@Column (name="stipend")
	Integer stipend;
	
	public Integer getStipend() {
		return stipend;
	}
	public void setStipend(Integer stipend) {
		this.stipend = stipend;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String toString() {
		return ("id:"+getId() +", name:"+name +", address:"+address +", stipend:"+stipend);
	
		
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}


}
