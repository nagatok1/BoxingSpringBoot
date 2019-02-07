package com.benleadbeater.database.hello.mySpringDatabaseBootApp.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "champions")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "creationDate", "lastModified" }, allowGetters = true)
public class ChampionModel implements Serializable {

	@Column(name = "Boxer")
	private String boxer;

	@Column(name = "WeightClass")
	private String weightclass;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Title_Id")
	private Long titleid;

	@Column(name = "Title")
	private String title;

	public ChampionModel(String boxer, String weightclass, String title) {
		this.boxer = boxer;
		this.weightclass = weightclass;
		this.title = title;
	}

	public ChampionModel() {

	}

	public String getBoxer() {
		return boxer;
	}

	public void setBoxer(String boxer) {
		this.boxer = boxer;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWeightclass() {
		return weightclass;
	}

	public void setWeightclass(String weightclass) {
		this.weightclass = weightclass;
	}

	public Long getTitleid() {
		return titleid;
	}

	public void setTitleid(Long titleid) {
		this.titleid = titleid;
	}
}
