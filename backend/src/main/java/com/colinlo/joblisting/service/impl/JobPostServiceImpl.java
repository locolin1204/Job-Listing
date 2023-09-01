package com.colinlo.joblisting.service.impl;

import com.colinlo.joblisting.model.JobPost;
import com.colinlo.joblisting.repository.JobPostRepository;
import com.colinlo.joblisting.service.JobPostService;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobPostServiceImpl implements JobPostService {

    @Autowired
    JobPostRepository jobPostRepo;
    @Autowired
    private MongoClient mongoClient;
    @Value("${spring.data.mongodb.database}")
    private String dbName;

    @Override
    public String getMongoDbConnectionStatus(){
        try {
            MongoDatabase database = mongoClient.getDatabase(dbName);
            Document command = new Document("ping", 1);
            database.runCommand(command);
            return "Successfully connected to MongoDB.";
        } catch (Exception e) {
            return "Failed to connect to MongoDB: " + e.getMessage();
        }
    }

    @Override
    public List<JobPost> getAllPosts() {
       return jobPostRepo.getAllPosts();
    }

    @Override
    public List<String> getAllTechs() {
        return jobPostRepo.getAllTechs();
    }

    @Override
    public List<JobPost> getPostBySearchTextAndTech(String text, ArrayList<String> chosenTechList) {
        Criteria textCriteria = text.isEmpty() ? new Criteria() : jobPostRepo.getCriteriaByText(text);
        Criteria techListCriteria = chosenTechList.isEmpty() ? new Criteria() : jobPostRepo.getCriteriaByTechList(chosenTechList);
        return jobPostRepo.getPostByCriteria(textCriteria, techListCriteria);
    }

    @Override
    public JobPost addPost(JobPost post) {
        return jobPostRepo.addPost(post);
    }

    @Override
    public void deletePost(String id) {
        jobPostRepo.deletePost(id);
    }

    @Override
    public JobPost editPost(JobPost post) throws Exception {
        JobPost jobPost = jobPostRepo.getPostById(post.getId());
        if (jobPost == null) {
            throw new Exception("Job Post not found, id not found.");
        }
        return jobPostRepo.editPost(post);
    }
}
