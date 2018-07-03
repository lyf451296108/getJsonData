package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name= "tp_address")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Address {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long addressId;

    @Column(name = "user_id",nullable = false)
    private Long userId;

    @Column(name = "add_info",nullable = false)
    private String addressInfo;
    
    public Address(Long userId, String addressInfo) {
		this.userId = userId;
		this.addressInfo = addressInfo;
	}
}
