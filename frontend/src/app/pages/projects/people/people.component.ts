import { Component, OnInit } from '@angular/core';
import {User} from "../../../models/users-info";
import {DataTransferService} from "../../../services/data-transfer.service";
import {Projects} from "../../../models/projects-info";
import {UserService} from "../../../services/user.service";

@Component({
  selector: 'app-people',
  templateUrl: './people.component.html',
  styleUrls: ['./people.component.css']
})
export class PeopleComponent implements OnInit {

  public searchFilter: any = '';
  public query: any;
  public listAccount!: User[];
  public currentProject: Projects;

  constructor(private userService: UserService, private data: DataTransferService) { }

  ngOnInit(): void {
    this.getUsers();
    this.data.currentProject.subscribe(project => this.currentProject = project);
    console.log(this.currentProject);

  }

  getUsers(): void {
    this.userService.getUsers().subscribe(data => {
      this.listAccount = data;
    });
  }

  addPerson(currentUser: User){
    //this.currentProject.setUsers(this.currentProject.getUsersInProject.push(currentUser));
    console.log(currentUser);
    this.currentProject?.users.push(currentUser);
    console.log(this.currentProject);
  }

}
