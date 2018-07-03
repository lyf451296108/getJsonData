package com.example.demo.dao;

import java.util.List;

import com.example.demo.model.Address;

public interface JpaEntity {
	
	List<Address> findAll();

    Address findAddress(Long addressId);

	Address findAddress(Long userId, String addressInfo);
}
