import { Component, OnInit } from '@angular/core';
import {TasksInfo} from "./tasks-info";
import {TasksService} from "../services/tasks.service";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-projects',
  templateUrl: './tasks.component.html',
  styleUrls: ['./tasks.component.css']
})
export class TasksComponent implements OnInit {

  listTasks !: TasksInfo[];

  constructor(private taskService : TasksService) {
  }

  ngOnInit(): void {
    this.taskService.getAllProjects()
      .subscribe(data => {
        this.listTasks = data
      });
  }


}
