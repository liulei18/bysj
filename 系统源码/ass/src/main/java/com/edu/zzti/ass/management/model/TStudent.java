package com.edu.zzti.ass.management.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.edu.zzti.ass.testlibrary.model.TJudge;
import com.edu.zzti.ass.testlibrary.model.TSinglesel;
import com.edu.zzti.ass.testlibrary.model.TSubjective;

/**
 * TStudent entity. 
 */
@Entity
@Table(name = "t_student", catalog = "ass")
public class TStudent implements java.io.Serializable {



	
	
	private String id;
	private TClass TClass;
	private String pwd;
	private String realName;
	private String imgurl;
	private String descs;
	private String email;
	private String remark;


	public TStudent() {
	}

	public TStudent(String id, String pwd) {
		this.id = id;
		this.pwd = pwd;
	}

	public TStudent(String id, TClass TClass, String pwd, String realName) {
		this.id = id;
		this.TClass = TClass;
		this.pwd = pwd;
		this.realName = realName;
	}


	@Id
	@Column(name = "id", unique = true, nullable = false, length = 32)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}
	@ManyToOne(cascade=CascadeType.REFRESH,optional=false)
	@JoinColumn(name = "classId")
	@JsonIgnore
	public TClass getTClass() {
		return TClass;
	}

	public void setTClass(TClass tClass) {
		TClass = tClass;
	}

	@Column(name = "pwd", nullable = false, length = 32)
	public String getPwd() {
		return this.pwd;
	}

	
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	@Column(name = "realName", length = 32)
	public String getRealName() {
		return this.realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	
	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	public String getDescs() {
		return descs;
	}

	public void setDescs(String descs) {
		this.descs = descs;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		TStudent other = (TStudent) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	
}