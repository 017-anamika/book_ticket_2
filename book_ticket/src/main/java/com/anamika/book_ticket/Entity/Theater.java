package com.anamika.book_ticket.Entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Theater {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private String theaterName;
    private String theaterLocation;
    private Integer theaterCapacity;
    private String theaterScreenType;

    @OneToMany(mappedBy = "theater",fetch = FetchType.LAZY)
    private List<showEntity> showEntity;


    public Integer getTheaterCapacity() {
        return theaterCapacity;
    }
    public void setTheaterCapacity(Integer theaterCapacity) {
        this.theaterCapacity = theaterCapacity;
    }
    public String getTheaterScreenType() {
        return theaterScreenType;
    }
    public void setTheaterScreenType(String theaterScreenType) {
        this.theaterScreenType = theaterScreenType;
    }
    public String getTheaterName() {
        return theaterName;
    }
    public void setTheaterName(String theaterName) {
        this.theaterName = theaterName;
    }
    public String getTheaterLocation() {
        return theaterLocation;
    }
    public void setTheaterLocation(String theaterLocation) {
        this.theaterLocation = theaterLocation;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public List<showEntity> getShowEntity() {
        return showEntity;
    }
    public void setShowEntity(List<showEntity> showEntity) {
        this.showEntity = showEntity;
    }

}
