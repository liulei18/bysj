package com.edu.zzti.ass.management.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.edu.zzti.ass.testlibrary.model.TUnit;

/**
 * TFile entity.
 */
@Entity
@Table(name = "t_file_show", catalog = "ass")
public class TFileShow implements java.io.Serializable {



	private static final long serialVersionUID = 9072234076349733389L;
	
	private Integer id;
	private String name;
	private String url;
	private String realName;
	private Date addTimes;
	private Integer fkey;
	private Integer ftype;
	private Integer isCheck;
	

	public TFileShow() {
	}

	
	public TFileShow( String name, String url,
			String realName, Integer fkey, Integer ftype, Integer isCheck) {
		this.name = name;
		this.url = url;
		this.realName = realName;
		this.fkey = fkey;
		this.ftype = ftype;
		this.isCheck = isCheck;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	

	@Column(name = "name", nullable = false, length = 32)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "url", nullable = false, length = 128)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "realName", nullable = false, length = 32)
	public String getRealName() {
		return this.realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	@Column(name = "fkey", nullable = false)
	public Integer getFkey() {
		return this.fkey;
	}

	public void setFkey(Integer fkey) {
		this.fkey = fkey;
	}

	@Column(name = "ftype", nullable = false)
	public Integer getFtype() {
		return this.ftype;
	}

	public void setFtype(Integer ftype) {
		this.ftype = ftype;
	}

	@Column(name = "isCheck", nullable = false)
	public Integer getIsCheck() {
		return this.isCheck;
	}

	public void setIsCheck(Integer isCheck) {
		this.isCheck = isCheck;
	}
	

	public Date getAddTimes() {
		return addTimes;
	}

	public void setAddTimes(Date addTimes) {
		this.addTimes = addTimes;
	}
	
	

}