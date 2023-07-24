package com.colinlo.joblisting.repository;

import com.colinlo.joblisting.model.JobPost;

import java.util.List;

public interface SearchRepository {

    List<JobPost> findByText(String text);

}

