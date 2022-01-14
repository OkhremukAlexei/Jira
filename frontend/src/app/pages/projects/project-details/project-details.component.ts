import {
  Component,
  OnInit
} from '@angular/core';
import {ActivatedRoute, Params, Router} from "@angular/router";
import {ProjectsService} from "../../../services/projects.service";
import {DataTransferService} from "../../../services/data-transfer.service";
import {Projects} from "../../../models/projects-info";

import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {FormBuilder, FormGroup} from "@angular/forms";
import { switchMap } from 'rxjs/operators';
import {Observable} from "rxjs";
import {TokenStorageService} from "../../../services/token-storage.service";

@Component({
  selector: 'app-project-details',
  templateUrl: './project-details.component.html',
  styleUrls: ['./project-details.component.css'],
})
export class ProjectDetailsComponent implements OnInit{

  id: number;
  currentProject: Projects;
  public currentProject$: Observable<any>

  editForm: FormGroup;
  roles!: string[];
  authority!: string;

  constructor(private projectService : ProjectsService, private route: ActivatedRoute, private data: DataTransferService,
              private router : Router, private modalService: NgbModal, private fb: FormBuilder, private token: TokenStorageService) {

  }

  ngOnInit(): void {
    this.getAuthority();
    let projectId = this.route.snapshot.paramMap.get('id');
    this.currentProject$ = this.route.params.pipe(
      switchMap((params: Params) =>
        this.projectService.getProjectById(params['id']))
    );

    this.currentProject$.subscribe((project:any) => {
        this.currentProject = project;
        this.data.setProject(this.currentProject);
        console.log(this.currentProject.users);
      },
      error => {
        console.log(error);
      });

    this.data.currentProject.subscribe(project => this.currentProject = project);

    this.editForm = this.fb.group({
      name: [''],
      linkToGit: ['']
    } );

  }

  addPeople(){
    this.router.navigate(['projects/people']);
  }

  openEdit(targetModal: any, currentProject: Projects) {
    this.modalService.open(targetModal, {
      backdrop: 'static',
      size: 'lg'
    });
    this.editForm.patchValue( {
      name: currentProject.name,
      linkToGit: currentProject.linkToGit
    });
  }

  updateProject(): void {
    this.projectService.updateProject(this.currentProject.id, this.editForm.value)
      .subscribe((results) => {
          this.modalService.dismissAll();
          this.ngOnInit();
        });
  }

  getAuthority(): void {
    this.roles = this.token.getAuthorities();
    this.roles.every(role => {
      if (role === 'ROLE_ADMIN') {
        this.authority = 'admin';
        return false;
      } else if (role === 'ROLE_MANAGER') {
        this.authority = 'manager';
        return false;
      }
      this.authority = 'user';
      return true;
    });
  }

}
