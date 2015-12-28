package com.ibm.itacademy.attractions.web;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

import com.ibm.itacademy.attractions.Attraction;
import com.ibm.itacademy.attractions.AttractionDao;

@Named
@ViewScoped
public class AttractionsBean implements Serializable{

		@Inject
		private AttractionDao attractionDao;
		
		private Attraction attraction;
		
		private List<Attraction> attractions = new LinkedList<Attraction>();
		
		private String selectedType;
		
		@PostConstruct
		public void refresh() {
			
			this.attractions = attractionDao.list();
			this.attraction = new Attraction();
		}


		public void setAttraction(Attraction attraction) {
			this.attraction = attraction;
		}
		
		

		public Attraction getAttraction() {
			return attraction;
		}


		public void setAttractions(List<Attraction> attractions) {
			this.attractions = attractions;
		}


		public List<Attraction> getAttractions() {
			return attractions;
		}

		public void submit() {
			attraction.setType(selectedType);
			attractionDao.save(attraction);
			refresh();
		}

		
		//FIXME
		public void delete(){
			attractionDao.delete(attraction);
			
			refresh();
		}

		public String getSelectedType() {
			return selectedType;
		}


		public void setSelectedType(String selectedType) {
			this.selectedType = selectedType;
		}
}
