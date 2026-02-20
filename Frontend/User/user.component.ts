import { Component } from '@angular/core';
import { LeaveService } from '../../service/leave.service';
import { Leave } from '../../models/leave.model';
import { FormGroup, FormBuilder, Validators, FormsModule } from '@angular/forms';
import { Router, RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';
import { CommonModule, JsonPipe } from '@angular/common';
import { EmpPostResponse } from '../../interfaces/EmpPostResponse';
import { MatDatepicker } from '@angular/material/datepicker';
import { MatFormField, MatLabel } from '@angular/material/form-field';

@Component({
  selector: 'app-user',
  standalone: true,
  imports: [CommonModule,FormsModule,JsonPipe,RouterOutlet,RouterLink,RouterLinkActive],
  templateUrl: './user.component.html',
  styleUrl: './user.component.css'
})
export class UserComponent {
  constructor(private es: LeaveService,private router:Router){

  }
  
    id:number=0;
    employeeId:string="";
    employeeName:string="";
    employeePhone:string="";
    employeeEmail:string="";
    managerEmail:string="";
    fromDate:Date=new Date();
    toDate:Date=new Date();
    totalDays:number=0;
    reason:string="";
    
  
    f1(){
      console.log(this.id)
      console.log(this.employeeId)
      console.log(this.employeeName)
      console.log(this.employeePhone)
      console.log(this.employeeEmail)
      console.log(this.managerEmail)
      console.log(this.fromDate)
      console.log(this.toDate)
      console.log(this.totalDays)
      console.log(this.reason)
  
      let emp={
        
        "employeeId":this.employeeId,
        "employeeName":this.employeeName,
        "employeePhone":this.employeePhone,
        "employeeEmail":this.employeeEmail,
        "managerEmail":this.managerEmail,
        "fromDate":this.fromDate,
        "toDate":this.toDate,
        "totalDays":this.totalDays,
        "reason":this.reason
       
      }

     
      this.es.add(emp).subscribe(
        (data:EmpPostResponse)=>{
          console.log(data);
        }
      );
      this.router.navigate(['/list'])
    }
  
  
  }
  

