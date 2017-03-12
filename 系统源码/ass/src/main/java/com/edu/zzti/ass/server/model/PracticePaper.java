package com.edu.zzti.ass.server.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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
import com.edu.zzti.ass.testlibrary.model.Judge;
import com.edu.zzti.ass.testlibrary.model.Singlesel;
import com.edu.zzti.ass.testlibrary.model.Subjective;
import com.edu.zzti.ass.testlibrary.model.TUnit;

@Entity
@Table(name = "t_practicepaper", catalog = "ass")
public class PracticePaper {

	
	private Integer id;
	private String pagerName;
	private List<Singlesel> singlesels = new ArrayList<Singlesel>();
	private List<Judge> judges = new ArrayList<Judge>();
	private List<Subjective> subjective = new ArrayList<Subjective>();
	private Date CreateDate;
	private TTeacher teacher;
	private TUnit unit;
	private Date answerDate;
	private int isRelease;
	private PracticeAnswer PracticeAnswer;

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

	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name = "t_pra_single", 
				joinColumns = { @JoinColumn(name = "paperId") }, 
				inverseJoinColumns = { @JoinColumn(name = "singleselId") })
	public List<Singlesel> getSinglesels() {
		return singlesels;
	}

	public void setSinglesels(List<Singlesel> singlesels2) {
		this.singlesels = singlesels2;
	}

	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name = "t_pra_judge", 
				joinColumns = { @JoinColumn(name = "paperId") }, 
				inverseJoinColumns = { @JoinColumn(name = "judgeId") })
	public List<Judge> getJudges() {
		return judges;
	}

	public void setJudges(List<Judge> judges) {
		this.judges = judges;
	}

	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name = "t_pra_subj", 
				joinColumns = { @JoinColumn(name = "paperId") }, 
				inverseJoinColumns = { @JoinColumn(name = "subjectiveId") })
	public List<Subjective> getSubjective() {
		return subjective;
	}

	public void setSubjective(List<Subjective> subjective) {
		this.subjective = subjective;
	}

	public Date getCreateDate() {
		return CreateDate;
	}

	public void setCreateDate(Date createDate) {
		CreateDate = createDate;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "teacherId")
	public TTeacher getTeacher() {
		return teacher;
	}

	public void setTeacher(TTeacher teacher) {
		this.teacher = teacher;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "unitId")
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
	public PracticeAnswer getPracticeAnswer() {
		return PracticeAnswer;
	}

	public void setPracticeAnswer(PracticeAnswer practiceAnswer) {
		PracticeAnswer = practiceAnswer;
	}
	

}
