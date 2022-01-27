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
    public user:User;

    public ngOnInit() {

    }


    public constructor(private tokenStorageService:TokenStorageService, private userService:UserService) {
      this.id=this.tokenStorageService.getId();
      console.log(this.id);



      this.user$ = this.userService.getUser(this.id);
        this.user$.subscribe(user => {
          this.user = user
          console.log(user)
        }
     )
    }
}
