package com.example.EdBugTrackerRest.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table
public class EdBugTracker {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer bugId;
	
	@CreationTimestamp
    private LocalDate createDate;

	@Column(name = "BUG_DESCRIPTION")
	private String bugDescription;
		
	@Column(name = "RESOLUTION")
	private String resolution;
	
	@Column(name = "STATUS")
	private String status = "Open";
	

	public Integer getBugId() {
		return bugId;
	}

	public void setBugId(Integer bugId) {
		this.bugId = bugId;
	}

	public LocalDate getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDate createDate) {
		this.createDate = createDate;
	}

	public String getBugDescription() {
		return bugDescription;
	}

	public void setBugDescription(String bugDescription) {
		this.bugDescription = bugDescription;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}
	

}
