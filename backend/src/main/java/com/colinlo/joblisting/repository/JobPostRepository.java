package com.colinlo.joblisting.repository;

import com.colinlo.joblisting.model.JobPost;

import java.util.List;

public interface JobPostRepository {
    List<JobPost> getAllPosts();
    List<JobPost> searchPost(String text);
    JobPost addPost(JobPost post);
    void deletePost(String id);
    JobPost editPost( JobPost post);
}
