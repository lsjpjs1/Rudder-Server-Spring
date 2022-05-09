package com.example.restapimvc.scraper.site.prospects;

import com.example.restapimvc.job.insert.application.InsertJobService;
import com.example.restapimvc.scraper.common.CsvUtil;
import com.example.restapimvc.scraper.site.brightnetwork.BrightNetworkScraper;
import com.example.restapimvc.scraper.site.indeed.IndeedScraper;
import com.example.restapimvc.scraper.site.targetjobs.TargetJobsScraper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@SpringBootTest
class ProspectsScraperTest {

    @Autowired
    private InsertJobService insertJobService;
    private static final String today = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());

    @Test
    void runJobs() throws InterruptedException {
        Thread prospectsThread = new Thread(() -> {
            ProspectsScraper prospectsScraper = new ProspectsScraper();
            try {
                prospectsScraper.startScraping();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                insertJobService.insertJobs(prospectsScraper.getFindTargets());
                CsvUtil.toCsv(prospectsScraper.getFindTargets(), "/Users/sunghonpark/RESTApiMVC/src/main/java/com/example/scraper/result/res_prospects_" + today + ".csv", false);
            }
        });

        Thread brightNetworkThread = new Thread(() -> {
            BrightNetworkScraper brightNetworkScraper = new BrightNetworkScraper();
            try {
                brightNetworkScraper.startScraping();
            } catch (Exception e){
                e.printStackTrace();
            } finally {
                insertJobService.insertJobs(brightNetworkScraper.getFindTargets());
                CsvUtil.toCsv(brightNetworkScraper.getFindTargets(),"/Users/sunghonpark/RESTApiMVC/src/main/java/com/example/scraper/result/res_bright_network_"+today+".csv",false);
            }
        });

        Thread indeedThread = new Thread(() -> {
            IndeedScraper indeedScraper = new IndeedScraper(0, "graduate scheme","London, Greater London");
            try {
                indeedScraper.startScraping();
            } catch (Exception e){
                e.printStackTrace();
            } finally {
                insertJobService.insertJobs(indeedScraper.getFindTargets());
                CsvUtil.toCsv(indeedScraper.getFindTargets(),"/Users/sunghonpark/RESTApiMVC/src/main/java/com/example/scraper/result/res_indeed_"+today+".csv",false);
            }
        });

        Thread targetJobsThread = new Thread(() -> {
            TargetJobsScraper targetJobsScraper = new TargetJobsScraper();
            try {
                targetJobsScraper.startScraping();
            } catch (Exception e){
                e.printStackTrace();
            } finally {
                insertJobService.insertJobs(targetJobsScraper.getFindTargets());
                CsvUtil.toCsv(targetJobsScraper.getFindTargets(),"/Users/sunghonpark/RESTApiMVC/src/main/java/com/example/scraper/result/res_targetjobs_"+today+".csv",false);
            }
        });

        prospectsThread.start();
        brightNetworkThread.start();
        indeedThread.start();
        targetJobsThread.start();
        Thread.sleep(10000000l);
    }
    @Test
    void prospects() {
        ProspectsScraper prospectsScraper = new ProspectsScraper();
        try {
            prospectsScraper.startScraping();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            insertJobService.insertJobs(prospectsScraper.getFindTargets());
            CsvUtil.toCsv(prospectsScraper.getFindTargets(),"/Users/sunghonpark/RESTApiMVC/src/main/java/com/example/scraper/result/res_prospects_"+today+".csv",false);
        }
    }
    @Test
    void brightNetwork() {
        BrightNetworkScraper brightNetworkScraper = new BrightNetworkScraper();
        try {
            brightNetworkScraper.startScraping();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            insertJobService.insertJobs(brightNetworkScraper.getFindTargets());
            CsvUtil.toCsv(brightNetworkScraper.getFindTargets(),"/Users/sunghonpark/RESTApiMVC/src/main/java/com/example/scraper/result/res_bright_network_"+today+".csv",false);
        }
    }
    @Test
    void indeed() {
        IndeedScraper indeedScraper = new IndeedScraper(0, "graduate scheme","London, Greater London");
        try {
            indeedScraper.startScraping();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            insertJobService.insertJobs(indeedScraper.getFindTargets());
            CsvUtil.toCsv(indeedScraper.getFindTargets(),"/Users/sunghonpark/RESTApiMVC/src/main/java/com/example/scraper/result/res_indeed_"+today+".csv",false);
        }
    }
    @Test
    void targetJobs() {
        TargetJobsScraper targetJobsScraper = new TargetJobsScraper();
        try {
            targetJobsScraper.startScraping();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            insertJobService.insertJobs(targetJobsScraper.getFindTargets());
            CsvUtil.toCsv(targetJobsScraper.getFindTargets(),"/Users/sunghonpark/RESTApiMVC/src/main/java/com/example/scraper/result/res_targetjobs_"+today+".csv",false);
        }
    }
}