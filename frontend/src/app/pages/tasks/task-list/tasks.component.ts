import { Component, OnInit, Input } from '@angular/core';
import {TasksInfo} from "../../../models/tasks-info";
import {TasksService} from "../../../services/tasks.service";
import { Router } from '@angular/router';
import {Observable, Subscription} from "rxjs";
import {ModalDismissReasons, NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {FormBuilder, FormGroup} from "@angular/forms";
import {TokenStorageService} from "../../../services/token-storage.service";
import {DataTransferService} from "../../../services/data-transfer.service";
import {Projects} from "../../../models/projects-info";
import {L10n} from "@syncfusion/ej2-base";
import {kanbanData} from "./datasource";
import {
  CardSettingsModel,
  ColumnsModel, DataSourceChangedEventArgs,
  DataStateChangeEventArgs,
  DialogEventArgs,
  SwimlaneSettingsModel
} from "@syncfusion/ej2-angular-kanban";

@Component({
  selector: 'app-tasks',
  templateUrl: './tasks.component.html',
  styleUrls: ['./tasks.component.css']
})
export class TasksComponent implements OnInit {

  public cards: Observable<DataStateChangeEventArgs>;
  public state: DataStateChangeEventArgs;
  public cardSettings: CardSettingsModel;

  public columns: ColumnsModel[] = [
    { headerText: 'To Do', keyField: 'NEW' },
    { headerText: 'In Progress', keyField: 'ASSIGNED' },
    { headerText: 'Testing', keyField: 'COMPLETED' },
    { headerText: 'Close', keyField: 'CLOSE' }
  ];

  listTasks !: TasksInfo[];
  listUserTasks !: TasksInfo[];
  currentTask: any = {};
  closeResult: string;

  today: number = Date.now();
  startForm: FormGroup;

  @Input() currentProject!: Projects;
  @Input() authority!: string;

  constructor(private taskService : TasksService, private router : Router, private data: DataTransferService,
              private modalService: NgbModal, private fb: FormBuilder, private token: TokenStorageService) {
    this.cards = taskService;
  }

  ngOnInit(): void {
    this.data.currentProject.subscribe(project => {
      this.currentProject = project;

      if(this.currentProject != null) {
        if (this.authority === 'manager') {
          console.log(this.currentProject?.id);
          this.taskService.getProjectTasks(this.currentProject?.id)
            .subscribe(data => {
              this.listTasks = data;
            });
        }
        if (this.authority === 'user') {
          this.taskService.getProjectUserTasks(this.currentProject?.id, this.token.getId())
            .subscribe(data => {
              this.listUserTasks = data
            });
        }
      }

      let state = { skip: 0, take: 10 };


      if (project != null) {
        if(this.authority == 'manager')
          this.taskService.execute(state, project?.id, "0");
        else
          this.taskService.execute(state, project?.id, this.token.getId());
      }

      this.cardSettings = {
        headerField: 'id',
        contentField: 'title',
        selectionType: 'Multiple'
      };

    });

    this.startForm = this.fb.group({
      title: [''],
      description: [''],
      spentTime: ['']
    } );


  }

  public dataSourceChanged(state: DataSourceChangedEventArgs): void {
    if (state.requestType === 'cardCreated') {

      // @ts-ignore
      state.endEdit(); }
    else if (state.requestType === 'cardChanged') {

      this.taskService.updateCard(state);
      // @ts-ignore
      let task = state.changedRecords[0];
      console.log(task)
      // @ts-ignore
      state.endEdit() }
    else if (state.requestType === 'cardRemoved') {
      this.taskService.deleteCard(state).subscribe(() => {
        // @ts-ignore
        state.endEdit();
      });
    }
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

  updateTask(id: number) {
    this.router.navigate(['tasks/task-details', id]);
  }

  deleteTask(id: number) {

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

  public dataStateChange(state: DataStateChangeEventArgs): void {
    if(this.authority == 'manager')
      this.taskService.execute(state, this.currentProject?.id, "0");
    else
      this.taskService.execute(state, this.currentProject?.id, this.token.getId());
  }

  public swimlaneSettings: SwimlaneSettingsModel = { keyField: 'userName' };

  dialogOpen(args: DialogEventArgs): void {

  }
}
