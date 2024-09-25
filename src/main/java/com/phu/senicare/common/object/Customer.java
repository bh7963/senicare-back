package com.phu.senicare.common.object;

import java.util.List;
import java.util.ArrayList;
import com.phu.senicare.repository.resultSet.GetCustomersResultSet;

import lombok.Getter;

@Getter
public class Customer {
    
    private Integer customerNumber;
    private String name;
    private String birth;
    private String location;
    private String chargetName;
    private String chargerId;
    

    private Customer(GetCustomersResultSet resultSet) {
        this.customerNumber = resultSet.getCustomerNumber();
        this.name = resultSet.getName();
        this.birth = resultSet.getBirth();
        this.location = resultSet.getLocation();
        this.chargetName = resultSet.getChargerName();
        this.chargerId = resultSet.getChargerId();
    }

    public static List<Customer> getList(List<GetCustomersResultSet> resultSets) {
        List<Customer> customers = new ArrayList<>();
        for (GetCustomersResultSet resultSet: resultSets){
            Customer customer = new Customer(resultSet);
            customers.add(customer);
        }
        return customers;
    }
}
