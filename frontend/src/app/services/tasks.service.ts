import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable, Subject, Subscription} from "rxjs";
import {TasksInfo} from "../models/tasks-info";
import {catchError, map, tap} from "rxjs/operators";
import {DataSourceChangedEventArgs, DataStateChangeEventArgs} from "@syncfusion/ej2-angular-kanban";

@Injectable({
  providedIn: 'root'
})
export class TasksService extends Subject<DataStateChangeEventArgs>{

  private taskUrl = 'http://localhost:8080/api/v1/tasks';
  private updateUrl: string;

  constructor(private http: HttpClient) { super();}

  public execute(state: any, id: number, userId:string): void {
    this.getData(state, id, userId).subscribe(x => super.next(x));
  }

  protected getData(state: DataStateChangeEventArgs, id: number, userId:string): Observable<DataStateChangeEventArgs> {
    if(userId == "0") {
      return this.getProjectTasks(id)
        .pipe(map((response: any) => (<any>{
          result: response
        })))
        .pipe((data: any) => data);
    }
    else {
      return this.getProjectUserTasks(id, userId)
        .pipe(map((response: any) => (<any>{
          result: response
        })))
        .pipe((data: any) => data);
    }
  }

  getAllTasks() : Observable<TasksInfo[]> {
    return this.http.get<TasksInfo[]>(`${this.taskUrl}`, {responseType: "json"});
  }

  getTaskById(id: any) {
    return this.http.get(`${this.taskUrl}/task/${id}`);
  }
  getAllTasksByUserId(id:string):Observable<TasksInfo[]>{
    return this.http.get<TasksInfo[]>(`${this.taskUrl}/user/${id}`)
  }

  updateTask(id: number, data: TasksInfo) {
    return this.http.put(`${this.taskUrl}/${id}`, data).subscribe(data=>console.log(data));
  }

  addTask(data: TasksInfo) {
    return this.http.post(`${this.taskUrl}`, data)
  }

  getProjectUserTasks(projectId: any, userId: any) : Observable<TasksInfo[]>{
    return this.http.get<TasksInfo[]>(`${this.taskUrl}/project/${projectId}/user/${userId}`, {responseType: "json"});
  }

  getProjectTasks(id: any) {
    return this.http.get<TasksInfo[]>(`${this.taskUrl}/project/${id}`, {responseType: "json"});
  }

  deleteCard(state: any) {
    const id = state.deletedRecords[0].id;
    const url = `${this.taskUrl}/${id}`;
    return this.http.delete(url);
  }

  updateCard(state: DataSourceChangedEventArgs): Subscription {

    // @ts-ignore
    const id = state.changedRecords[0].id;

    return this.http.put(`${this.taskUrl}/task/${id}`, state.changedRecords?.[0]).subscribe(data=>console.log(data));
  }
}
