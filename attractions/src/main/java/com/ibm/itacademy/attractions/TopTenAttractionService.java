package com.ibm.itacademy.attractions;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class TopTenAttractionService {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	
	public List<Attraction> getTopTen() {
		String sql = "SELECT TOP 10 * FROM (SELECT COUNT(attractioncomment.idattraction) as counter, attraction.*"
				+ " FROM attraction LEFT OUTER JOIN attractioncomment "
				+ " ON attractioncomment.idattraction=attraction.id "
				+ " WHERE attractioncomment.idattraction is not null "
				+ " GROUP BY attractioncomment.idattraction, attraction.id, attraction.description, attraction.name "
				+ " ORDER BY name ASC) "
				+ " ORDER BY counter DESC";
				
	return entityManager.createNativeQuery(
			sql, 
			Attraction.class)
			.getResultList();
	}
}
