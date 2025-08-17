package com.example.demo.repo;
import com.example.demo.model.JobPost;

import java.util.List;

//Above methods are used when we have repo not connected with database.
//So to connect repo with database we are using spring jpa.
//To do so we are creating interface repo which extends JpaRepository.
//JpaRepository contains various methods that are implemented by spring in runtime which will allow use to interact with database.
//we just have to use the method implmentation in done by spring in runtime.

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepo extends JpaRepository<JobPost, Integer>{
	
	@Query("SELECT j FROM JobPost j JOIN j.postTechStack tech " +
		       "WHERE LOWER(j.postProfile) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
		       "LOWER(j.postDesc) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
		       "LOWER(tech) LIKE LOWER(CONCAT('%', :keyword, '%'))")
	List<JobPost> search(@Param("keyword") String keyword);

}


