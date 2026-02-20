package com.mytimeaway.entity;

import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "employeeleave")
public class EmployeeLeave {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String employeeId;

	@Column(nullable = false)
	private String employeeName;

	@Column(nullable = false)
	private String employeePhone;

	@Column(nullable = false)
	private String employeeEmail;

	@Column(nullable = false)
	private String managerEmail;

	@Column(nullable = false)
	private Date fromDate;

	@Column(nullable = false)
	private Date toDate;

	@Column(nullable = false)
	private int totalDays;

	@Column(nullable = false)
	private String reason;

	@Column(nullable = false)
	private Boolean isProcessed = false;

	@Column(nullable = false)
	private String status = "";

	public EmployeeLeave() {
		super();
	}

	public EmployeeLeave(Long id, String employeeId, String employeeName, String employeePhone, String employeeEmail,
			String managerEmail, Date fromDate, Date toDate, int totalDays, String reason, Boolean isProcessed,
			String status) {
		super();
		this.id = id;
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.employeePhone = employeePhone;
		this.employeeEmail = employeeEmail;
		this.managerEmail = managerEmail;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.totalDays = totalDays;
		this.reason = reason;
		this.isProcessed = isProcessed;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeePhone() {
		return employeePhone;
	}

	public void setEmployeePhone(String employeePhone) {
		this.employeePhone = employeePhone;
	}

	public String getEmployeeEmail() {
		return employeeEmail;
	}

	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}

	public String getManagerEmail() {
		return managerEmail;
	}

	public void setManagerEmail(String managerEmail) {
		this.managerEmail = managerEmail;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public int getTotalDays() {
		return totalDays;
	}

	public void setTotalDays(int totalDays) {
		this.totalDays = totalDays;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Boolean getIsProcessed() {
		return isProcessed;
	}

	public void setIsProcessed(Boolean isProcessed) {
		this.isProcessed = isProcessed;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "EmployeeLeave [id=" + id + ", employeeId=" + employeeId + ", employeeName=" + employeeName
				+ ", employeePhone=" + employeePhone + ", employeeEmail=" + employeeEmail + ", managerEmail="
				+ managerEmail + ", fromDate=" + fromDate + ", toDate=" + toDate + ", totalDays=" + totalDays
				+ ", reason=" + reason + ", isProcessed=" + isProcessed + ", status=" + status + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeeLeave other = (EmployeeLeave) obj;
		return Objects.equals(id, other.id);
	}

}
