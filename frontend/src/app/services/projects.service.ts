import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {ProjectsInfo} from "../projects/projects-info";

@Injectable({
  providedIn: 'root'
})
export class ProjectsService {

  private projectListUrl = 'http://localhost:8080/api/v1/projects/projectList';

  private projectUrl = 'http://localhost:8080/api/v1/projects/usersProject/';

  constructor(private http: HttpClient) { }

  getAllProjects() : Observable<ProjectsInfo[]> {
    return this.http.get<ProjectsInfo[]>(this.projectListUrl, {responseType: "json"});
  }

  getProjectFromUserId(id: number) : Observable<ProjectsInfo[]> {
    return this.http.get<ProjectsInfo[]>(this.projectUrl + id);
  }
}
