package com.mycompany.Model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idc;

    @Column(nullable = false,unique = true,length = 50)
    private String namec;

    public Course(String namec) {
        this.namec = namec;
    }

    public Course() {

    }

    public Integer getId() {
        return idc;
    }

    public void setId(Integer id) {
        this.idc = id;
    }

    public String getNamec() {
        return namec;
    }

    public void setNamec(String namec) {
        this.namec = namec;
    }
    @ManyToMany(mappedBy = "courses")
    private List<User> users;

    @Override
    public String toString() {
        return "course{" +
                "id=" + idc +
                ", namec='" + namec + '\'' +
                '}';
    }
}
