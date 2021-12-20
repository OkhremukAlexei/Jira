import { Component, OnInit } from '@angular/core';
import {TokenStorageService} from "../../services/token-storage.service";
import {Account, User} from "../../models/users-info";
import {TasksInfo} from "../../models/tasks-info";
import {Projects} from "../../models/projects-info";
import {ProjectsService} from "../../services/projects.service";
import {TasksService} from "../../services/tasks.service";
import {UserService} from "../../services/user.service";
import {Observable, of} from "rxjs";


@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

    public id:string;
    public user$:Observable<User>;
    public tasks$:Observable<TasksInfo[]>;
    public projects$:Observable<Projects[]>;
  public user:User;
  public projects:Projects[];

    public ngOnInit() {
       }


    public constructor(private tokenStorageService:TokenStorageService, private userService:UserService, private projectService:ProjectsService, private tasksService:TasksService) {
      this.id=this.tokenStorageService.getId();
      console.log(this.id);

      this.user$ = of(new User(
     1337,
        "testLogin",
         "testPassword",
         "TEST_ROLE",
        new Account (
          "Test",
          "Surname",
           "testEmail@gmail.com"

      )
      ))

      // this.user$ = this.userService.getUser(this.id);
        this.user$.subscribe(user => {
          this.user = user
          console.log(user)
        })

      // this.projects$ = this.projectService.getProjectsFromUserId(this.id);

      this.projects$ = of([new Projects(123, "test project", "url.com", 2, 5, [this.user], this.user),
      new Projects(321, "another test project", "url2.com", 4, 1, [this.user], this.user)])

      this.projects$.subscribe(projects => {
        this.projects = projects
      })

        // this.tasks$ = this.tasksService.getAllTasksByUserId(this.id)
    }
}
