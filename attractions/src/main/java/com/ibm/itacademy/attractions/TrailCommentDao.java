package com.ibm.itacademy.attractions;

import java.util.List;

public interface TrailCommentDao {
	List<TrailComment> trailComments();
	void saveOrUpdate(TrailComment trailComment);
}
