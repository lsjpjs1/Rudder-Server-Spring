package com.example.restapimvc.scraper.site.indeed;

import com.example.restapimvc.scraper.common.AbstractJobScraper;
import com.example.restapimvc.scraper.common.CsvUtil;
import com.example.restapimvc.scraper.common.FindTarget;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class IndeedScraper extends AbstractJobScraper {

    private static final String SITE_URL = "https://uk.indeed.com/jobs?";
    private static final String VIEW_DETAIL_LINK = "https://uk.indeed.com/viewjob?jk=";

    
    private Integer page;
    private String searchWord;
    private String location;
    private Integer prevSize = 0;



    public IndeedScraper(Integer page, String searchWord, String location) {
        this.page = page;
        this.searchWord = searchWord.replaceAll(" ", "%20");
        this.location = location.replaceAll(" ", "%20").replaceAll(",","%2C");
    }


    @Override
    public void startScraping() {
        setUp();
        while (prevSize == 0 || getFindTargets().size() != prevSize) {
            prevSize = getFindTargets().size();
            fillQueue();
            exploreQueue();
            pageUp();
            goListPage();
        }
    }



    @Override
    public void fillQueue() {
        List<WebElement> jobCards = getDriver().findElement(By.id("mosaic-provider-jobcards")).findElements(By.tagName("a"));
        for (WebElement jobCard : jobCards) {
            String jobCardId = jobCard.getAttribute("id");
            if (jobCardId.isEmpty()) {
                continue;
            }
            if (jobCardId.length() == 20) {
                getQueue().add(jobCardId.substring(4));
            }
        }
    }

    @Override
    public void exploreQueue() {
        while (!getQueue().isEmpty()) {
            try {
                Thread.sleep(2500); //1초 대기
            } catch (InterruptedException e) {
            }
            String jobCardId = getQueue().poll();
            getDriver().get(VIEW_DETAIL_LINK + jobCardId);
            collectTargets(VIEW_DETAIL_LINK + jobCardId);
        }
    }

    @Override
    public void collectTargets(String currentUrl) {
        FindTarget findTarget = FindTarget.builder()
                .companyName(findCompanyName())
                .jobTitle(findJobTitle())
                .url(currentUrl)
                .jobType(findJobType())
                .location(findLocation())
                .salary(findSalary())
                .jobDescription(findJobDescription())
                .build();
        getFindTargets().add(findTarget);
        System.out.println(findTarget.toString());
    }

    @Override
    public void goInitPage() {
        getDriver().get(getListPageLink());
    }

    private String findJobDescription() {
        String jobDescription = "";
        try {
            jobDescription = getDriver()
                    .findElement(By.xpath("//div[@id='jobDescriptionText']"))
                    .getText();
        } catch (Exception e) {e.printStackTrace();}
        return jobDescription;
    }

    private String findJobTitle() {
        String jobTitle = "";
        try {
            jobTitle = getDriver().findElement(By.cssSelector("meta[id='indeed-share-message']")).getAttribute("content");
        } catch (Exception e) {e.printStackTrace();}
        return jobTitle;
    }

    private String findCompanyName() {
        String companyName = "";
        try {
            wait(By.cssSelector("meta[property='og:description']"),8);
            companyName = getDriver().findElement(By.cssSelector("meta[property='og:description']")).getAttribute("content");
        } catch (Exception e) {e.printStackTrace();}
        return companyName;
    }

    private String findLocation() {
        String location = "";
        Pattern pattern = Pattern.compile("in ([a-zA-Z ]+)");

        try {
            String s = getDriver()
                    .findElement(By.xpath("//a[@class='jobsearch-RelatedLinks-link']"))
                    .getText();
            System.out.println(s);
            Matcher matcher = pattern.matcher(s);
            matcher.find();
            location = matcher.group(1);
        } catch (Exception e) {e.printStackTrace();}
        return location;
    }

    private String findSalary() {
        String salary = "";
        try {
            salary = getDriver().findElement(By.xpath("//div[@id='salaryInfoAndJobType']/span[@class='icl-u-xs-mr--xs attribute_snippet']")).getAttribute("innerText");
        }catch (Exception e){
        }
        return salary;
    }

    private String findJobType() {
        String jobType = "";
        try {
            jobType = getDriver().findElement(By.cssSelector("span[class='jobsearch-JobMetadataHeader-item  icl-u-xs-mt--xs']")).getAttribute("innerText");
        }catch (Exception e){
        }
        return jobType;
    }

    @Override
    public void goListPage() {
        getDriver().get(getListPageLink());
    }

    public String getListPageLink() {
        return SITE_URL + "q=" + searchWord + "&start=" + page * 10+"&l="+location;
    }

    public void pageUp() {
        this.page++;
    }


    public static void main(String[] args) {
        IndeedScraper indeedScraper = new IndeedScraper(0, "graduate scheme","London, Greater London");
        try {
            indeedScraper.startScraping();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            CsvUtil.toCsv(indeedScraper.getFindTargets(),"/Users/sunghonpark/RESTApiMVC/src/main/java/com/example/scraper/result/indeed_test.csv",false);
        }



    }
}
