package com.colinlo.joblisting.repository.impl;

import com.colinlo.joblisting.controller.JobController;
import com.colinlo.joblisting.model.JobPost;
import com.colinlo.joblisting.repository.JobPostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class JobPostRepositoryImpl implements JobPostRepository {
    @Autowired
    MongoTemplate mongoTemplate;
    Logger logger = LoggerFactory.getLogger(JobPostRepositoryImpl.class);
    String jobPostCollectionName = "jobPosts";

    @Override
    public List<JobPost> getAllPosts() {
        logger.info("Get All Posts");
        return mongoTemplate.findAll(JobPost.class, jobPostCollectionName);
    }

    @Override
    public List<String> getAllTechs() {
        Query query = new Query();
        logger.info("Get All Techs");
        return mongoTemplate.findDistinct(query, "techs", JobPost.class, String.class);
    }

    @Override
    public JobPost getPostById(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        logger.info(String.format("Get Post with ID: %s", id));
        return mongoTemplate.findOne(query, JobPost.class, jobPostCollectionName);
    }

    @Override
    public Criteria getCriteriaById(String id) {
        logger.trace(String.format("Get Criteria with ID: %s", id));
        return Criteria.where("id").is(id);
    }

    @Override
    public Criteria getCriteriaByText(String text) {
        Criteria criteria = new Criteria();
        criteria.orOperator(
                Criteria.where("profile").regex(text, "i"),
                Criteria.where("desc").regex(text, "i"),
                Criteria.where("techs").regex(text, "i")
        );
        logger.trace(String.format("Get Criteria with Text: %s", text));
        return criteria;
    }

    @Override
    public Criteria getCriteriaByTechList(ArrayList<String> chosenTechList) {
        Criteria criteria = new Criteria();
        criteria.andOperator(Criteria.where("techs").all(chosenTechList));
        logger.trace(String.format("Get Criteria with Techs: %s", chosenTechList));
        return criteria;
    }

    @Override
    public List<JobPost> getPostByCriteria(Criteria ...criteriaList) {
        Criteria criteria = new Criteria();
        criteria.andOperator(criteriaList);
        Query query = new Query(criteria);
        logger.info(String.format("Get Post by %d Criteria", criteriaList.length));
        return mongoTemplate.find(query, JobPost.class, jobPostCollectionName);
    }

    @Override
    public JobPost createPost(JobPost post) {
        logger.info(String.format("Added Post with ID: %s", post.getId()));
        return mongoTemplate.insert(post, jobPostCollectionName);
    }

    @Override
    public void deletePost(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        logger.info(String.format("Deleted Post with ID: %s", id));
        mongoTemplate.remove(query, JobPost.class, jobPostCollectionName);
    }

    @Override
    public JobPost editPost(JobPost post) {
        logger.info(String.format("Edited Post with ID: %s", post.getId()));
        return mongoTemplate.save(post, jobPostCollectionName);
    }

//    @Override
//    public List<JobPost> getPostByTech(ArrayList<String> techList) {
//        Query query = new Query(Criteria.where("techs").all(techList));
//        return mongoTemplate.find(query, JobPost.class, jobPostCollectionName);
//    }
//
//    @Override
//    public List<JobPost> searchAndFilterPost(String text, ArrayList<String> chosenTechList) {
////        System.out.println("text " + text + " text is empty: " + text.isEmpty());
////        System.out.println("chosenTechList " + chosenTechList + " is Empty: " + chosenTechList.isEmpty());
//
//        Criteria searchCriteria = new Criteria();
//        if (!text.isEmpty()) {
//            searchCriteria.orOperator(
//                    Criteria.where("profile").regex(text, "i"),
//                    Criteria.where("desc").regex(text, "i"),
//                    Criteria.where("techs").regex(text, "i")
//            );
//        }
//
//        Criteria filterCriteria = new Criteria();
//        if (!chosenTechList.isEmpty()) {
//            filterCriteria.andOperator(Criteria.where("techs").all(chosenTechList));
//        }
//
//        Criteria criteria = new Criteria();
//        criteria.andOperator(
//                searchCriteria,
//                filterCriteria
//        );
//        Query query = new Query(criteria);
//        return mongoTemplate.find(query, JobPost.class, jobPostCollectionName);
//    }

//    public List<JobPost> getPostsByText(String text) {
//        Criteria criteria = new Criteria();
//        criteria.orOperator(
//                Criteria.where("profile").regex(text, "i"),
//                Criteria.where("desc").regex(text, "i"),
//                Criteria.where("techs").regex(text, "i")
//        );
//        Query query = new Query(criteria);
//        return mongoTemplate.find(query, JobPost.class, jobPostCollectionName);
//    }
//
//    public List<JobPost> getPostsByTechList(ArrayList<String> chosenTechList) {
//        Criteria criteria = new Criteria();
//        criteria.andOperator(Criteria.where("techs").all(chosenTechList));
//        Query query = new Query(criteria);
//        return mongoTemplate.find(query, JobPost.class, jobPostCollectionName);
//    }

//    @Override
//    public List<JobPost> getPostBySearchText(String text) {
//        Criteria criteria = new Criteria();
//        criteria.orOperator(
//                Criteria.where("profile").regex(text, "i"),
//                Criteria.where("desc").regex(text, "i"),
//                Criteria.where("techs").regex(text, "i")
//        );
//        Query query = new Query(criteria);
//        return mongoTemplate.find(query, JobPost.class, jobPostCollectionName);
//    }
}