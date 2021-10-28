import { Component, OnInit } from '@angular/core';
import {PartialAccountInfo} from "../partial-account-info";
import {AdminService} from "../../services/admin.service";

@Component({
  selector: 'app-userlist',
  templateUrl: './userlist.component.html',
  styleUrls: ['./userlist.component.css']
})
export class UserlistComponent implements OnInit {

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

  setUserManager(id: number): void {
    console.log("button work" + id);

  }

}
