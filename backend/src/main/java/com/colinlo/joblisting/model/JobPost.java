package com.colinlo.joblisting.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

@CompoundIndex(def="{'profile': 'text', 'desc': 'text', 'techs': 'text'}")
@Data
@Document(collection = "JobPost")
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

//    public String getProfile() {
//        return profile;
//    }
//
//    public void setProfile(String profile) {
//        this.profile = profile;
//    }
//
//    public String getDesc() {
//        return desc;
//    }
//
//    public void setDesc(String desc) {
//        this.desc = desc;
//    }
//
//    public int getExp() {
//        return exp;
//    }
//
//    public void setExp(int exp) {
//        this.exp = exp;
//    }
//
//    public String[] getTechs() {
//        return techs;
//    }
//
//    public void setTechs(String[] techs) {
//        this.techs = techs;
//    }

    public JobPost() {
    }

    public JobPost(String profile, String desc, int exp, String[] techs) {
        this.profile = profile;
        this.desc = desc;
        this.exp = exp;
        this.techs = techs;
    }
}
