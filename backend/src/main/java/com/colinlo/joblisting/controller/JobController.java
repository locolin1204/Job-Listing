package com.colinlo.joblisting.controller;

import javax.servlet.http.HttpServletResponse;

import com.colinlo.joblisting.model.JobPost;
import com.colinlo.joblisting.service.JobPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "${frontend.base.url}")
public class JobController {
    @Autowired
    JobPostService jobPostService;

    @GetMapping("/_health")
    public String health(){
        return "OK";
    }

    @GetMapping("/_mongodbconnectionstatus")
    public String mongoDbConnectionStatus(){
        return jobPostService.getMongoDbConnectionStatus();
    }

    @ApiIgnore
    @RequestMapping(value = "/")
    public void redirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html");
    }

    @GetMapping("/posts")
    public List<JobPost> getAllPosts(){
        return jobPostService.getAllPosts();
    }

    @GetMapping("/techs")
    public List<String> getAllTechs(){ return jobPostService.getAllTechs(); }

    @GetMapping("/search-filter-posts")
    public List<JobPost> searchAndFilterPost(@RequestParam("keyword") String text, @RequestParam("tech-list") ArrayList<String> techList){
        return jobPostService.getPostBySearchTextAndTech(text, techList);
    }

    @PostMapping("/addpost")
    @ResponseStatus(HttpStatus.CREATED)
    public JobPost addPost(@RequestBody JobPost post){
        return jobPostService.addPost(post);
    }

    @DeleteMapping("/deletepost/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable String id) { jobPostService.deletePost(id); }

    @PatchMapping("/editpost")
    public JobPost editPost(@RequestBody JobPost post) throws Exception { return jobPostService.editPost(post); }

}
