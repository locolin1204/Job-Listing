package com.colinlo.joblisting.service;

import java.util.function.Predicate;

public interface EmailValidatorService extends Predicate<String> {
    boolean test(String email);
}
