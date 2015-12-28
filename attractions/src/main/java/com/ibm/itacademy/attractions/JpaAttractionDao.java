package com.ibm.itacademy.attractions;

import java.util.List;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;


@Stateful
public class JpaAttractionDao implements AttractionDao{
	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Attraction> list() {
		String query = "SELECT attraction FROM Attraction attraction";
		return entityManager
			.createQuery(query)
			.getResultList();
	}

	@Override
	public void save(Attraction attraction) {
		entityManager.persist(attraction);
	}
	
	@Override
	public void update(Attraction attraction) {
		entityManager.merge(attraction);
	}

	@Override
	public Attraction findById(Long id) {
		return entityManager.find(Attraction.class, id);
	}
	@Override
	public void delete(Attraction attraction) {
		entityManager.remove(attraction);
	}
	
	
}
