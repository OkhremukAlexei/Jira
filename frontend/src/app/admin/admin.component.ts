import { Component, OnInit } from '@angular/core';
import {AdminService} from "../services/admin.service";
import {AuthLoginInfo} from "../auth/login-info";
import {PartialAccountInfo} from "./partial-account-info";

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  listAccount!: PartialAccountInfo[];

  constructor(private adminService: AdminService) { }

  ngOnInit(): void {
    this.getListAccount();
  }

  getListAccount(): void {
    this.adminService.getAllUsers().subscribe(data => {
      this.listAccount = data;
    });
}
}
