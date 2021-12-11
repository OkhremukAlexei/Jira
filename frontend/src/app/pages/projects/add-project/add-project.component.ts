import {Component, OnInit, TemplateRef} from '@angular/core';
import {ProjectsService} from "../../../services/projects.service";
import {DataTransferService} from "../../../services/data-transfer.service";

@Component({
  selector: 'app-add-project',
  templateUrl: './add-project.component.html',
  styleUrls: ['./add-project.component.css']
})
export class AddProjectComponent implements OnInit {

  currentProject: any = {};
  isAdded = false;
  add: TemplateRef<any>

  constructor(private projectService : ProjectsService, private data: DataTransferService) { }

  ngOnInit(): void {
//    this.data.project$.subscribe(project => this.currentProject = project);
  }

  addProject(): void{
    this.projectService.addProject(this.currentProject);
    this.data.setProject(this.currentProject);
    this.isAdded = true;
  }

}
