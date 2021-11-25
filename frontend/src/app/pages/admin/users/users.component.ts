import { Component, OnInit } from '@angular/core';
import {PartialAccountInfo} from "../../../models/partial-account-info";
import {AdminService} from "../../../services/admin.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  listAccount!: PartialAccountInfo[];

  constructor(private adminService: AdminService, private router: Router) { }

  ngOnInit(): void {
    this.getListAccount();
  }

  getListAccount(): void {
    this.adminService.getAllUsers().subscribe(data => {
      this.listAccount = data;
    });
  }

  setUserManager(id: number): void {
    console.log("button work" + id);

  }

  deleteUser(id: number): void {
    this.adminService.deleteUser(id).subscribe();
  }

}
