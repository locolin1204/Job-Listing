package com.colinlo.joblisting.repository.impl;

import com.colinlo.joblisting.model.JobPost;
import com.colinlo.joblisting.repository.JobPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JobPostRepositoryImpl implements JobPostRepository {
    @Autowired
    MongoTemplate mongoTemplate;

    String jobPostCollectionName = "JobPost";

    @Override
    public List<JobPost> getAllPosts() {
        return mongoTemplate.findAll(JobPost.class, jobPostCollectionName);
    }

    @Override
    public List<JobPost> searchPost(String text) {
        // TODO
//        return mongoTemplate.findBy(JobPost.class, jobPostCollectionName);
    }

    @Override
    public JobPost addPost(JobPost post) {
        return mongoTemplate.insert(post);
    }

    @Override
    public void deletePost(String id) {
        // TODO
//        return mongoTemplate.
    }

    @Override
    public JobPost editPost(JobPost post) {
return mongoTemplate.save(post);
    }
}