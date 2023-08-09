package com.colinlo.joblisting.repository;

import com.colinlo.joblisting.model.JobPost;

import java.util.List;

//public interface JobPostRepository extends MongoRepository<JobPost, String> {
//    @Query("{ $or: [ { 'profile': { $regex: ?0, $options: 'i' } }, { 'desc': { $regex: ?0, $options: 'i' } }, { 'techs': { $regex: ?0, $options: 'i' } } ] }")
//    List<JobPost> findByText(String text);
//}

public interface JobPostRepository {
    List<JobPost> getAllPosts();
    List<JobPost> searchPost(String text);
    JobPost addPost(JobPost post);
    void deletePost(String id);
    JobPost editPost( JobPost post);
}
