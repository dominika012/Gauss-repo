package com.ibm.itacademy.attractions;

import java.util.List;

public interface AttractionCommentDao {

	void saveOrUpdate(AttractionComment attractionComment);
	
	List<AttractionComment> attractionComments();
}
