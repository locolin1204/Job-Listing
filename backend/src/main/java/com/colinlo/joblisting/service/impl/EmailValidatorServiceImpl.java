package com.colinlo.joblisting.service.impl;

import com.colinlo.joblisting.service.EmailValidatorService;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class EmailValidatorServiceImpl implements EmailValidatorService {
    public boolean test(String email) {
        Pattern pattern = Pattern.compile("^([a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,})$");
        Matcher matcher = pattern.matcher(email);
        return matcher.find();
    }
}
