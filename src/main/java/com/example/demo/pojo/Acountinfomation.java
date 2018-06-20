package com.example.demo.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Acountinfomation {
    private Integer id;

    private String name;

    private String phoneNum;

    private String privilege;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPhonenum() {
        return phoneNum;
    }

    public void setPhonenum(String phonenum) {
        this.phoneNum = phonenum == null ? null : phonenum.trim();
    }

    public String getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege == null ? null : privilege.trim();
    }

	@Override
	public String toString() {
		return "Acountinfomation [id=" + id + ", name=" + name + ", phonenum=" + phoneNum + ", privilege=" + privilege
				+ "]";
	}
    
    
}