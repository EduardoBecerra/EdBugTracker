package com.example.EdBugTrackerRest.controller;

import java.util.List;
import java.util.Optional;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.EdBugTrackerRest.entity.EdBugTracker;
import com.example.EdBugTrackerRest.repository.EdBugTrackerRepository;
import com.example.EdBugTrackerRest.service.EdBugTrackerService;

/*
 * Welcome to my simple bank bug tracker application.
 * Please see the end of the file for the sample curl commands.
 * This is a H2 database. 
 * 
 * Sincerely, 
 * Eduardo Becerra (Ed)
 */

@RestController
public class BugTrackerController {
	

	private final EdBugTrackerRepository edBugTrackerRepository;
	private final EdBugTrackerService edBugTrackerService;
	
	public BugTrackerController (EdBugTrackerRepository edBugTrackerRepository, EdBugTrackerService edBugTrackerService ) {
		this.edBugTrackerRepository = edBugTrackerRepository;
		this.edBugTrackerService = edBugTrackerService;
	}
	
	//Retrieve all bugs
	@GetMapping("/bugTracker")
	public Iterable<EdBugTracker> getAllBugs(){
		return this.edBugTrackerRepository.findAll();	
	}
	
	//Retrieve all open bugs
	@RequestMapping(value="bugTracker/status/{status}")
		public List<EdBugTracker> getBugTrackerByStatus(@PathVariable String status) {
		return edBugTrackerService.getBugTrackerByStatus(status);
	}
	

	
	//Make a new account
	@PostMapping("/bugTracker")
	public EdBugTracker createNewBug(@RequestBody EdBugTracker edBugTracker) {
		EdBugTracker newAccount = this.edBugTrackerRepository.save(edBugTracker);
		return newAccount;
	}
	
	
	//Retrieve bug by ID
	@GetMapping("/bugTracker/{bugId}")
	public EdBugTracker getBug(@PathVariable("bugId") Integer bugId) {
		Optional<EdBugTracker> getBugOptional = this.edBugTrackerRepository.findById(bugId);
		if(!getBugOptional.isPresent()) {
			return null;
		}		
		EdBugTracker bugFound = getBugOptional.get();	
		return bugFound;
	}
	
	
	//Update bug by ID
	@PutMapping("/bugTracker/{bugId}")
	public EdBugTracker updateBug(@PathVariable("bugId") Integer bugId, @RequestBody EdBugTracker edBugTracker) {
		Optional<EdBugTracker> updateBugOptional = this.edBugTrackerRepository.findById(bugId);
		if(!updateBugOptional.isPresent()) {
			return null;
		}		
		EdBugTracker bugFound = updateBugOptional.get();	
		
		String previousDescription = bugFound.getBugDescription();
		String addedDescription = edBugTracker.getBugDescription();
		if(addedDescription != null) {
		bugFound.setBugDescription(previousDescription +" UPDATE: "+ addedDescription);
		}
		String bugSolved = edBugTracker.getStatus();
		if(bugSolved != null) {
		bugFound.setStatus(bugSolved);
		}
		String bugResolution = edBugTracker.getResolution();
		if(bugResolution != null) {
		bugFound.setResolution(bugResolution);
		bugFound.setStatus("Closed");
		}
		
		this.edBugTrackerRepository.save(bugFound);
		
		return bugFound;
	}	
	
	//Retrieve bug by ID
	@DeleteMapping("/bugTracker/{bugId}")
	public EdBugTracker deleteBug(@PathVariable("bugId") Integer bugId) {
		Optional<EdBugTracker> getBugOptional = this.edBugTrackerRepository.findById(bugId);
		if(!getBugOptional.isPresent()) {
			return null;
		}		
		EdBugTracker bugFound = getBugOptional.get();	
		this.edBugTrackerRepository.delete(bugFound);
		return bugFound;
	}
	

}

/*

Once this application is running on a server copy and paste the following curl commands to command prompt...

Create bug:
curl -X POST localhost:8080/bugTracker -H "Content-Type: application/json" -d "{\"bugDescription\":\"Application keeps freezing when using search bar.\"}"

Create bug:
curl -X POST localhost:8080/bugTracker -H "Content-Type: application/json" -d "{\"bugDescription\":\"The information is not saving.\"}"

Update bugDescription by Id:
curl -X PUT localhost:8080/bugTracker/1 -H "Content-Type: application/json" -d "{\"bugDescription\":\"It freezes for 10 minutes\"}"

Update bugDescription by Id:
curl -X PUT localhost:8080/bugTracker/1 -H "Content-Type: application/json" -d "{\"bugDescription\":\"It now freezes for 20 minutes\"}"

Update bugResolution by Id (Will close the bug) :
curl -X PUT localhost:8080/bugTracker/1 -H "Content-Type: application/json" -d "{\"resolution\":\"We just restarted the computer and it no longer freezes\"}"

Get all open bugs:
curl localhost:8080/bugTracker/status/Open

Get all closed bugs:
curl localhost:8080/bugTracker/status/Closed

Get a bug by ID:
curl localhost:8080/bugTracker/1

Get all bugs:
curl localhost:8080/bugTracker

Delete a bug by ID:
curl -X DELETE localhost:8080/bugTracker/2

*/