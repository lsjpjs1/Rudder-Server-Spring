package com.example.restapimvc.scraper.common;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FindTargetTest {

    @Test
    void getSearchSummary() {
        FindTarget companyName = FindTarget.builder().jobDescription("description hi-Hi").companyName("companyName").build();
        System.out.println(companyName.getSearchSummary());
    }
}