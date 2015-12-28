package com.ibm.itacademy.attractions;

//FIXME Database
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
public class JpaTrailService {
	@PersistenceContext
	private EntityManager entityManager;
	
	
	public List<Attraction> getAvailableAttraction(Trail trail) {
		String sql = "SELECT attraction.*"
					 + " FROM attraction "
					 + " LEFT OUTER JOIN trail_attraction "
					 + " ON trail_attraction.attractions_id = attraction.id"
					 + " WHERE trail_attraction.trail_id is null or  trail_attraction.trail_id !=?";
	
		Long id = trail.getId();
		
		return entityManager.createNativeQuery(
				sql, 
				Attraction.class)
				.setParameter(1, id).getResultList();
		
		/*String query = "select a from Trail as t, Attraction as a where a not member t.attractions";
		return entityManager.createQuery(query, Attraction.class)
			.getResultList();*/
		
	}
	
	
}
