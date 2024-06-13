package com.hotelbooker.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Table(name="reserve")
@Entity(name="reserve")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "createdAt", "updatedAt"})
public class Reserve {


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


    @JoinColumn(name = "hotel_id")
    @ManyToOne(targetEntity = Hotel.class, fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value = {"id", "rooms", "createdAt", "updated_at"})
    private Hotel hotel;

    public Hotel getHotel() {
        return this.hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    @JoinColumn(name = "room_id")
    @ManyToOne(targetEntity = Room.class, fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value = {"id", "createdAt", "updatedAt", "hotel", "reserves"})
    private Room room;

    public Room getRoom() {
        return this.room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }


    //@NotNull(message = "Check-in date cannot be null")
    @Column(name = "date_check_in")
    private LocalDate checkInDate;
    public LocalDate getCheckInDate() {
        return this.checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    @Column(name = "date_check_out")
    private LocalDate checkOutDate;

    public LocalDate getCheckOutDate() {
        return this.checkOutDate;
    }
    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
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

    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }


}
