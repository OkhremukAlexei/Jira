import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {PartialAccountInfo} from "../admin/partial-account-info";
import {TokenStorageService} from "../auth/token-storage.service";


@Injectable({
  providedIn: 'root'
})
export class AdminService {

  private userListUrl = 'http://localhost:8080/api/admin/userlist';

  constructor(private http: HttpClient, private tokenStorageService: TokenStorageService) { }


  getAllUsers(): Observable<PartialAccountInfo[]> {
    return this.http.get<PartialAccountInfo[]>(this.userListUrl);
  }

  //getUserInfo(): Observable<>
}
