package com.colinlo.joblisting.repository;

import com.colinlo.joblisting.model.JobPost;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface JobPostRepository extends MongoRepository<JobPost, String> {
    @Query("{ $or: [ { 'profile': { $regex: ?0, $options: 'i' } }, { 'desc': { $regex: ?0, $options: 'i' } }, { 'techs': { $regex: ?0, $options: 'i' } } ] }")
    List<JobPost> findByText(String text);
}
