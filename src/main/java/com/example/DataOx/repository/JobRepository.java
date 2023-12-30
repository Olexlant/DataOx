package com.example.DataOx.repository;

import com.example.DataOx.Jobs.JobListing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<JobListing, Long> {

}
