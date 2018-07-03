package com.example.demo;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.dao.impl.JpaEntityImpl;
import com.example.demo.model.Address;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaEntityImplTest {

    @Autowired
    private JpaEntityImpl jpaEntity;

    @Test
    public void findAll() throws Exception {

        List<Address> list = jpaEntity.findAll();
        System.out.println(list.size());
        for (Address address : list){
            System.out.println(address.toString());
        }
    }

    @Test
    public void findAddress() throws Exception {
        Address address = jpaEntity.findAddress(9L);
        System.out.println(address.toString());
    }
    
    @Test
    public void findAddressTwo() throws Exception {
        Address address = jpaEntity.findAddress(15L, "XJ");
        System.out.println(address.toString());
    }

}
