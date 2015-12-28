package com.ibm.itacademy.attractions;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
public class JpaAccountDao implements AccountDao{

	@PersistenceContext
	private EntityManager entityManager;
	

	@Override
	public void saveOrUpdate(Account account) {
		entityManager.persist(account);
	}
	
}
