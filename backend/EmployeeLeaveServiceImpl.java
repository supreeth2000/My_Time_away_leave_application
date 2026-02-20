package com.mytimeaway.serviceimpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mytimeaway.dto.EmployeeLeaveDTO;
import com.mytimeaway.entity.EmployeeLeave;
import com.mytimeaway.exception.ApplicationNotFoundException;
import com.mytimeaway.repo.EmployeeLeaveRepository;
import com.mytimeaway.service.EmployeeLeaveService;

@Service
public class EmployeeLeaveServiceImpl implements EmployeeLeaveService {

	@Autowired
	private EmployeeLeaveRepository leaveRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<EmployeeLeaveDTO> getAllLeaves() {
		List<EmployeeLeave> leaves = leaveRepository.findAll();
		  return leaves.stream().map(this::convertToDTO).collect(Collectors.toList());

		
	}

	@Override
	public EmployeeLeaveDTO getLeaveById(Long id) {
		Optional<EmployeeLeave> leaveoptional=leaveRepository.findById(id);
		if(leaveoptional.isPresent()){
			EmployeeLeave leave=leaveoptional.get();
			return convertToDTO(leave);
		}
		else {
			throw new ApplicationNotFoundException("Leave with id " + id +" not found");
		}

	}

	@Override
	public EmployeeLeaveDTO createLeave(EmployeeLeaveDTO leaveDTO) {
		EmployeeLeave leave=convertToEntity(leaveDTO);
		return convertToDTO(this.leaveRepository.save(leave));
	}
	

	@Override
	public EmployeeLeaveDTO updateLeaveById(Long id, EmployeeLeaveDTO leaveDTO) {
		Optional<EmployeeLeave> leaveoptional=leaveRepository.findById(id);
		if(leaveoptional.isPresent()){
			EmployeeLeave updatedleave=convertToEntity(leaveDTO);
			return convertToDTO(this.leaveRepository.save(updatedleave));
		}
		else {
			throw new ApplicationNotFoundException("Leave with the  id" + id +" not found");
		}

	}

	@Override
	public Boolean deleteLeaveById(Long id) {
		Optional<EmployeeLeave> leaveoptional=leaveRepository.findById(id);
		if(leaveoptional.isPresent()){
			this.leaveRepository.deleteById(id);
			return true;
			
		}
		else {
			throw new ApplicationNotFoundException("Leave with id" + id +" not found");
		}

	}

	@Override
	public List<EmployeeLeaveDTO> searchLeaves(String employeeId, String employeeName, Integer totalDays) {
		
		List<EmployeeLeave> leaves=leaveRepository.findAll();
		return leaves.stream().filter(leave -> employeeId == null || leave.getEmployeeId().equals(employeeId))
				.filter(leave -> employeeName == null || leave.getEmployeeName().equals(employeeName))
				.filter(leave -> totalDays <=0 || leave.getTotalDays()== totalDays ).map(this::convertToDTO).
				collect(Collectors.toList());
	}

	@Override
	public EmployeeLeaveDTO cancelLeaveRequest(Long id) {
		return changeLeaveStatus(id, "CANCELLED");
	}

	@Override
	public EmployeeLeaveDTO approveLeaveRequest(Long id) {
		return changeLeaveStatus(id, "APPROVED");
	}

	@Override
	public EmployeeLeaveDTO rejectLeaveRequest(Long id) {
		return changeLeaveStatus(id, "REJECTED");
	}

	private EmployeeLeaveDTO changeLeaveStatus(Long id, String newStatus) {
		Optional<EmployeeLeave> leaveoptional=leaveRepository.findById(id);
		if(leaveoptional.isPresent()){
			EmployeeLeave leave=leaveoptional.get();
			leave.setStatus(newStatus);
			EmployeeLeave updatedleave = leaveRepository.save(leave);
			return convertToDTO(updatedleave);
			
			
		}
		else {
			throw new ApplicationNotFoundException("Leave with id" + id +" not found");
		}
	}

	private EmployeeLeaveDTO convertToDTO(EmployeeLeave leave) {
		EmployeeLeaveDTO leaveDTO = modelMapper.map(leave, EmployeeLeaveDTO.class);
        return leaveDTO;
	}

	private EmployeeLeave convertToEntity(EmployeeLeaveDTO leaveDTO) {
		 EmployeeLeave leave = modelMapper.map(leaveDTO, EmployeeLeave.class);
        return leave;
	}
}

