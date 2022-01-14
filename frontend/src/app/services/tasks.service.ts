import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {TasksInfo} from "../models/tasks-info";
import {catchError, map, tap} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class TasksService {

  private taskUrl = 'http://localhost:8080/api/v1/tasks';

  constructor(private http: HttpClient) { }

  getAllTasks() : Observable<TasksInfo[]> {
    return this.http.get<TasksInfo[]>(`${this.taskUrl}`, {responseType: "json"});
  }

  getTaskById(id: any) {
    return this.http.get(`${this.taskUrl}/${id}`);
  }

  updateTask(id: number, data: TasksInfo) {
    return this.http.put(`${this.taskUrl}/${id}`, data).subscribe(data=>console.log(data));
  }

  addTask(data: TasksInfo) {
    return this.http.post(`${this.taskUrl}`, data)
  }

  startTask(id: any, data: TasksInfo) {
    return this.http.put(`${this.taskUrl}/${id}/start`, data);
  }

  getProjectUserTasks(projectId: any, userId: any) : Observable<TasksInfo[]>{
    return this.http.get<TasksInfo[]>(`${this.taskUrl}/project/${projectId}/user/${userId}`, {responseType: "json"});
  }

  completeTask(id: number, data: TasksInfo) {
    return this.http.put(`${this.taskUrl}/${id}/complete`, data);
  }

  closeTask(id: number, data: TasksInfo) {
    return this.http.put(`${this.taskUrl}/${id}/close`, data);
  }

  getProjectTasks(id: any) {
    return this.http.get<TasksInfo[]>(`${this.taskUrl}/project/${id}`, {responseType: "json"});
  }
}
