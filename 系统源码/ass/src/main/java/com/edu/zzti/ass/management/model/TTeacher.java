package com.edu.zzti.ass.management.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.edu.zzti.ass.onlinestudy.model.TestPaper;
import com.edu.zzti.ass.server.model.PracticePaper;
import com.edu.zzti.ass.testlibrary.model.TJudge;
import com.edu.zzti.ass.testlibrary.model.TSinglesel;
import com.edu.zzti.ass.testlibrary.model.TSubjective;
import com.edu.zzti.ass.management.model.TClass;
/**
 * TTeacher entity.
 */
@Entity
@Table(name = "t_teacher", catalog = "ass")
public class TTeacher implements java.io.Serializable {



	private static final long serialVersionUID = -811866943275213416L;
	
	private String id;
	private String tname;
	private String tpwd;
	private String realName;
	private String email ="";
	private String lastLogDate;
	private Integer isAdmin=0;
    private String imgurl ="";
    private String descs ="";
    private String remark ="";
	private Set<TSubjective> TSubjectives = new HashSet<TSubjective>();
	private Set<TJudge> TJudges = new HashSet<TJudge>();
	private Set<TFile> TFiles = new HashSet<TFile>();
	private Set<TSinglesel> TSinglesels = new HashSet<TSinglesel>();
	private Set<TestPaper> TestPagers = new HashSet<TestPaper>();
	private Set<TClass> TClass = new HashSet<TClass>();
	private Set<PracticePaper> practicePapers = new HashSet<PracticePaper>();



	public TTeacher() {
	}
	
	public TTeacher(String id) {
		this.id = id;
	}
	
	public TTeacher( String tname, String tpwd){
		this.tname = tname;
		this.tpwd = tpwd;
	}
	public TTeacher(String id, String tname, String tpwd, Integer isAdmin) {
		this.id = id;
		this.tname = tname;
		this.tpwd = tpwd;
		this.isAdmin = isAdmin;
	}


	public TTeacher(String id, String tname, String tpwd, String realName,
			String email, String lastLogDate, Integer isAdmin,
			Set<TSubjective> TSubjectives, Set<TJudge> TJudges,Set<TFile> TFiles,
			Set<TSinglesel> TSinglesels/* ,Set<TClass> TClass*/) {
		this.id = id;
		this.tname = tname;
		this.tpwd = tpwd;
		this.realName = realName;
		this.email = email;
		this.lastLogDate = lastLogDate;
		this.isAdmin = isAdmin;
		this.TSubjectives = TSubjectives;
		this.TJudges = TJudges;
		this.TFiles = TFiles;
		this.TSinglesels = TSinglesels;/*
		this.TClass = TClass;*/
	}

	
	@Id
	@Column(name = "id", unique = true, nullable = false)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "tname", nullable = false, length = 16)
	public String getTname() {
		return this.tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	@Column(name = "tpwd", nullable = false, length = 32)
	public String getTpwd() {
		return this.tpwd;
	}

	public void setTpwd(String tpwd) {
		this.tpwd = tpwd;
	}

	@Column(name = "realName", length = 32)
	public String getRealName() {
		return this.realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	@Column(name = "email", length = 64)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "lastLogDate", length = 32)
	public String getLastLogDate() {
		return this.lastLogDate;
	}

	public void setLastLogDate(String lastLogDate) {
		this.lastLogDate = lastLogDate;
	}

	@Column(name = "isAdmin")
	public Integer getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Integer isAdmin) {
		this.isAdmin = isAdmin;
	}


	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "TTeacher")
	public Set<TSubjective> getTSubjectives() {
		return this.TSubjectives;
	}

	public void setTSubjectives(Set<TSubjective> TSubjectives) {
		this.TSubjectives = TSubjectives;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TTeacher")
	public Set<TJudge> getTJudges() {
		return this.TJudges;
	}

	public void setTJudges(Set<TJudge> TJudges) {
		this.TJudges = TJudges;
	}

	

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TTeacher")
	public Set<TFile> getTFiles() {
		return this.TFiles;
	}

	public void setTFiles(Set<TFile> TFiles) {
		this.TFiles = TFiles;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TTeacher")
	public Set<TSinglesel> getTSinglesels() {
		return this.TSinglesels;
	}

	public void setTSinglesels(Set<TSinglesel> TSinglesels) {
		this.TSinglesels = TSinglesels;
	}
	
	@OneToMany(mappedBy="teacher",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public Set<TestPaper> getTestPagers() {
		return TestPagers;
	}

	public void setTestPagers(Set<TestPaper> testPagers) {
		TestPagers = testPagers;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "teacher")
	public Set<TClass> getTClass() {
		return TClass;
	}


	public void setTClass(Set<TClass> tClass) {
		TClass = tClass;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "teacher")
	public Set<PracticePaper> getPracticePapers() {
		return practicePapers;
	}


	public void setPracticePapers(Set<PracticePaper> practicePapers) {
		this.practicePapers = practicePapers;
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


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


	@Override
	public String toString() {
		return "TTeacher [id=" + id + ", tname=" + tname + ", tpwd=" + tpwd
				+ ", realName=" + realName + ", email=" + email
				+ ", lastLogDate=" + lastLogDate + ", isAdmin=" + isAdmin
				+ ", TClass=" + TClass + "]";
	}

	
	

}