package com.example.EdBugTrackerRest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.EdBugTrackerRest.entity.EdBugTracker;
import com.example.EdBugTrackerRest.repository.EdBugTrackerRepository;

@Service
public class EdBugTrackerService {
	
	@Autowired
	private EdBugTrackerRepository edBugTrackerRepository;

	public List<EdBugTracker> getBugTrackerByStatus(String status) {
	
		return edBugTrackerRepository.findByStatus(status);
		
		
	}

}
