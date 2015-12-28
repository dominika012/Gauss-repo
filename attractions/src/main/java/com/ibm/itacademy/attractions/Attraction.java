package com.ibm.itacademy.attractions;

import java.util.Collections;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.ibm.itacademy.attractions.Trail;

@Entity
public class Attraction {
	
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Long id;
		
		private String name;
		
		private String type;
		
		private String description;

		
		public Long getId() {
			return id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		@ManyToMany
		private List<Trail> trails = Collections.emptyList();
		
		@OneToMany
		private List<AttractionComment> comments = Collections.emptyList();
		
		public void setId(Long id) {
			this.id = id;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public List<Trail> getTrails() {
			return trails;
		}

		public void setTrails(List<Trail> trails) {
			this.trails = trails;
		}

		public List<AttractionComment> getComments() {
			return comments;
		}

		public void setComments(List<AttractionComment> comments) {
			this.comments = comments;
		}

		

		
}
