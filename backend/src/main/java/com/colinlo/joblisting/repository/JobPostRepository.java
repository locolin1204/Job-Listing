package com.colinlo.joblisting.repository;

import com.colinlo.joblisting.model.JobPost;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.ArrayList;
import java.util.List;

public interface JobPostRepository {
    // GET Posts
    List<JobPost> getAllPosts();
    List<String> getAllTechs();
    JobPost getPostById(String id);

    // GET Criteria
    Criteria getCriteriaById(String id);
    Criteria getCriteriaByText(String text);
    Criteria getCriteriaByTechList(ArrayList<String> chosenTechList);
    List<JobPost> getPostByCriteria(Criteria ...criteriaList);

    // CHANGE Posts
    JobPost editPost(JobPost post) throws Exception;
    JobPost createPost(JobPost post);
    void deletePost(String id);
}
