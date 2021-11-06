import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {TasksInfo} from "../tasks/tasks-info";

@Injectable({
  providedIn: 'root'
})
export class TasksService {

  private projectsUrl = 'http://localhost:8080/api/v1/tasks';

  constructor(private http: HttpClient) { }

  getAllProjects() : Observable<TasksInfo[]> {

    return this.http.get<TasksInfo[]>(this.projectsUrl, {responseType: "json"})
  }
}
