package com.example.restapimvc.scraper.site.brightnetwork;

import com.example.restapimvc.scraper.common.AbstractJobScraper;
import com.example.restapimvc.scraper.common.CsvUtil;
import com.example.restapimvc.scraper.common.FindTarget;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BrightNetworkScraper extends AbstractJobScraper {

    private static final String TEST_INIT_URL = "https://www.brightnetwork.co.uk/search/?content_types=jobs&job_types=Graduate+job&job_types=Internship&location=glasgow&no_radius=True";
    private static final String INIT_URL = "https://www.brightnetwork.co.uk/search/?offset=0&content_types=jobs&job_types=Graduate+job&job_types=Internship&location=london&no_radius=True";

    @Override
    public void startScraping() {
        setUp();
        fillQueue();
        exploreQueue();
    }

    @Override
    public void fillQueue() {
        while (true) {
            try {
                wait(By.xpath("//*[@class='result-link result-link-text js-ga-search-event card-link']"),10);
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
            List<WebElement> aTags = getDriver().findElements(By.xpath("//*[@class='result-link result-link-text js-ga-search-event card-link']"));
            for (WebElement aTag : aTags) {
                getQueue().add(aTag.getAttribute("href"));
            }
            try {
                wait(By.xpath("//*[@class='pagination-link ms-5 ']"),10);
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
            JavascriptExecutor javascriptExecutor = (JavascriptExecutor) getDriver();
            javascriptExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight);");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            getDriver().findElement(By.xpath("//*[@class='pagination-link ms-5 ']")).click();
        }
    }

    @Override
    public void goListPage() {

    }

    @Override
    public void exploreQueue() {
        for (String url: getQueue()) {
            getDriver().get(url);
            collectTargets(url);
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {e.printStackTrace();}
        }
    }

    @Override
    public void collectTargets(String url) {
        FindTarget findTarget = FindTarget.builder()
                .companyName(findCompanyName())
                .jobTitle(findJobTitle())
                .url(url)
                .expireIn(findExpire())
                .jobType(findJobType())
                .location(findLocation())
                .jobDescription(findJobDescription())
                .build();
        getFindTargets().add(findTarget);
        System.out.println(findTarget);
    }

    @Override
    public void goInitPage() {
        getDriver().get(INIT_URL);
    }

    private String findJobDescription() {
        String jobDescription = "";
        try {
            jobDescription = getDriver()
                    .findElement(By.xpath("//section[@class='section__description mt-5']"))
                    .getText();
        } catch (Exception e) {e.printStackTrace();}
        return jobDescription;
    }
    private String findLocation() {
        String location = "";
        try {
            location = getDriver()
                    .findElement(By.xpath("//div[@class='col-md-6 field field-locations']/div[@class='field-value']"))
                    .getText();
        } catch (Exception e) {e.printStackTrace();}
        return location;
    }

    private String findJobType() {
        String jobType = "";
        try {
            jobType = getDriver()
                    .findElement(By.xpath("//div[@class='col-md-6 field field-type']/div[@class='field-value']/a"))
                    .getText();
        } catch (Exception e) {e.printStackTrace();}
        return jobType;
    }
    private String findExpire() {
        String expire = "";
        try {
            expire = getDriver()
                    .findElement(By.xpath("//div[@class='col-md-6 field field-deadline']/div[@class='field-value']"))
                    .getText();
        } catch (Exception e) {e.printStackTrace();}
        return expire;
    }

    private String findJobTitle() {
        String jobTitle = "";
        try {
            jobTitle = getDriver().findElement(By.xpath("//div[@class='add-to-calendar-button-container']/a")).getAttribute("data-name");
        } catch (Exception e) {e.printStackTrace();}
        return jobTitle;
    }

    private String findCompanyName() {
        String companyName = "";
        try {
            wait(By.xpath("//*[@class='col-md-6 field field-related-company']"),8);
            companyName = getDriver().findElement(By.xpath("//*[@class='col-md-6 field field-related-company']")).getAttribute("innerText");
        } catch (Exception e) {e.printStackTrace();}
        return companyName;
    }

    public static void main(String[] args) {
        BrightNetworkScraper brightNetworkScraper = new BrightNetworkScraper();
        try {
            brightNetworkScraper.startScraping();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            CsvUtil.toCsv(brightNetworkScraper.getFindTargets(),"/Users/sunghonpark/RESTApiMVC/src/main/java/com/example/scraper/result/test_bright_network.csv",false);
        }
    }
}
