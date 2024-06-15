package com.hotelbooker.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name="rooms")
@Entity(name="rooms")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "createdAt", "updatedAt", "hotel"})
public class Room {

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
    @NotNull(message = "type cannot be null")
    @NotBlank(message = "type cannot be empty")
    @Column(name="type")
    private String type;

    public void setType(String type){
        this.type = type;
    }

    public String getType() {
        return this.type;
    }
    @NotNull(message="daily_price cannot be null")
    @Column(name="daily_price")
    private Float dailyPrice;
    public Float getDailyPrice() {
        return this.dailyPrice;
    }
    public void setDailyPrice(float dailyPrice) {
        this.dailyPrice = dailyPrice;
    }


    @ManyToOne()
    @JoinColumn(name = "hotel_id")
    @JsonIgnoreProperties({"rooms", "updated_at", "createdAt", "id"})
    private Hotel hotel;
    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    @Column(name="image")
    private String image;
    public String getImage() {
        return this.image;
    }
    public void setImage(String image) {
        this.image = image;
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


    public void setDailyPrice(Float dailyPrice) {
        this.dailyPrice = dailyPrice;
    }


    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"hotel", "room"})
    private List<Reserve> reserves = new ArrayList<>();
    public List<Reserve> getReserves() {
        return reserves;
    }

    public void setReserves(List<Reserve> reserves) {
        this.reserves = reserves;
    }

}
