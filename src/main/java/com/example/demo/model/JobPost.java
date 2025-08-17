package com.example.demo.model;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/*
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
*/

@Component
@Entity
public class JobPost {
	@Id
	private int postId;
	private String postProfile;
	private String postDesc;
	private int reqExperience;
	@ElementCollection
	private List<String> postTechStack;
	
	public JobPost() { }
    public JobPost(int postId, String postProfile, String postDesc,
                   int reqExperience, List<String> postTechStack) {
        this.postId          = postId;
        this.postProfile     = postProfile;
        this.postDesc        = postDesc;
        this.reqExperience   = reqExperience;
        this.postTechStack   = postTechStack;
    }

    // ——— GETTERS ———

    public int getPostId() {
        return postId;
    }

    public String getPostProfile() {
        return postProfile;
    }

    public String getPostDesc() {
        return postDesc;
    }

    public int getReqExperience() {
        return reqExperience;
    }

    public List<String> getPostTechStack() {
        return postTechStack;
    }

    // ——— (optional) SETTERS ———

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public void setPostProfile(String postProfile) {
        this.postProfile = postProfile;
    }

    public void setPostDesc(String postDesc) {
        this.postDesc = postDesc;
    }

    public void setReqExperience(int reqExperience) {
        this.reqExperience = reqExperience;
    }

    public void setPostTechStack(List<String> postTechStack) {
        this.postTechStack = postTechStack;
    }
	@Override
	public String toString() {
		return "JobPost [postId=" + postId + ", postProfile=" + postProfile + ", postDesc=" + postDesc
				+ ", reqExperience=" + reqExperience + ", postTechStack=" + postTechStack + "]";
	}
    
    

}
