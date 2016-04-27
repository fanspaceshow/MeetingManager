package org.zframework.web.entity.system;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ro_richtext")
public class RichTextData {
	private Integer id;
	private String richtext;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_ro_richtext")
	@SequenceGenerator(name = "seq_ro_richtext", sequenceName = "seq_ro_richtext")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="richtext",nullable=false,length=50)
	public String getRichtext() {
		return richtext;
	}
	public void setRichtext(String richtext) {
		this.richtext = richtext;
	}	
}
