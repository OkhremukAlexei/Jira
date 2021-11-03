import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {PartialAccountInfo} from "../admin/partial-account-info";
import {TokenStorageService} from "../auth/token-storage.service";
import {UserInfo} from "../admin/user-info";

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

  getUserInfo(id: number): Observable<UserInfo> {
    return this.http.post<UserInfo>(this.userInfoUrl, id, httpOptions);
  }
}
