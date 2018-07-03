package com.example.demo.mapper;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {

    List<Address> findByUserId(Long userId);

    Address findByAddressIdAndUserId(Long addressId,Long userId);

    @Query(value = "select  t from Address t where t.addressInfo = :addressInfo")
    Address findAddress(@Param("addressInfo") String addressInfo);
}
