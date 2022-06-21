package com.hanghae99.cloneproject5.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
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
    private List<TagName> nameList = new ArrayList<>();

    public Tag(String tagStrings){
        if(tagStrings.equals("")){
            return;
        }
        String[] tagNames = tagStrings.split(",");

        for(String name : tagNames){
            TagName tagName = new TagName(name);
            this.nameList.add(tagName);
        }
    }

}
