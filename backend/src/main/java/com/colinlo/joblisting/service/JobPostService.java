package com.colinlo.joblisting.service;

import com.colinlo.joblisting.model.JobPost;

import java.util.ArrayList;
import java.util.List;

public interface JobPostService {
    String getMongoDbConnectionStatus();
    List<JobPost> getAllPosts();
    List<String> getAllTechs();
    List<JobPost> getPostBySearchTextAndTech(String text, ArrayList<String> chosenTechList); // need change
    JobPost addPost(JobPost post);
    void deletePost(String id);
    JobPost editPost(JobPost post) throws Exception;
}
