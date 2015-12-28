package com.ibm.itacademy.attractions.web;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.Param;
import org.omnifaces.cdi.ViewScoped;

import com.ibm.itacademy.attractions.Attraction;
import com.ibm.itacademy.attractions.AttractionComment;
import com.ibm.itacademy.attractions.AttractionCommentDao;
import com.ibm.itacademy.attractions.AttractionDao;
import com.ibm.itacademy.attractions.auth.LoginService;

@Named
@ViewScoped
public class AttractionBean implements Serializable {
	@Inject
	private AttractionDao attractionDao;
	
	@Inject
	private transient AttractionCommentDao attractionCommentDao;

	private Attraction attraction;

	private AttractionComment attractionComment;

	private List<AttractionComment> comments;

	private String date;
	
	@Inject
	@Param
	private Long id;

	private String selectedType;
	private String type;
	private String description;
	
	
	@PostConstruct
	public void refresh() {
		this.attraction = attractionDao.findById(id);
		description = attraction.getDescription();
		type = attraction.getType();
		comments = attractionCommentDao.attractionComments();
		this.attractionComment = new AttractionComment();
	}

	public String getCurrentDate(){
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	public Attraction getAttraction() {
		return attraction;
	}

	public void setAttraction(Attraction attraction) {
		this.attraction = attraction;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSelectedType() {
		return selectedType;
	}

	public void setSelectedType(String selectedType) {
		this.selectedType = selectedType;
	}

	public String submit() {
		refresh();
		
		attractionComment.setAttraction(attraction);
		attractionComment.setIdAttraction(id);
		date=getCurrentDate();
		attractionComment.setDate(date);
		System.out.println("cooooomeeent teeeext  "+ attractionComment.getText());
		attractionCommentDao.saveOrUpdate(attractionComment);
		
		return "attraction?faces-redirect=true&id=" + id;
	}

	
	public String goToAttraction(){
		return "attraction?faces-redirect=true&id=" + id;
	}
	
	public AttractionComment getComment() {
		return attractionComment;
	}

	public void setComment(AttractionComment comment) {
		this.attractionComment = comment;
	}

	public List<AttractionComment> getComments() {
		return comments;
	}

	public void setComments(List<AttractionComment> comments) {
		this.comments = comments;
	}

	
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String goToAttractions() {
		return "attractions?faces-redirect=true";
	}

	public String edit() {
		return "editAttraction?faces-redirect=true&id=" + id;
	}
	public String sedit() {
		attraction.setType(selectedType);
		attraction.setDescription(description);
		attractionDao.update(attraction);
		
		return "attraction?faces-redirect=true&id=" + id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public AttractionComment getAttractionComment() {
		return attractionComment;
	}

	public void setAttractionComment(AttractionComment attractionComment) {
		this.attractionComment = attractionComment;
	}

	
}
