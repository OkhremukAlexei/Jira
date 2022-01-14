import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {TokenStorageService} from "./token-storage.service";
import {Observable} from "rxjs";
import {PartialUserInfo} from "../models/partial-user";
import {User} from "../models/users-info";

const httpOptions = {
  headers: new  HttpHeaders({'Content-Type':'application/json'})
}

@Injectable({
  providedIn: 'root'
})

export class AdminService {

  private userListUrl = 'http://localhost:8080/api/admin/userlist';
  private userInfoUrl = 'http://localhost:8080/api/admin/userinfo';
  private userToManager = 'http://localhost:8080/api/admin/manager';

  constructor(private http: HttpClient, private tokenStorageService: TokenStorageService) { }


  getAllUsers(): Observable<User[]> {
    return this.http.get<User[]>(this.userListUrl);
  }

  getUserInfo(id: number): Observable<PartialUserInfo> {
    return this.http.post<PartialUserInfo>(this.userInfoUrl+'/'+id, id, httpOptions);
  }

  setManager(id: number) {
    console.log(id);
    return this.http.post(this.userToManager + '/' + id, id, httpOptions);
  }

  deleteUser(id: number): Observable<string> {
    return this.http.delete<string>(this.userListUrl+'/'+id, httpOptions);
  }
}
