package com.ibm.itacademy.attractions;

import java.util.Collections;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Trail {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String description;
		
	@ManyToMany
	private List<Attraction> attractions = Collections.emptyList();

	@OneToMany
	private List<TrailComment> comments = Collections.emptyList();
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Attraction> getAttractions() {
		return attractions;
	}

	public void setAttractions(List<Attraction> attractions) {
		this.attractions = attractions;
	}

	public void addAttraction(Attraction attraction) {
		this.attractions.add(attraction);
	}

	public List<TrailComment> getComments() {
		return comments;
	}

	public void setComments(List<TrailComment> comments) {
		this.comments = comments;
	}

	

	
}
