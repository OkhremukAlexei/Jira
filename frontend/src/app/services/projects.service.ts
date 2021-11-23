import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {ProjectsInfo} from "../projects/projects-info";
import {catchError, map, tap} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class ProjectsService {

  private projectUrl = 'http://localhost:8080/api/v1/projects';

  constructor(private http: HttpClient) { }

  getAllProjects() : Observable<ProjectsInfo[]> {
    return this.http.get<ProjectsInfo[]>(`${this.projectUrl}/projectList`, {responseType: "json"});
  }

  getProjectFromUserId(id: any) : Observable<ProjectsInfo> {
      return this.http.get<ProjectsInfo>(`${this.projectUrl}/usersProject/${id}`, {responseType: "json"});
  }

  getProjectById(id: any) {
    return this.http.get(`${this.projectUrl}/${id}`);
  }

  updateProject(id: number, data: ProjectsInfo) {
    return this.http.put(`${this.projectUrl}/${id}`, data).subscribe(data=>console.log(data));
  }

  addProject(data: ProjectsInfo) {
    return this.http.post(`${this.projectUrl}`, data)
  }
}
