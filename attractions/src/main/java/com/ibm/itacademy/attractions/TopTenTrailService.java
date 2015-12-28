package com.ibm.itacademy.attractions;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class TopTenTrailService {
	@PersistenceContext
	private EntityManager entityManager;
	
	public List<Trail> getTopTen() {
		String sql = "SELECT TOP 10 * FROM (SELECT COUNT(trailcomment.idtrail) as counter, trail.*"
				+ " FROM trail LEFT OUTER JOIN trailcomment "
				+ " ON trailcomment.idtrail=trail.id "
				+ " WHERE trailcomment.idtrail is not null "
				+ " GROUP BY trailcomment.idtrail, trail.id, trail.description "
				+ " ORDER BY description ASC) "
				+ " ORDER BY counter DESC";
				
	return entityManager.createNativeQuery(
			sql, 
			Trail.class)
			.getResultList();
	}
}
