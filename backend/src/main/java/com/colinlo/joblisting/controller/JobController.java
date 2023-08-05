package com.colinlo.joblisting.controller;

import javax.servlet.http.HttpServletResponse;

import com.colinlo.joblisting.repository.JobPostRepository;
import com.colinlo.joblisting.model.JobPost;
import com.colinlo.joblisting.repository.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "${frontend.base.url}")
public class JobController {

    @Autowired
    JobPostRepository repo;

    @Autowired
    SearchRepository searchRepo;

    @ApiIgnore
    @RequestMapping(value = "/")
    public void redirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html");
    }

    @GetMapping("/allposts")
    public List<JobPost> getAllPosts(){
        return repo.findAll();
    }

    @PostMapping("/addpost")
    public JobPost addpost(@RequestBody JobPost post){
        return repo.save(post);
    }

    @GetMapping("/posts/{text}")
    public List<JobPost> search(@PathVariable String text){
        return searchRepo.findByText(text);
    }

}
