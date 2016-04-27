package org.zframework.web.entity.system;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.metamodel.domain.Superclass;

@Entity
@Table(name = "ro_reponse")
public class Response {
	private Integer id;
	private String title;
	private Integer meetarrid;
	private String name;
	private String confirm;
	private String reason;
	private String timechanged;
	
	public Response(){
		super();		
	}
	
	public Response(Integer id, String title, Integer meetarrid, String name,
			String confirm, String reason, String timechanged) {
		super();
		this.id = id;
		this.title = title;
		this.meetarrid = meetarrid;
		this.name = name;
		this.confirm = confirm;
		this.reason = reason;
		this.timechanged = timechanged;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_ro_reponse")
	@SequenceGenerator(name = "seq_ro_reponse", sequenceName = "seq_ro_reponse")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="name",nullable=false,length=50)
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="meetarrid",nullable=false,length=50)
	public Integer getMeetarrid() {
		return meetarrid;
	}

	public void setMeetarrid(Integer meetarrid) {
		this.meetarrid = meetarrid;
	}
	
	@Column(name="confirm",nullable=false,length=50)
	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}
	
	@Column(name="title",nullable=false,length=50)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}	

	@Column(name="reason",nullable=false,length=50)
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	@Column(name="timechanged",nullable=false,length=50)
	public String getTimechanged() {
		return timechanged;
	}

	public void setTimechanged(String timechanged) {
		this.timechanged = timechanged;
	}

}
