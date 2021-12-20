import { Component, OnInit } from '@angular/core';
import {Projects} from "../../models/projects-info";
import {Router} from "@angular/router";
import {ProjectsService} from "../../services/projects.service";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  listProjects !: Projects[];

  constructor(private projectService : ProjectsService, private router : Router) {
  }

  ngOnInit(): void {
    this.projectService.getAllProjects()
      .subscribe(data => {
        this.listProjects = data
      });
  }


  updateProject(id: number) {
    this.router.navigate(['projects/project-details', id]);
  }

}
