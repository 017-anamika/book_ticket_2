package com.anamika.book_ticket.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String genre;
    private String description;
    private Integer duration;
    private LocalDate releaseDate;
    private String language;

    @OneToMany(mappedBy ="movie",fetch = FetchType.LAZY)
    private List<showEntity> showEntity;


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Integer getDuration() {
        return duration;
    }
    public void setDuration(Integer duration) {
        this.duration = duration;
    }
    public LocalDate getReleaseDate() {
        return releaseDate;
    }
    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
    public String getLanguage() {
        return language;
    }
    public void setLanguage(String language) {
        this.language = language;
    }
    public List<showEntity> getShowEntity() {
        return showEntity;
    }
    public void setShowEntity(List<showEntity> showEntity) {
        this.showEntity = showEntity;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

}
