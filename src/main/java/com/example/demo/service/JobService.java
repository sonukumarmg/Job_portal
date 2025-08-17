package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.JobPost;
import com.example.demo.repo.JobRepo;

//We are using above methods when repo is not connected with database,but when the repo is connected with interface using Jparepository then we have to use specific methods name.


@Service
public class JobService {
	@Autowired
	private JobRepo repo;
	
	
	public void add(JobPost job1) {
		repo.save(job1);
	}
	
	public List<JobPost> giveall(){
		return repo.findAll();
	}

	public JobPost getjob(int postid) {
		return repo.findById(postid).orElse(new JobPost());
		
	}

	public void updatejob(JobPost job) {
		repo.save(job);
		
		
	}

	public void deleteJob(int postid) {
		repo.deleteById(postid);
		
	}
	public List<JobPost> searchbykeyword(String keyword) {
		return repo.search(keyword);
		
		
		
	}
	
	

};
