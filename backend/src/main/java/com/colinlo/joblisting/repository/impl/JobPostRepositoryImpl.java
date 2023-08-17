package com.colinlo.joblisting.repository.impl;

import com.colinlo.joblisting.model.JobPost;
import com.colinlo.joblisting.repository.JobPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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
    public JobPost editPost(String id, JobPost post) throws Exception {
        Query query = new Query(Criteria.where("id").is(id));
        List<JobPost> jobPostList = mongoTemplate.find(query, JobPost.class, jobPostCollectionName);
        if (jobPostList.isEmpty()) {
            throw new Exception("Job Post not found, id not found.");
        }
        if (!post.getId().equals(id)) {
            throw new Exception("id in path does not match id in request body.");
        }
        return mongoTemplate.save(post, jobPostCollectionName);
    }

    @Override
    public List<String> getAllTechs() {
        Query query = new Query();
        return mongoTemplate.findDistinct(query, "techs", JobPost.class, String.class);
    }

    @Override
    public List<JobPost> filterTechPosts(ArrayList<String> techList) {
        Query query = new Query(Criteria.where("techs").all(techList));
        return mongoTemplate.find(query, JobPost.class, jobPostCollectionName);
    }

    @Override
    public List<JobPost> searchAndFilterPost(String text, ArrayList<String> chosenTechList) {
        System.out.println("text " + text + " text is empty: " + text.isEmpty());
        System.out.println("chosenTechList " + chosenTechList + " is Empty: " + chosenTechList.isEmpty());

        Criteria searchCriteria = new Criteria();
        if (!text.isEmpty()) {
            searchCriteria.orOperator(
                    Criteria.where("profile").regex(text, "i"),
                    Criteria.where("desc").regex(text, "i"),
                    Criteria.where("techs").regex(text, "i")
            );
        }

        Criteria filterCriteria = new Criteria();
        if (!chosenTechList.isEmpty()){
            filterCriteria.andOperator(Criteria.where("techs").all(chosenTechList));
        }

        Criteria criteria = new Criteria();
        criteria.andOperator(
                searchCriteria,
                filterCriteria
        );
        Query query = new Query(criteria);
        return mongoTemplate.find(query, JobPost.class, jobPostCollectionName);
    }
}