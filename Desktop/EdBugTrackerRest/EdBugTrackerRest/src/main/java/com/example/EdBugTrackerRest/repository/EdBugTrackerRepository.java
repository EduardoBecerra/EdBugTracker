package com.example.EdBugTrackerRest.repository;



import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.example.EdBugTrackerRest.entity.EdBugTracker;


public interface EdBugTrackerRepository extends CrudRepository <EdBugTracker, Integer> {

	public List<EdBugTracker> findByStatus(String Status);
	
}
