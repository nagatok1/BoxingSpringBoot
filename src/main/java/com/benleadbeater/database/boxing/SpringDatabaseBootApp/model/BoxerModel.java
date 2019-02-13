package com.benleadbeater.database.boxing.SpringDatabaseBootApp.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "boxers")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "creationDate", "lastModified" }, allowGetters = true)
public class BoxerModel implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Boxer_ID")
	private Long id;

	@Column(name = "Name")
	private String name;

	@Column(name = "Status")
	private String status;

	@Column(name = "Age")
	private int age;

	@Column(name = "DOB")
	private String dob;

	@Column(name = "Nationality")
	private String nationality;

	@Column(name = "Weight_Division")
	private String weight;

	@Column(name = "Stance")
	private String stance;

	@Column(name = "Current_Location")
	private String location;

	@Column(name = "Birthplace")
	private String birthplace;

	public BoxerModel(String name, String status, int age, String dob,
			String nationality, String weight, String stance, String location, String birthplace) {
		this.name = name;
		this.status = status;
		this.age = age;
		this.dob = dob;
		this.nationality = nationality;
		this.weight = weight;
		this.stance = stance;
		this.location = location;
		this.birthplace = birthplace;
	}

	public BoxerModel() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getStance() {
		return stance;
	}

	public void setStance(String stance) {
		this.stance = stance;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getBirthplace() {
		return birthplace;
	}

	public void setBirthplace(String birthplace) {
		this.birthplace = birthplace;
	}

}
