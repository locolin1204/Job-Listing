package com.colinlo.joblisting.controller;

import javax.servlet.http.HttpServletResponse;

import com.colinlo.joblisting.repository.JobPostRepository;
import com.colinlo.joblisting.model.JobPost;
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
    JobPostRepository jobRepo;

//    @Autowired
//    SearchRepository searchRepo;

    @GetMapping("/_health")
    public String health(){
        return "OK";
    }

    @ApiIgnore
    @RequestMapping(value = "/")
    public void redirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html");
    }

    @GetMapping("/allposts")
    public List<JobPost> getAllPosts(){
        return jobRepo.getAllPosts();
    }

    @GetMapping("/posts/{text}")
    public List<JobPost> searchPost(@PathVariable String text){
        return jobRepo.searchPost(text);
    }

    @PostMapping("/addpost")
    @ResponseStatus(HttpStatus.CREATED)
    public JobPost addPost(@RequestBody JobPost post){
        return jobRepo.addPost(post);
    }

    @DeleteMapping("/deletepost/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable String id) { jobRepo.deletePost(id); }

    @PatchMapping("/editPost/{id}") // need some amendment
    public JobPost editPost(@PathVariable String id, @RequestBody JobPost post) throws Exception { return jobRepo.editPost(id, post); }

    @GetMapping("/getAllTechs")
    public List<String> getAllTechs(){ return jobRepo.getAllTechs(); }

    @GetMapping("/filterTechPosts")
    public List<JobPost> filterTechPosts(@RequestParam("tech-list") ArrayList<String> techList){
        return jobRepo.filterTechPosts(techList);
    }
    @GetMapping("/searchAndFilterPost")
    public List<JobPost> searchAndFilterPost(@RequestParam String text, @RequestParam("tech-list") ArrayList<String> techList){
        return jobRepo.searchAndFilterPost(text, techList);
    }
}
