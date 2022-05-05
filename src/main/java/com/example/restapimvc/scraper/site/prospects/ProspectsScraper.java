package com.example.restapimvc.scraper.site.prospects;

import com.example.restapimvc.scraper.common.AbstractJobScraper;
import com.example.restapimvc.scraper.common.CsvUtil;
import com.example.restapimvc.scraper.common.FindTarget;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ProspectsScraper extends AbstractJobScraper {
    private static final String INIT_URL = "https://www.prospects.ac.uk/graduate-jobs-results?sortBy=dp&region=53&size=20&page=0";

    @Override
    public void startScraping() {
        setUp();
        goEntireListPage();
        fillQueue();
        exploreQueue();
    }

    @Override
    public void fillQueue() {
        wait(By.cssSelector("h3[class='result-item-title']"),20);
        List<WebElement> h3Elements = getDriver().findElements(By.cssSelector("h3[class='result-item-title']"));
        for (WebElement h3Element : h3Elements) {
            String detailPageLink = h3Element.findElement(By.tagName("a")).getAttribute("href");
            getQueue().add(detailPageLink);
        }
        System.out.println(getQueue());

    }

    @Override
    public void goListPage() {

    }

    @Override
    public void exploreQueue() {
        while (!getQueue().isEmpty()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {}
            String detailUrl = getQueue().poll();
            getDriver().get(detailUrl);
            collectTargets(detailUrl);
        }
    }

    @Override
    public void collectTargets(String currentUrl) {
        FindTarget findTarget = FindTarget.builder()
                .companyName(findCompanyName())
                .jobTitle(findJobTitle())
                .url(currentUrl)
                .expireIn(findExpire())
                .jobType(findJobType())
                .salary(findSalary())
                .location(findLocation())
                .jobDescription(findJobDescription())
                .build();
        getFindTargets().add(findTarget);
        System.out.println(findTarget.toCsvString());

    }

    @Override
    public void goInitPage() {
        getDriver().get(INIT_URL);
    }

    private String findJobDescription() {
        String jobDescription = "";
        try {
            jobDescription = getDriver()
                    .findElement(By.xpath("//div[@class='col-md-8']/div[@data-ua-category='Key interaction']"))
                    .getText();
        } catch (Exception e) {e.printStackTrace();}
        return jobDescription;
    }

    private String findJobType() {
        String jopType = "";
        try {
            jopType += getDriver().findElement(By.xpath("//*[text()='Contract, dates and working times']/../dd"))
                    .getText()
                    .replaceAll("\\n"," ");
        } catch (Exception e) {}
        return jopType;
    }

    private String findLocation() {
        String location = "";
        try {
            location += getDriver().findElement(By.cssSelector("meta[name='page:location']")).getAttribute("content");
        } catch (Exception e) {}
        return location;

    }

    private String findSalary() {
        String salary = "";
        try {
            salary += getDriver().findElement(By.cssSelector("meta[name='page:salary']")).getAttribute("content");
        } catch (Exception e) {}
        return salary;

    }

    private String findExpire() {
        String expireIn = "";
        List<WebElement> spans = new ArrayList<>();
        try {
            spans = getDriver().findElement(By.className("section-job-details-meta-tags"))
                    .findElements(By.cssSelector("div[class='section-job-details-meta-expires'] > span"));
        } catch (Exception e) {}
        for (WebElement span: spans) {
            System.out.println(span.getAttribute("innerHTML"));
            expireIn = expireIn + span.getAttribute("innerHTML") +" ";
        }
        return expireIn;
    }

    private String findCompanyName() {
        String companyName = "";
        try {
            wait(By.className("section-job-details-meta-data"),5);
            companyName = getDriver().findElement(By.className("section-job-details-meta-data"))
                    .findElement(By.tagName("li"))
                    .findElements(By.tagName("span"))
                    .get(1)
                    .getText();
        } catch (Exception e) {
            try {
                companyName = getDriver().findElement(By.className("section-job-details-meta-data"))
                        .findElement(By.tagName("li"))
                        .findElement(By.tagName("a"))
                        .getText();
            } catch (Exception e2) {}
        }
        return companyName;

    }
    private String findJobTitle() {
        return getDriver().findElement(By.cssSelector("meta[name='page:title']"))
                .getAttribute("content");
    }


    private void goEntireListPage() {

        String entireListUrl = INIT_URL.replaceAll("size=20", "size=" + findEntireItemCount());
        getDriver().get(entireListUrl);
    }

    private String findEntireItemCount() {
        wait(By.cssSelector("span[count='jobs.totalNumberOfResults']"),20);
        String totalNumberOfResults = getDriver().findElement(By.cssSelector("span[count='jobs.totalNumberOfResults']")).getText();
        Matcher matcher = Pattern.compile("\\d+").matcher(totalNumberOfResults);
        matcher.find();
        return matcher.group();
    }

    public static void main(String[] args) {

        ProspectsScraper prospectsScraper = new ProspectsScraper();
        try {
            prospectsScraper.startScraping();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            CsvUtil.toCsv(prospectsScraper.getFindTargets(),"/Users/sunghonpark/RESTApiMVC/src/main/java/com/example/scraper/result/test_prospects.csv",false);
        }
    }
}
