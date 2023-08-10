package com.colinlo.joblisting.repository.impl;

import com.colinlo.joblisting.model.JobPost;
import com.colinlo.joblisting.repository.JobPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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
        Criteria criteria = new Criteria();
        criteria.orOperator(
                Criteria.where("profile").regex(text, "i"),
                Criteria.where("desc").regex(text, "i"),
                Criteria.where("techs").regex(text, "i")
        );
        Query query = new Query(criteria);
        return mongoTemplate.find(query, JobPost.class, jobPostCollectionName);
    }

    @Override
    public JobPost addPost(JobPost post) {
        return mongoTemplate.insert(post, jobPostCollectionName);
    }

    @Override
    public void deletePost(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query, JobPost.class, jobPostCollectionName);
    }

    @Override
    public JobPost editPost(JobPost post) {
return mongoTemplate.save(post, jobPostCollectionName);
    }
}