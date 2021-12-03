import { Component, OnInit } from '@angular/core';
import {TasksService} from "../../../services/tasks.service";

@Component({
  selector: 'app-addTask',
  templateUrl: './add-task.component.html',
  styleUrls: ['./add-task.component.css']
})
export class AddTaskComponent implements OnInit {

  currentTask: any = {};
  isAdded = false;

  constructor(private taskService : TasksService) { }

  ngOnInit(): void {
  }

  addTask(): void{
    this.taskService.addTask(this.currentTask);
    this.isAdded = true;
  }


}
