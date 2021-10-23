import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {PartialAccountInfo} from "../admin/partial-account-info";
import {TokenStorageService} from "../auth/token-storage.service";

const httpOptions = {
  headers: new HttpHeaders({
    'Content-type':'application/x-www-form-urlencode',
    Authorization: "Bearer " + window.sessionStorage.getItem('AuthToken')
  })
};

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  private adminUrl = 'http://localhost:8080/api/admin/userlist';

  constructor(private http: HttpClient, private tokenStorageService: TokenStorageService) { }


  getAllUsers(): Observable<PartialAccountInfo[]> {
    return this.http.get<PartialAccountInfo[]>(this.adminUrl, httpOptions);
  }
}