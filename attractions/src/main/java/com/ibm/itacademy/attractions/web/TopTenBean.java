package com.ibm.itacademy.attractions.web;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

import com.ibm.itacademy.attractions.Attraction;
import com.ibm.itacademy.attractions.TopTenAttractionService;
import com.ibm.itacademy.attractions.TopTenTrailService;
import com.ibm.itacademy.attractions.Trail;

@Named
public class TopTenBean {

	@Inject
	private TopTenAttractionService attractionService;
	
	@Inject
	private TopTenTrailService trailService;
	
	private List<Attraction> attractions =Collections.emptyList();;
	
	private List<Trail> trails = Collections.emptyList();
	

	@PostConstruct
	public void init() {
		attractions=attractionService.getTopTen();
		trails=trailService.getTopTen();
	}
	
	
	public List<Attraction> getAttractions() {
		return attractions;
	}

	public void setAttractions(List<Attraction> attractions) {
		this.attractions = attractions;
	}

	public List<Trail> getTrails() {
		return trails;
	}

	public void setTrails(List<Trail> trails) {
		this.trails = trails;
	}
	
	
}
