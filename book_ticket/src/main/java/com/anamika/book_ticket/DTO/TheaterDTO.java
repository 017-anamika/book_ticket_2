package com.anamika.book_ticket.DTO;


import lombok.Data;

@Data
public class TheaterDTO {
    private String theaterName;
    private String theaterLocation;
    private Integer theaterCapacity;
    private String theaterScreenType;


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
}
