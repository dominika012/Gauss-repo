package com.ibm.itacademy.attractions;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class TrailComment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Long idTrail;
	
	private String text;

	@ManyToOne
	private Trail trail;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdAttraction() {
		return idTrail;
	}

	public void setIdAttraction(Long idAttraction) {
		this.idTrail = idAttraction;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Long getIdTrail() {
		return idTrail;
	}

	public void setIdTrail(Long idTrail) {
		this.idTrail = idTrail;
	}

	public Trail getTrail() {
		return trail;
	}

	public void setTrail(Trail trail) {
		this.trail = trail;
	}
	
	
}
