import { Component, OnInit } from '@angular/core';
import {ProjectsService} from "../../../services/projects.service";
import {DataTransferService} from "../../../services/data-transfer.service";
import {Router} from "@angular/router";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {FormBuilder, FormGroup} from "@angular/forms";
import {Projects} from "../../../models/projects-info";

@Component({
  selector: 'app-update-project',
  templateUrl: './update-project.component.html',
  styleUrls: ['./update-project.component.css']
})
export class UpdateProjectComponent implements OnInit {

  currentProject: Projects;
  editForm: FormGroup;

  constructor(private projectService : ProjectsService, private data: DataTransferService,
              private router : Router, private modalService: NgbModal, private fb: FormBuilder) { }

  ngOnInit(): void {
    this.data.currentProject.subscribe(project => this.currentProject = project);
    this.editForm = this.fb.group({
      name: [''],
      linkToGit: ['']
    } );
  }

  updateProject(): void {
    this.projectService.updateProject(this.currentProject.id, this.editForm.value)
      .subscribe((results) => {
          this.ngOnInit();
          this.modalService.dismissAll();
        }
      );
  }

}
