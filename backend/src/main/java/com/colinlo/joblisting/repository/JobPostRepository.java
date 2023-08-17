package com.colinlo.joblisting.repository;

import com.colinlo.joblisting.model.JobPost;

import java.util.ArrayList;
import java.util.List;

public interface JobPostRepository {
    List<JobPost> getAllPosts();
    List<JobPost> searchPost(String text);
    JobPost addPost(JobPost post);
    void deletePost(String id);
    JobPost editPost(String id, JobPost post) throws Exception;
    List<String> getAllTechs();
    List<JobPost> filterTechPosts(ArrayList<String> techList);
    List<JobPost> searchAndFilterPost(String text, ArrayList<String> chosenTechList);
}
