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
  firstName: any;
  key: string = 'id';
  reverse: boolean = false;
  p: number = 1;

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

  search() {
    if(this.firstName == ""){
      this.ngOnInit();
    }
    else {
      this.listAccount = this.listAccount.filter(res => {
        return res.name.toLocaleLowerCase().match(this.firstName.toLocaleLowerCase());
      })
    }
  }


  sort(key: string) {
    this.key = key;
    this.reverse = !this.reverse;
  }

}
