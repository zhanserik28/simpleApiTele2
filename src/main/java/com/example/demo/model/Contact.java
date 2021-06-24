package com.example.demo.model;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;
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
    private String number;
    private String address;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private String gender;

    public Contact() {
    }

    public Contact(@Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters") String firstName, @Size(min = 2, max = 30, message = "Last Name should be between 2 and 30 characters") String lastName, @Min(value = 0, message = "Age should be greater than 0") String number, String address, LocalDate date, String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.number = number;
        this.address = address;
        this.date = date;
        this.gender = gender;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", number='" + number + '\'' +
                ", address='" + address + '\'' +
                ", date=" + date +
                ", gender='" + gender + '\'' +
                '}';
    }
}
