package com.ibm.itacademy.attractions;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

@Stateful
public class JpaTrailCommentDao implements TrailCommentDao{

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TrailComment> trailComments() {
		String query = "SELECT trailComment FROM TrailComment trailComment";
		return entityManager
			.createQuery(query)
			.getResultList();
	}
	
	@Override
	public void saveOrUpdate(TrailComment trailComment) {
		entityManager.persist(trailComment);
	}
}
