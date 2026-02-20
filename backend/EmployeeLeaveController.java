
package com.mytimeaway.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mytimeaway.dto.EmployeeLeaveDTO;
import com.mytimeaway.service.EmployeeLeaveService;
@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/leaves")
public class EmployeeLeaveController {

	@Autowired
	private EmployeeLeaveService leaveService;
	
	@GetMapping
	public ResponseEntity<List<EmployeeLeaveDTO>> getAllLeaves(){
		List<EmployeeLeaveDTO> leaves= leaveService.getAllLeaves();
		return new ResponseEntity<>(leaves,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EmployeeLeaveDTO> getLeavebyId(@PathVariable(name="id") Long id){
		EmployeeLeaveDTO leave=leaveService.getLeaveById(id);
		if(leave!=null) {
			return new ResponseEntity<>(leave, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping
	public ResponseEntity<EmployeeLeaveDTO> createLeave(@RequestBody EmployeeLeaveDTO leave){
		 EmployeeLeaveDTO createdleave= leaveService.createLeave(leave);
		 if(createdleave!=null) {
		return new ResponseEntity<>(createdleave,HttpStatus.OK);
	}
		 else {
			 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			 
		 }
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<EmployeeLeaveDTO> updateLeavebyId(@PathVariable(name="id") Long id, @RequestBody EmployeeLeaveDTO leave){
		 EmployeeLeaveDTO updatedleave =leaveService.updateLeaveById(id, leave);
		 if(updatedleave!=null) {
			 return new ResponseEntity<>(updatedleave,HttpStatus.OK);
		 }
		 else {
			 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		 }
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteLeaveById(@PathVariable(name="id") Long id){
		
		
		Boolean deleteStatus= leaveService.deleteLeaveById(id);
		if(deleteStatus) {
			return new ResponseEntity<>(HttpStatus.OK);
			
		}
		else {
			 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
		}
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<EmployeeLeaveDTO>> search(@RequestParam(name="id") String employeeId,@RequestParam(name="name") String EmployeeName ,
			@RequestParam(name="totaldays") Integer totalDays){
		List<EmployeeLeaveDTO> leaves=leaveService.searchLeaves(employeeId, EmployeeName, totalDays);
		return new ResponseEntity<>(leaves,HttpStatus.OK);
	}
	
	
	@PutMapping("/{id}/cancel")
	public ResponseEntity<?> cancelLeaveById(@PathVariable(name="id") Long id){
		EmployeeLeaveDTO leave=leaveService.cancelLeaveRequest(id);
		if(leave!=null) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@PutMapping("/{id}/approve")
	public ResponseEntity<?> approveLeaveById(@PathVariable(name="id") Long id){
		EmployeeLeaveDTO leave=leaveService.approveLeaveRequest(id);
		if(leave!=null) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@PutMapping("/{id}/reject")
	public ResponseEntity<?> rejectLeaveById(@PathVariable(name="id") Long id){
		EmployeeLeaveDTO leave=leaveService.rejectLeaveRequest(id);
		if(leave!=null) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	
	
	
	

}
