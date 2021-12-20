import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Projects} from "../models/projects-info";

@Injectable({
  providedIn: 'root'
})
export class ProjectsService {

  private projectUrl = 'http://localhost:8080/api/v1/projects';

  constructor(private http: HttpClient) { }

  getAllProjects() : Observable<Projects[]> {
    return this.http.get<Projects[]>(`${this.projectUrl}/projectList`, {responseType: "json"});
  }

  getProjectsFromUserId(id: any) : Observable<Projects[]> {
    return this.http.get<Projects[]>(`${this.projectUrl}/usersProject/${id}`, {responseType: "json"});
  }

  getProjectById(id: any) : Observable<Projects>{
    return this.http.get<Projects>(`${this.projectUrl}/${id}`);
  }

  updateProject(id: number, data: Projects) {
    return this.http.put(`${this.projectUrl}/${id}`, data);
  }

  addProject(data: Projects) : Observable<Projects>{
    return this.http.post<Projects>(`${this.projectUrl}`, data);
  }

  addPeopleToProject(data: Projects){
    return this.http.put(`${this.projectUrl}/people`, data).subscribe(data => console.log(data));
  }

  deletePeopleInProject(idProject: number, idUser: number){
    return this.http.delete(`${this.projectUrl}/project/${idProject}/user/${idUser}`).subscribe();
  }

}
