import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "../models/users-info";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private userTestUrl = 'http://localhost:8080/api/test/user';
  private managerUrl = 'http://localhost:8080/api/test/manager';
  private adminUrl = 'http://localhost:8080/api/test/admin';

  private userUrl = 'http://localhost:8080/api/v1/users';

  constructor(private http: HttpClient) { }

  getUserBoard(): Observable<string> {
    return this.http.get(this.userTestUrl, {responseType: 'text'});
  }

  getManagerBoard(): Observable<string> {
    return this.http.get(this.managerUrl, {responseType: 'text'});
  }

  getAdminBoard(): Observable<string> {
    return this.http.get(this.adminUrl, {responseType: 'text'});
  }

  getUsers(): Observable<User[]>{
    return this.http.get<User[]>(`${this.userUrl}/roleUser`, {responseType: "json"});
  }

  getUsersOutsideProject(id: number): Observable<User[]>{
    return this.http.get<User[]>(`${this.userUrl}/project/${id}`, {responseType: "json"});
  }

  getUser(id: any) :Observable<User>{
    return this.http.get<User>( `${this.userUrl}/${id}`, {responseType: "json"})
  }

}
