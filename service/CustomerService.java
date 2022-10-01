package service;

import java.util.*;
import java.lang.*;
import model.*;

public class CustomerService{

    private static Collection<Customer> customers = new HashSet<>();

    public static void addCustomer(String email, String firstName, String lastName) throws Exception{
        Customer customer = new Customer(firstName, lastName, email);
        // TODO check if email already used
        customers.add(customer);
        //try{
        //    Customer customer = new Customer(firstName, lastName, email);
        //    customers.put(customer.email, customer);
        //}catch (IllegalArgumentException ex){
        //    System.out.println(ex.getLocalizedMessage()); 
        //}
    }
    
    public static Customer getCustomer(String customerEmail) throws Exception{
        for (Customer cus : customers){
            if (cus.email.equals(customerEmail)){
                return cus;
            }
        }
        throw new IllegalArgumentException("invalid customer email!");
        // not sure what to return if invalid input
    }
        
    public static Collection<Customer> getAllCustomers(){
        Collection<Customer> customers2 = new HashSet<>(customers);
        return customers2;
    }
}
