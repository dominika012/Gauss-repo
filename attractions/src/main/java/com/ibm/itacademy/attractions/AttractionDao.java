package com.ibm.itacademy.attractions;

import java.util.List;



public interface AttractionDao {
	List<Attraction> list();
	
	void save(Attraction attraction);
	
	void update(Attraction attraction);
	
	Attraction findById(Long id);
	
	void delete(Attraction attraction);
	
}
