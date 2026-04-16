package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.demo.dto.JobPostDto;
import com.example.demo.model.JobPost;
import com.example.demo.repo.JobRepo;

//We are using above methods when repo is not connected with database,but when the repo is connected with interface using Jparepository then we have to use specific methods name.


@Service
public class JobService {
	@Autowired
	private JobRepo repo;
	
	@CacheEvict(value="jobs",key=" 'all_jobs' ")
	public void add(JobPost job) {
		repo.save(job);
	}
	
	@Cacheable(value="jobs",key=" 'all_jobs' ")
	public List<JobPostDto> giveall(){
		List<JobPost> jobs=repo.findAll();
		return jobs.stream()
				.map(this::convertToDto)
				.toList();
	}

	public JobPostDto getjob(int postid) {
		JobPost job= repo.findById(postid)
				.orElseThrow(()->new RuntimeException("Job not found with this id: "+postid));
		//lambda function used here while throwing the error ..see it if now rememberred.
		
		return convertToDto(job);
		
		
	}
	
	
	@CacheEvict(value="jobs",key=" 'all_jobs' ")
	public void updatejob(JobPost job) {
		if(!repo.existsById(job.getPostId())) {
			throw new RuntimeException("Cannot update . Job id "+job.getPostId()+" does not exist");
		}
		repo.save(job);
		
		
	}
	
	@CacheEvict(value="jobs",key=" 'all_jobs' ")
	public void deleteJob(int postid) {
		repo.deleteById(postid);
		
	}
	public List<JobPostDto> searchbykeyword(String keyword) {
		List<JobPost> jobs=repo.search(keyword);
		return jobs.stream()
				.map(this::convertToDto)
				.toList();
		
		
		
	}
	
	public JobPostDto convertToDto(JobPost job) {
    	return new JobPostDto(
    			job.getPostId(), 
    			job.getPostProfile(), 
    			job.getPostDesc(),
    			job.getReqExperience(), 
    			job.getPostTechStack());
    }
    
	
	

};
