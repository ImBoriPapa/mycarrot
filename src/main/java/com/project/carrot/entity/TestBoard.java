package com.project.carrot.entity;

import com.project.carrot.entity.locationItem.City;
import com.project.carrot.entity.locationItem.District;
import com.project.carrot.entity.locationItem.Town;

import javax.persistence.*;

@Entity
@Table(name="TEST_BOARD")
public class TestBoard {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String title;

    @Embedded
    Location location;

    public TestBoard() {}

    public TestBoard(Long id, String name, String title, Location location) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
