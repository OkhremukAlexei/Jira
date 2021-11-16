import { Component, OnInit } from '@angular/core';
import {ProjectsService} from "../../services/projects.service";

@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.css']
})
export class AddProjectComponent implements OnInit {

  currentProject: any = {};
  isAdded = false;

  constructor(private projectService : ProjectsService) { }

  ngOnInit(): void {

  }

  addProject(): void{
    this.projectService.addProject(this.currentProject);
    this.isAdded = true;
  }
}
