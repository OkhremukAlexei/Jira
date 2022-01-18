import { Component, OnInit } from '@angular/core';
import {TasksInfo} from "../../../models/tasks-info";
import {TasksService} from "../../../services/tasks.service";
import {Params, Router} from '@angular/router';
import {Observable, Subscription} from "rxjs";
import {ModalDismissReasons, NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {FormBuilder, FormGroup} from "@angular/forms";
import {TokenStorageService} from "../../../services/token-storage.service";
import {DataTransferService} from "../../../services/data-transfer.service";
import {Projects} from "../../../models/projects-info";
import {switchMap} from "rxjs/operators";

@Component({
  selector: 'app-tasks',
  templateUrl: './tasks.component.html',
  styleUrls: ['./tasks.component.css']
})
export class TasksComponent implements OnInit {

  listTasks !: TasksInfo[];
  listUserTasks !: TasksInfo[];
  currentTask: any = {};
  closeResult: string;
  currentProject: Projects;
  authority!: string;
  roles!: string[];

  today: number = Date.now();
  startForm: FormGroup;

  constructor(private taskService : TasksService, private router : Router, private data: DataTransferService,
              private modalService: NgbModal, private fb: FormBuilder, private token: TokenStorageService) {

    this.data.currentProject.subscribe(project => {this.currentProject = project; console.log(project);});
  }

  ngOnInit(): void {
    this.data.currentProject.subscribe(project => {this.currentProject = project; console.log(project);});

    console.log(this.currentProject);

    this.getAuthority();

    if(this.authority === 'manager'){
      console.log(this.currentProject?.id);
      this.taskService.getProjectTasks(this.currentProject?.id)
        .subscribe(data => {
          this.listTasks = data;
        });
    }
    if(this.authority === 'user'){
      this.taskService.getProjectUserTasks(this.currentProject?.id, this.token.getId())
        .subscribe(data => {
          this.listUserTasks = data
        });
    }


    this.startForm = this.fb.group({
      title: [''],
      description: [''],
      spentTime: ['']
    } );


  }

  onSubmit() {
    this.currentTask.project = this.currentProject;
    this.taskService.addTask(this.currentTask)
      .subscribe((result) => {
        this.ngOnInit(); //reload the table
      });
    this.modalService.dismissAll(); //dismiss the modal
  }

  open(content: any) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${TasksComponent.getDismissReason(reason)}`;
    });
  }

  private static getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
  }

  openTaskDetails(id: number){
    this.router.navigate(['tasks/task-details', id]);
  }

  deleteTask(id: number) {

  }

  getAuthority(): void {
    this.roles = this.token.getAuthorities();
    this.roles.every(role => {
      if (role === 'ROLE_ADMIN') {
        this.authority = 'admin';
        return false;
      } else if (role === 'ROLE_MANAGER') {
        this.authority = 'manager';
        return false;
      }
      this.authority = 'user';
      return true;
    });
  }

  openStart(targetModal: any, currentTask: TasksInfo) {
    this.modalService.open(targetModal, {
      centered: true,
      backdrop: 'static',
      size: 'lg'
    });
    this.startForm.patchValue({
      title: currentTask.title,
      description: currentTask.description,
      spentTime: currentTask.spentTime
    })
    this.startForm.controls['title'].disable();
    this.startForm.controls['description'].disable();
    this.data.setTask(currentTask);
    this.data.currentTask.subscribe(task => this.currentTask = task);
  }

  startTask(): void {
    this.taskService.startTask(this.currentTask.id, this.startForm.value)
      .subscribe((results) => {
        this.ngOnInit();
        this.modalService.dismissAll()
      });
  }

  completeTask(currentTask: TasksInfo): void{
    this.taskService.completeTask(currentTask.id, currentTask).subscribe((results)=>{this.ngOnInit()});
  }

  closeTask(currentTask: TasksInfo): void{
    this.taskService.closeTask(currentTask.id, currentTask).subscribe((results)=>{this.ngOnInit()});
  }
}
