package com.edu.zzti.ass.onlinestudy.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.edu.zzti.ass.management.model.TTeacher;
import com.edu.zzti.ass.testlibrary.model.Fill;
import com.edu.zzti.ass.testlibrary.model.Judge;
import com.edu.zzti.ass.testlibrary.model.Singlesel;
import com.edu.zzti.ass.testlibrary.model.Subjective;
import com.edu.zzti.ass.testlibrary.model.TUnit;

@Entity
@Table(name = "t_testpaper", catalog = "ass")
public class TestPaper implements Serializable {

	private static final long serialVersionUID = 1493658721626713158L;

	private Integer id;
	private String pagerName;
	private List<Singlesel> singlesels = new ArrayList<Singlesel>();
	private List<Judge> judges = new ArrayList<Judge>();
	private List<Subjective> subjective = new ArrayList<Subjective>();
	private List<Fill> fill = new ArrayList<Fill>();
	private Date createDate;
	private TTeacher teacher;
	private TUnit unit;
	private Date answerDate;
	private int isRelease;
	private TestAnswer TestAnswer;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPagerName() {
		return pagerName;
	}

	public void setPagerName(String pagerName) {
		this.pagerName = pagerName;
	}

	@ManyToMany
	@JoinTable(name = "t_page_single", 
				joinColumns = { @JoinColumn(name = "paperId") }, 
				inverseJoinColumns = { @JoinColumn(name = "singleselId") })
	public List<Singlesel> getSinglesels() {
		return singlesels;
	}

	public void setSinglesels(List<Singlesel> singlesels) {
		this.singlesels = singlesels;
	}

	@ManyToMany
	@JoinTable(name = "t_page_judge", 
				joinColumns = { @JoinColumn(name = "paperId") }, 
				inverseJoinColumns = { @JoinColumn(name = "judgeId") })
	public List<Judge> getJudges() {
		return judges;
	}

	public void setJudges(List<Judge> judges) {
		this.judges = judges;
	}

	@ManyToMany
	@JoinTable(name = "t_page_subj", 
				joinColumns = { @JoinColumn(name = "paperId") }, 
				inverseJoinColumns = { @JoinColumn(name = "subjectiveId") })
	public List<Subjective> getSubjective() {
		return subjective;
	}
	
	

	public void setSubjective(List<Subjective> subjective) {
		this.subjective = subjective;
	}

	
	@ManyToMany
	@JoinTable(name = "t_page_fill", 
				joinColumns = { @JoinColumn(name = "paperId") }, 
				inverseJoinColumns = { @JoinColumn(name = "fillId") })
	public List<Fill> getFill() {
		return fill;
	}

	public void setFill(List<Fill> fill) {
		this.fill = fill;
	}

	
	
	public Date getCreateDate() {
		return createDate;
	}

	
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "teacherId")
	public TTeacher getTeacher() {
		return teacher;
	}

	public void setTeacher(TTeacher teacher) {
		this.teacher = teacher;
	}

	@ManyToOne
	public TUnit getUnit() {
		return unit;
	}

	public void setUnit(TUnit unit) {
		this.unit = unit;
	}

	public Date getAnswerDate() {
		return answerDate;
	}

	public void setAnswerDate(Date answerDate) {
		this.answerDate = answerDate;
	}

	public int getIsRelease() {
		return isRelease;
	}

	public void setIsRelease(int isRelease) {
		this.isRelease = isRelease;
	}

	@OneToOne
	@JoinColumn(name = "answerId")
	public TestAnswer getTestAnswer() {
		return TestAnswer;
	}

	public void setTestAnswer(TestAnswer testAnswer) {
		TestAnswer = testAnswer;
	}

	

	

	
	
}
