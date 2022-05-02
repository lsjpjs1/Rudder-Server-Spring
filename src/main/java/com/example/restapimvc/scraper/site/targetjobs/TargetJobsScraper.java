package com.example.restapimvc.scraper.site.targetjobs;

import com.example.restapimvc.scraper.common.AbstractJobScraper;
import com.example.restapimvc.scraper.common.CsvUtil;
import com.example.restapimvc.scraper.common.FindTarget;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class TargetJobsScraper extends AbstractJobScraper {

    private static final String TEST_INIT_URL = "https://targetjobs.co.uk/search/jobs?search=&opportunity_type=Immediate+start&sort=publish_desc";
    private static final String INIT_URL = "https://targetjobs.co.uk/search/jobs?search=&sort=publish_desc";

    @Override
    public void startScraping() {
        setUp();
        cookieAgree();
        loadAll();
        fillQueue();
        exploreQueue();
    }

    @Override
    public void fillQueue() {
        List<WebElement> aTags = getDriver().findElements(By.xpath("//*[@class='relative h-full']/a"));
        for (WebElement aTag : aTags) {
            getQueue().add(aTag.getAttribute("href"));
        }
        System.out.println(getQueue());
    }

    @Override
    public void goListPage() {

    }

    @Override
    public void exploreQueue() {
        for (String url : getQueue()) {
            getDriver().get(url);
            collectTargets(url);
        }

    }

    @Override
    public void collectTargets(String url) {
        FindTarget findTarget = FindTarget.builder()
                .companyName(findCompanyName())
                .jobTitle(findJobTitle())
                .url(url)
                .expireIn(findExpire())
                .salary(findSalary())
                .location(findLocation())
                .build();
        getFindTargets().add(findTarget);
        System.out.println(findTarget);
    }

    @Override
    public void goInitPage() {
        getDriver().get(INIT_URL);
    }
    private String findLocation() {
        String location = "";
        try {
            location = getDriver()
                    .findElement(By.xpath("//p[text()='Locations']/.."))
                    .getText()
                    .replaceAll("LOCATIONS\n","");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return location;
    }
    private String findSalary() {
        String salary = "";
        try {
            salary = getDriver()
                    .findElement(By.xpath("//p[text()='Salary']/.."))
                    .getText()
                    .replaceAll("SALARY\n","");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return salary;
    }
    private String findExpire() {
        String expire = "";
        try {
            expire = getDriver()
                    .findElement(By.xpath("//*[text()='Apply by']/.."))
                    .getText()
                    .replaceAll("APPLY BY\n","");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return expire;
    }
    private String findJobTitle() {
        String jobTitle = "";
        try {
            jobTitle = getDriver().findElement(By.xpath("//*[@property='og:title']")).getAttribute("content");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jobTitle;
    }

    private String findCompanyName() {
        String companyName = "";
        try {
            wait(By.xpath("//*[@class='text-gray text-base line-clamp-1 mb-1 ']"),8);
            companyName = getDriver().findElement(By.xpath("//*[@class='text-gray text-base line-clamp-1 mb-1 ']")).getAttribute("innerText");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return companyName;
    }


    private void cookieAgree() {
        try {
            wait(By.xpath("//*[text()='Yes, I agree']"),8);
            getDriver().findElement(By.xpath("//*[text()='Yes, I agree']")).click();
        } catch (Exception e) {
            e.getMessage();
        }
    }

    private void loadAll() {
        while(true) {
            try {
                getDriver().findElement(By.cssSelector("chilli-button[text='Load more']"));
            } catch (Exception e) {
                break;
            }
            getDriver().findElement(By.cssSelector("chilli-button[text='Load more']")).click();
            while (true) {
                try {
                    if (getDriver().findElement(By.cssSelector("chilli-button[text='Load more']")).getAttribute("style").equals("")){
                        break;
                    }
                } catch (Exception e) {
                    break;
                }

            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        TargetJobsScraper targetJobsScraper = new TargetJobsScraper();

        try {
            targetJobsScraper.startScraping();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            CsvUtil.toCsv(targetJobsScraper.getFindTargets(),"/Users/sunghonpark/RESTApiMVC/src/main/java/com/example/scraper/result/res_targetjobs_220422.csv",false);
        }
    }

}
