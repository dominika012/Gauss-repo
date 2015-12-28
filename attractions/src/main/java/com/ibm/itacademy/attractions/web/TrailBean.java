package com.ibm.itacademy.attractions.web;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.Param;
import org.omnifaces.cdi.ViewScoped;

import com.ibm.itacademy.attractions.Attraction;
import com.ibm.itacademy.attractions.AttractionComment;
import com.ibm.itacademy.attractions.AttractionCommentDao;
import com.ibm.itacademy.attractions.AttractionDao;
import com.ibm.itacademy.attractions.Trail;
import com.ibm.itacademy.attractions.TrailComment;
import com.ibm.itacademy.attractions.TrailCommentDao;
import com.ibm.itacademy.attractions.TrailDao;
import com.ibm.itacademy.attractions.JpaTrailService;

import ch.qos.logback.core.net.SyslogOutputStream;


@Named
@ViewScoped
public class TrailBean implements Serializable {
	@Inject
	private TrailDao trailDao;
	
	@Inject
	private transient AttractionDao attractionDao;
	
	@Inject
	private transient TrailCommentDao trailCommentDao;
	
	private TrailComment comment;
	
	private List<TrailComment> comments;
	
	@Inject
	private JpaTrailService jpaTrailService;
	
	@Inject
	@Param
	private Long trailId;
	
	private Trail trail;
	
	private Long selectedAttractionId;

	private List<Attraction> availableAttractions;
	
	
	@PostConstruct
	public void init() {
		this.trail = trailDao.findById(trailId);	
		comments = trailCommentDao.trailComments();
		this.comment = new TrailComment();
		availableAttractions = jpaTrailService.getAvailableAttraction(trail);
	}
	
	public String submit() {
		comment.setIdTrail(trailId);
		trailCommentDao.saveOrUpdate(comment);
		init();
		return "trail?faces-redirect=true&trailId=" + trailId;
	}
	
	public String assignAttraction() {
		Attraction attraction = attractionDao.findById(selectedAttractionId);
		trail.addAttraction(attraction);
		trailDao.saveOrUpdate(trail);
		
		return "trail?faces-redirect=true&trailId=" + trailId;
	}

	public String goToTrails(){
		return "trails?faces-redirect=true";
	}
	
	public Long getTrailId() {
		return trailId;
	}

	public void setTrailId(Long trailId) {
		this.trailId = trailId;
	}

	public Trail getTrail() {
		return trail;
	}

	public void setTrail(Trail trail) {
		this.trail = trail;
	}

	public Long getSelectedAttractionId() {
		return selectedAttractionId;
	}

	public void setSelectedAttractionId(Long selectedAttractionId) {
		this.selectedAttractionId = selectedAttractionId;
	}

	public List<Attraction> getAvailableAttractions() {
		return availableAttractions;
	}

	public void setAvailableAttractions(List<Attraction> availableAttractions) {
		this.availableAttractions = availableAttractions;
	}

	public TrailComment getComment() {
		return comment;
	}

	public void setComment(TrailComment comment) {
		this.comment = comment;
	}

	public List<TrailComment> getComments() {
		return comments;
	}

	public void setComments(List<TrailComment> comments) {
		this.comments = comments;
	}
	
	
	
	
}
