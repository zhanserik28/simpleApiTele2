package com.example.demo.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Integer id;
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String firstName;
    @Size(min = 2, max = 30, message = "Last Name should be between 2 and 30 characters")
    private String lastName;
    @Min(value = 0, message = "Age should be greater than 0")
    private Integer age;
    private String number;
    private String address;
    private String city;

    public Contact() {
    }

    public Contact( @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters") String firstName, @Size(min = 2, max = 30, message = "Last Name should be between 2 and 30 characters") String lastName, @Min(value = 0, message = "Age should be greater than 0") Integer age, String number) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.number = number;
    }

    public Contact( String firstName, String lastName, @Min(value = 0, message = "Age should be greater than 0") Integer age, String number, String address, String city) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.number = number;
        this.address = address;
        this.city = city;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", number='" + number + '\'' +
                '}';
    }
}
