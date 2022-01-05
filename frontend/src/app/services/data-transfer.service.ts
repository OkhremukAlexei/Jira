import { Injectable } from '@angular/core';
import {BehaviorSubject} from "rxjs";
import {Projects} from "../models/projects-info";
import {TasksInfo} from "../models/tasks-info";

@Injectable({
  providedIn: 'root'
})
export class DataTransferService {

  private project!: Projects;
  private task!: TasksInfo;

  project$ = new BehaviorSubject<Projects>(this.project);
  currentProject = this.project$.asObservable();

  task$ = new BehaviorSubject<TasksInfo>(this.task);
  currentTask = this.task$.asObservable();

  constructor() { }

  setProject(data: Projects){
    this.project$.next(data);
  }

  setTask(data: TasksInfo){
    this.task$.next(data);
  }

}
