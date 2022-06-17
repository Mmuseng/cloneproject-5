package com.hanghae99.cloneproject5.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Tag { // 고민해봅시다.

    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @OneToMany(mappedBy = "tagName")
    private List<TagName> nameList;

}
