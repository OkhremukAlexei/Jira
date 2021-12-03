import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {TokenStorageService} from "./token-storage.service";
import {Observable} from "rxjs";
import {PartialAccountInfo} from "../models/partial-account-info";
import {PartialUserInfo} from "../models/partial-user";

const httpOptions = {
  headers: new  HttpHeaders({'Content-Type':'application/json'})
}

@Injectable({
  providedIn: 'root'
})

export class AdminService {

  private userListUrl = 'http://localhost:8080/api/admin/userlist';
  private userInfoUrl = 'http://localhost:8080/api/admin/userinfo';

  constructor(private http: HttpClient, private tokenStorageService: TokenStorageService) { }


  getAllUsers(): Observable<PartialAccountInfo[]> {
    return this.http.get<PartialAccountInfo[]>(this.userListUrl);
  }

  getUserInfo(id: number): Observable<PartialUserInfo> {
    return this.http.post<PartialUserInfo>(this.userInfoUrl+'/'+id, id, httpOptions);
  }

  setManager(id: number): Observable<string> {
    return this.http.post<string>(this.userListUrl, id);
  }

  deleteUser(id: number): Observable<string> {
    return this.http.delete<string>(this.userListUrl+'/'+id, httpOptions);
  }
}
