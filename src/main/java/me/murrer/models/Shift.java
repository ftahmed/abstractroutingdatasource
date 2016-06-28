package me.murrer.models;


import java.util.Date;
import java.util.UUID;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 * Shift Model.
 */
@Entity
@Access(AccessType.FIELD)
public class Shift {

	@Id
	private String id = UUID.randomUUID().toString();

	@Column
	private String customer;

	@Column
	private Date startDate;

	@Column
	private Date endDate;

	public Shift() {
	}

	public Shift(String customer) {
		this.customer = customer;
		this.startDate = new Date();
		this.endDate = new Date();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}
