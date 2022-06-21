package com.hanghae99.cloneproject5.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class TagName {

    @JsonIgnore
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column (nullable = false)
    private String name;

    @JsonIgnore
    @ManyToOne
    private Tag tagName;

    public TagName(String name){
        this.name = name;
    }
}
