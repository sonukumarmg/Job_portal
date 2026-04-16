package com.example.demo.controller;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.JobPostDto;
import com.example.demo.model.JobPost;
import com.example.demo.repo.JobRepo;
import com.example.demo.service.JobService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")

public class Restcontroller {
	
	@Autowired
	private JobService service;
	
	@Autowired
	private JobRepo repo;
	
	
	//@GetMapping(path="jobPosts", produces= {"application/json"})   //This particular change in my annotation tell the server to send only json data to client.(known as negotiation). 
	
	@GetMapping("/jobs")
	public ResponseEntity<List<JobPostDto>> getAlljobs(){
		return ResponseEntity.ok(service.giveall());
		
		
	}
	/*
	Controller
	   │
	   ▼
	ResponseEntity
	   │
	   ├── Status Code (200)
	   ├── Headers
	   └── Body (List<JobPost>)
	   │
	   ▼
	Client receives JSON
	*/
	
	@GetMapping("jobs/{postid}")
	public ResponseEntity<JobPostDto> getjob(@PathVariable int postid) {
		return ResponseEntity.ok(service.getjob(postid));
		
	}
	//@pathvariable annotation look for the variable with curly brackets in path and put that value in the varibale declared in parameter.
	
	
	//@PostMapping(path="jobPost",consumes= {"application/xml"})
	@PostMapping("jobs")
	public ResponseEntity<JobPostDto> adddata(@RequestBody JobPost job) {
		service.add(job);
		return new ResponseEntity<>(service.getjob(job.getPostId()),HttpStatus.CREATED);
		
	}
	
	
	
	@GetMapping("pushdata")
	public String pushdata() {
		List<JobPost> job=new ArrayList<>(Arrays.asList(
				new JobPost(1, "Java Developer", "Must have good experience in core Java and advanced Java", 2,
	                    List.of("Core Java", "J2EE", "Spring Boot", "Hibernate")),


	            new JobPost(2, "Frontend Developer", "Experience in building responsive web applications using React", 3,
	                    List.of("HTML", "CSS", "JavaScript", "React")),


	            new JobPost(3, "Data Scientist", "Strong background in machine learning and data analysis", 4,
	                    List.of("Python", "Machine Learning", "Data Analysis")),


	            new JobPost(4, "Network Engineer", "Design and implement computer networks for efficient data communication", 5,
	                    List.of("Networking", "Cisco", "Routing", "Switching")),


	            new JobPost(5, "Mobile App Developer", "Experience in mobile app development for iOS and Android", 3,
	                    List.of("iOS Development", "Android Development", "Mobile App"))
				
				
				));
		repo.saveAll(job);
		return "Saved sucessfully";
		
	}
	
	
	
	
	@GetMapping("jobs/keyword/{keyword}")
	public ResponseEntity<List<JobPostDto>> searchbykeyword(@PathVariable String keyword){
		return ResponseEntity.ok(service.searchbykeyword(keyword));
	}
	
	@PutMapping("jobs")
	public ResponseEntity<JobPostDto> updatejob(@RequestBody JobPost job) {
		service.updatejob(job);
		return new ResponseEntity<>(service.getjob(job.getPostId()),HttpStatus.OK);
	}
	
	@DeleteMapping("jobs/{postid}")
	public ResponseEntity<String> deletejob(@PathVariable int postid) {
		service.deleteJob(postid);
		return new ResponseEntity<>("Deleted successfully",HttpStatus.OK);
	}
	
	
	
	

}
