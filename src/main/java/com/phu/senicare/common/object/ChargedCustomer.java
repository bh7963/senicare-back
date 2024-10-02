package com.phu.senicare.common.object;

import java.util.ArrayList;
import java.util.List;

import com.phu.senicare.entity.CustomerEntity;

import lombok.Getter;

@Getter
public class ChargedCustomer {

    private Integer customerNumber;
    private String name;
    private String birth;
    private String location;

    private ChargedCustomer (CustomerEntity customerEntity) {
        this.customerNumber = customerEntity.getCustomerNumber();
        this.name = customerEntity.getName();
        this.birth = customerEntity.getBirth();
        this.location = customerEntity.getLocation();
    }

    public static List<ChargedCustomer> getList (List<CustomerEntity> customerEntities){
        List<ChargedCustomer> customers = new ArrayList<>();
        for (CustomerEntity customerEntity: customerEntities){
            ChargedCustomer customer = new ChargedCustomer(customerEntity);
            customers.add(customer);
        };

        return customers;
    }
    
}
