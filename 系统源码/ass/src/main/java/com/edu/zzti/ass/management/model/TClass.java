package com.edu.zzti.ass.management.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * TClass entity. 
 */
@Entity
@Table(name = "t_class", catalog = "ass")
public class TClass implements java.io.Serializable {



	private static final long serialVersionUID = 700089111466773298L;
	
	private Integer id;
	private String name;
	private Set<TStudent> TStudents = new HashSet<TStudent>(0);
	private TTeacher teacher;


	public TClass() {
	}

	public TClass(String name) {
		this.name = name;
	}

	public TClass(String name, Set<TStudent> TStudents) {
		this.name = name;
		this.TStudents = TStudents;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TClass")
	public Set<TStudent> getTStudents() {
		return this.TStudents;
	}

	public void setTStudents(Set<TStudent> TStudents) {
		this.TStudents = TStudents;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	@JoinColumn(name = "teacher_id")
	public TTeacher getTeacher() {
		return teacher;
	}

	public void setTeacher(TTeacher teacher) {
		this.teacher = teacher;
	}



	
}