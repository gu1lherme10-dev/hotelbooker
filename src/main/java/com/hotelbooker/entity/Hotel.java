package com.hotelbooker.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name="hotels")
@Entity(name="hotels")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Hotel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    @NotNull
    @NotBlank
    @Pattern(regexp = "^[a-zA-ZÀ-ÿ ]+$", message = ("Must be a string"))
    @Column(name="name")
    private String name;

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
    @NotNull(message = "address cannot be null")
    @NotBlank(message = "address cannot be empty")
    @Column(name="address")
    private String address;

    public void setAddress(String address){
        this.address = address;
    }

    public String getAddress() {
        return this.address;
    }
    @NotNull(message="Telephone cannot be null")
    @NotBlank(message="Telephone cannot be empty")
    @Column(name="telephone")
    private String telephone;
    public String getTelephone() {
        return this.telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @NotNull(message="email cannot be null")
    @NotBlank(message="email cannot be empty")
    @Email(message = "Must be a valid email")
    @Column(name="email")
    private String email;
    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @NotNull(message="state cannot be null")
    @NotBlank(message="state cannot be empty")
    @Column(name="state")
    private String state;
    public String getState() {
        return this.state;
    }
    public void setState(String state) {
        this.state = state;
    }

    @NotNull(message="country cannot be null")
    @NotBlank(message="country cannot be empty")

    @Column(name="country")
    private String country;
    public String getCountry() {
        return this.country;
    }
    public void setCountry(String country) {
        this.country = country;
    }


    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("hotel")
    private List<Room> rooms = new ArrayList<>();
    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    @CreationTimestamp
    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @CreationTimestamp
    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;

    public LocalDateTime getUpdated_at() {
        return this.updatedAt;
    }
    public void setUpdated_at(LocalDateTime updated_at) {
        this.updatedAt = updated_at;
    }


}
