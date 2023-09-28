package com.colinlo.joblisting.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

@CompoundIndex(def="{'profile': 'text', 'desc': 'text', 'techs': 'text'}")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "jobPosts")
public class JobPost {

    @Id
    private String id;
    @TextIndexed
    private String profile;
    @TextIndexed
    private String desc;
    private int exp;
    @TextIndexed
    private String techs[];

    public JobPost(String profile, String desc, int exp, String[] techs) {
        this.profile = profile;
        this.desc = desc;
        this.exp = exp;
        this.techs = techs;
    }
}
