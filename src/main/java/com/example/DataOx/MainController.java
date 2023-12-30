package com.example.DataOx;

import com.example.DataOx.Jobs.JobListing;
import com.example.DataOx.Jobs.JobScraperService;
import com.example.DataOx.repository.JobRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;


@AllArgsConstructor
@Controller
public class MainController {
    private final JobRepository jobRepository;


    @GetMapping("/")
    public String getHomePage(){
        return "home";
    }
    

    @PostMapping("/scrap")
    public String scrapJobs(Model model, @RequestParam String jobFunction) throws IOException {
        JobScraperService jobScraper = new JobScraperService();
        List<JobListing> jobListings = jobScraper.scrapeJobsByFunction(jobFunction);
        model.addAttribute("jobs", jobListings);
        jobRepository.saveAll(jobListings);
        return "result";
    }

}
