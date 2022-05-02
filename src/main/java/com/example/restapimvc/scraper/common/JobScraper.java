package com.example.restapimvc.scraper.common;

public interface JobScraper
{
    void startScraping();

    void setUp();

    void fillQueue();

    void goListPage();

    void exploreQueue();

    void collectTargets(String url);

    void goInitPage();
}
