package com.ibm.itacademy.attractions;

import java.util.List;

public interface TrailDao {
	List<Trail> list();
	
	void saveOrUpdate(Trail trail);
	
	Trail findById(Long id);
	
	void delete(Trail trail);
	
	
}
