package com.example.demo.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.example.demo.dao.JpaEntity;
import com.example.demo.model.Address;

@Repository
public class JpaEntityImpl implements JpaEntity{
	
	@PersistenceContext
    private EntityManager entityManager;
	
	@Override
	public List<Address> findAll() {
		
		return this.entityManager.createQuery("select t from Address t", Address.class).getResultList();
		
	}

	@Override
	public Address findAddress(Long addressId) {
		
		 return this.entityManager.createQuery("select t from Address t where t.addressId = ?1",Address.class)
	                .setParameter(1,addressId)
	                .getSingleResult();
	}
	
	@Override
	public Address findAddress(Long userId, String addressInfo) {
		
		 return this.entityManager.createQuery("select t from Address t where t.userId = ?1 and t.addressInfo = ?2",Address.class)
	                .setParameter(1,userId)
	                .setParameter(2,addressInfo)
	                .getSingleResult();
	}

}
