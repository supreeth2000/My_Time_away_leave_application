import { Component, OnInit } from '@angular/core';
import { LeaveService } from '../../service/leave.service';
import { Leave } from '../../models/leave.model';
import { EmpGetAllResponse } from '../../interfaces/EmpGetAllResponse';
import { CommonModule, JsonPipe } from '@angular/common';

@Component({
  selector: 'app-admin',
  standalone: true,
  imports: [JsonPipe, CommonModule],
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
  leaveApplications: any = [];

  constructor(private leaveService: LeaveService) { }

  ngOnInit() {
    this.fetchLeaveApplications();
  }

  fetchLeaveApplications() {
    this.leaveService.getAll().subscribe((data: EmpGetAllResponse) => {
      this.leaveApplications = data;
    }, (error: any) => {
      console.error('Error fetching leave applications: ', error);
    });
  }

  cancelLeave(application: Leave) {
    this.leaveService.cancelLeaveRequest(application.id).subscribe(() => {
      console.log(`Leave request ${application.id} canceled successfully.`);
      
      application.status = 'CANCELLED';
    }, (error: any) => {
      console.error('Error canceling leave request: ', error);
    });
  }

  approveLeave(application: Leave) {
    this.leaveService.approveLeaveRequest(application.id).subscribe(() => {
      console.log(`Leave request ${application.id} approved successfully.`);
      
      application.status = 'APPROVED';
    }, (error: any) => {
      console.error('Error approving leave request: ', error);
    });
  }
}

