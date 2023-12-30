package com.example.DataOx.Jobs;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class JobScraperService {

    private static final String BASE_URL = "https://jobs.techstars.com/jobs";

    public List<JobListing> scrapeJobsByFunction(String jobFunction) throws IOException {
        List<JobListing> jobs = new ArrayList<>();

        // Fetch the page with the specified job function
        String url = BASE_URL + "?filter=" + jobFunction;
        Document doc = Jsoup.connect(url).get();

        // Extract job listings elements
        Elements jobListings = doc.getElementsByClass("sc-beqWaB gupdsY job-card "); // Adjust selector as needed

        for (Element listing : jobListings) {
            //System.out.println(listing);
            JobListing job = extractJobDetails(listing);

            if (job != null) {
                jobs.add(job);

            }
        }
        return jobs;
    }

    private JobListing extractJobDetails(Element listing) throws IOException {
        String jobPageUrl = listing.select("a").get(1).attr("abs:href");
        if (jobPageUrl.contains("jobs.techstars.com")) {
            Document doc = Jsoup.connect(jobPageUrl).get();
            String positionName = doc.getElementsByClass("sc-beqWaB jqWDOR").text();
            if (positionName.isEmpty()){
                positionName = "NOT_FOUND";
            }
            String urlToOrganisation = doc.select("a").get(53).attr("abs:href");
            if (urlToOrganisation.isEmpty()){
                urlToOrganisation = "NOT_FOUND";
            }
            String logoUrl = doc.select("img").get(3).attr("abs:src");
            if (logoUrl.isEmpty()){
                logoUrl = "NOT_FOUND";
            }
            String organizationTitle = doc.getElementsByClass("sc-beqWaB bpXRKw").get(1).text();
            if (organizationTitle.isEmpty()){
                organizationTitle = "NOT_FOUND";
            }
            String laborFunction = doc.getElementsByClass("sc-beqWaB bpXRKw").get(2).text();
            if (laborFunction.isEmpty()){
                laborFunction = "NOT_FOUND";
            }
            String address = doc.getElementsByClass("sc-beqWaB bpXRKw").get(3).text();
            if (address.isEmpty()){
                address = "NOT_FOUND";
            }

            String date = doc.getElementsByClass("sc-beqWaB gRXpLa").text();
            if (date.isEmpty()){
                date = "NOT_FOUND";
            }
            Long epoch = getUnixTimestamp(date);

            Elements tagElements = listing.getElementsByClass("sc-dmqHEX dncTlc");

            StringBuilder tagNames = new StringBuilder();
            if(tagElements.isEmpty()){
                tagNames = new StringBuilder("NOT_FOUND");
            }
            for (Element e: tagElements){
                tagNames.append(e.text()).append(", ");
            }
            String tagsNames = tagNames.toString().replaceAll(", $", "");

            String description = String.valueOf(doc.getElementsByClass("sc-beqWaB fmCCHr"));

            JobListing jobListing = new JobListing();
            jobListing.setJobPageUrl(jobPageUrl);
            jobListing.setPositionName(positionName);
            jobListing.setUrlToOrganization(urlToOrganisation);
            jobListing.setLogo(logoUrl);
            jobListing.setOrganizationTitle(organizationTitle);
            jobListing.setLaborFunction(laborFunction);
            jobListing.setAddress(address);
            jobListing.setPostedDate(epoch);
            jobListing.setDescription(description);
            jobListing.setTagsNames(tagsNames);
            return jobListing;
        }
        return null;
    }

    private Long getUnixTimestamp(String date){
        String date2 = date.replace("Posted on ", "").replace(",", "");
        String[] d2 = date2.split(" ");
        String[] d3 = d2[1].split("");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MMM-yyyy");
        LocalDate postedDate = LocalDate.parse(d2[2]+"-"+d3[0]+d3[1]+d3[2]+"-"+d2[3], formatter);
        ZoneId zoneId = ZoneId.systemDefault();
        return postedDate.atStartOfDay(zoneId).toEpochSecond();
    }
}