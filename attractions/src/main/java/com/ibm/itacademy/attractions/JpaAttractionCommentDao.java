package com.ibm.itacademy.attractions;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

@Stateful
public class JpaAttractionCommentDao implements AttractionCommentDao{

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<AttractionComment> attractionComments() {
		String query = "SELECT attractionComment FROM AttractionComment attractionComment";
		return entityManager
			.createQuery(query)
			.getResultList();
	}
	
	@Override
	public void saveOrUpdate(AttractionComment attractionComment) {
		entityManager.persist(attractionComment);
	}
}
