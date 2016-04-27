package org.zframework.web.entity.system;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ro_meetarr")
public class Meettable {
	private Integer id;
	private String topic;
	private String factoryid;
	private String roomid;
	private String starttime;
	private String endtime;
	private String startdate;
	private String enddate;
	private Integer meetduration;
	private String meettype;
	private String reserver;
	private String invited;
	private String meetres;
	private String rereserve;
	private String meetfreq;
	private String contentsummary;
	private String meetrecord;
	private String attachmentpath;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_ro_meetarr")
	@SequenceGenerator(name = "seq_ro_meetarr", sequenceName = "seq_ro_meetarr")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "topic", nullable = false, length = 100)
	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	@Column(name = "factoryid", nullable = false, length = 100)
	public String getFactoryid() {
		return factoryid;
	}

	public void setFactoryid(String factoryid) {
		this.factoryid = factoryid;
	}

	@Column(name = "roomid", nullable = false, length = 100)
	public String getRoomid() {
		return roomid;
	}

	public void setRoomid(String roomid) {
		this.roomid = roomid;
	}

	@Column(name = "starttime", nullable = false, length = 100)
	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	@Column(name = "endtime", nullable = false, length = 100)
	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	@Column(name = "startdate", nullable = false, length = 100)
	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	@Column(name = "enddate", nullable = false, length = 100)
	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	@Column(name = "meetduration", nullable = false, length = 11)
	public Integer getMeetduration() {
		return meetduration;
	}

	public void setMeetduration(Integer meetduration) {
		this.meetduration = meetduration;
	}

	@Column(name = "meettype", nullable = false, length = 100)
	public String getMeettype() {
		return meettype;
	}

	public void setMeettype(String meettype) {
		this.meettype = meettype;
	}

	@Column(name = "reserver", nullable = false, length = 50)
	public String getReserver() {
		return reserver;
	}

	public void setReserver(String reserver) {
		this.reserver = reserver;
	}

	@Column(name = "rereserve", nullable = false, length = 100)
	public String getRereserve() {
		return rereserve;
	}

	public void setRereserve(String rereserve) {
		this.rereserve = rereserve;
	}

	@Column(name = "meetfreq", nullable = false, length = 100)
	public String getMeetfreq() {
		return meetfreq;
	}

	public void setMeetfreq(String meetfreq) {
		this.meetfreq = meetfreq;
	}

	@Column(name = "contentsummary", nullable = false, length = 100)
	public String getContentsummary() {
		return contentsummary;
	}

	public void setContentsummary(String contentsummary) {
		this.contentsummary = contentsummary;
	}

	@Column(name = "invited", nullable = false, length = 100)
	public String getInvited() {
		return invited;
	}

	public void setInvited(String invited) {
		this.invited = invited;
	}

	@Column(name = "meetres", nullable = false, length = 100)
	public String getMeetres() {
		return meetres;
	}

	public void setMeetres(String meetres) {
		this.meetres = meetres;
	}

	@Override
	public String toString() {
		return "MeetArr [id=" + id + ", topic=" + topic + ", factoryid="
				+ factoryid + ", roomid=" + roomid + ", starttime=" + starttime
				+ ", endtime=" + endtime + ", startdate=" + startdate
				+ ", enddate=" + enddate + ", meetduration=" + meetduration
				+ ", meettype=" + meettype + ", reserver=" + reserver
				+ ", invited=" + invited + ", meetres=" + meetres
				+ ", rereserve=" + rereserve + ", meetfreq=" + meetfreq
				+ ", contentsummary=" + contentsummary + "]";
	}

	public String getMeetrecord() {
		return meetrecord;
	}
	@Column(name = "meetrecord", nullable = false, length = 9999)
	public void setMeetrecord(String meetrecord) {
		this.meetrecord = meetrecord;
	}

	public String getAttachmentpath() {
		return attachmentpath;
	}
	@Column(name = "attachmentpath", nullable = false, length = 9999)
	public void setAttachmentpath(String attachmentpath) {
		this.attachmentpath = attachmentpath;
	}	
}
