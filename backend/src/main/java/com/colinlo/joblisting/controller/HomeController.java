package com.colinlo.joblisting.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
//@CrossOrigin(origins = "${frontend.base.url}")
public class HomeController {
    @GetMapping("/")
    public void redirectToSwaggerUI(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html");
    }
}
