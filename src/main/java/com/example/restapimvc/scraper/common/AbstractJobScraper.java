package com.example.restapimvc.scraper.common;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

@Getter
public abstract class AbstractJobScraper implements JobScraper{
    private WebDriver driver;
    private Queue<String> queue = new LinkedList<>();
    private Set<FindTarget> findTargets = new LinkedHashSet<>();




    @Override
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-setuid-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        this.driver = new ChromeDriver(options);

        goInitPage();
    }
    public void wait(By by,long maxWaitingTime) {
        new WebDriverWait(getDriver(),maxWaitingTime)
                .until(ExpectedConditions.presenceOfElementLocated(by));
    }
    public void nestedWait(By by,By child,long maxWaitingTime) {
        new WebDriverWait(getDriver(),maxWaitingTime)
                .until(ExpectedConditions.presenceOfNestedElementLocatedBy(by,child));
    }
}
