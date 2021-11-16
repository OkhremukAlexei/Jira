import { Component, OnInit } from '@angular/core';
import {UserService} from "../../services/user.service";
import {PartialAccountInfo} from "../../admin/partial-account-info";
import {UserInfo} from "./users-info";

@Component({
  selector: 'app-people',
  templateUrl: './people.component.html',
  styleUrls: ['./people.component.css']
})
export class PeopleComponent implements OnInit {

  public searchFilter: any = '';
  public query: any;
  listAccount!: UserInfo[];

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.getUsers();
  }

  getUsers(): void {
    this.userService.getUsers().subscribe(data => {
      this.listAccount = data;
    });
  }

}
