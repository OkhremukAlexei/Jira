import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {ProjectsInfo} from "../projects/projects-info";

@Injectable({
  providedIn: 'root'
})
export class ProjectsService {

  private projectsUrl = 'http://localhost:8080/api/v1/projects';

  constructor(private http: HttpClient) { }

  getAllProjects() : Observable<ProjectsInfo[]> {

    return this.http.get<ProjectsInfo[]>(this.projectsUrl, {responseType: "json"})
  }
}
