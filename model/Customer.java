package model;

import java.util.regex.*;
import java.util.*;

public class Customer{
    public String firstName;
    public String lastName;
    public String email;

    public Customer(String firstName, String lastName, String email) throws Exception{
        this.firstName = firstName;
        this.lastName = lastName;

//        String emailRegex = "^(.+)@(.+).com$";
//        Pattern pattern = Pattern.compile(emailRegex);
//        if (!pattern.matcher(email).matches())
//            throw new IllegalArgumentException("email format not correct!");

        this.email = email;
    }

    @Override
    public String toString(){
        return "Customer name: " + this.firstName + " " + this.lastName + "\temail: " + this.email + "\n";
    }

    // override equal and hash
    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || this.getClass() != o.getClass()) return false;

        Customer that = (Customer) o;

        return this.firstName == that.firstName && 
               this.lastName  == that.lastName  &&
               this.email     == that.email;
    }

    @Override
    public int hashCode(){
        return Objects.hash(firstName, lastName, email);
    }
}
