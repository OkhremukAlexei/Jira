import { Component, OnInit } from '@angular/core';
import {AdminService} from "../../../services/admin.service";
import {Router} from "@angular/router";
import {User} from "../../../models/users-info";

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  listUsers!: User[];
  firstName: any;
  key: string = 'id';
  reverse: boolean = false;
  p: number = 1;
  isUser: boolean = true;

  constructor(private adminService: AdminService, private router: Router) { }

  ngOnInit(): void {
    this.getListAccount();

  }

  getListAccount(): void {
    this.adminService.getAllUsers().subscribe(data => {
      this.listUsers = data;

    });
  }

  setUserManager(id: number): void {
    this.adminService.setManager(id).subscribe();
  }

  deleteUser(id: number): void {
    this.adminService.deleteUser(id).subscribe();
    this.isUser = false;
  }

  search() {
    if(this.firstName == ""){
      this.ngOnInit();
    }
    else {
      this.listUsers = this.listUsers.filter(res => {
        return res.account.name.toLocaleLowerCase().match(this.firstName.toLocaleLowerCase());
      })
    }
  }


  sort(key: string) {
    this.key = key;
    this.reverse = !this.reverse;
  }

}
