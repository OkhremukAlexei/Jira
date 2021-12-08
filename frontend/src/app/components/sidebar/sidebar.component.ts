import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {TokenStorageService} from "../../services/token-storage.service";
import {ProjectsService} from "../../services/projects.service";
import {Projects} from "../../models/projects-info";
import {ModalDismissReasons, NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {NgForm} from "@angular/forms";

declare interface RouteInfo {
  path: string;
  title: string;
  icon: string;
  class: string;
}
export const ROUTES: RouteInfo[] = [
  { path: '/dashboard', title: 'Dashboard',  icon: 'ni-tv-2 text-primary', class: '' }
];

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {

  public isCollapsed = true;
  listProjects !: Projects[];
  isAuthorized: boolean = false;
  roles!: string[];
  authority!: string;

  closeResult: string;
  currentProject: any = {};

  constructor(private router: Router, private token: TokenStorageService, private projectService : ProjectsService,
              private modalService: NgbModal) { }

  ngOnInit() {
    this.router.events.subscribe((event) => {
      this.isCollapsed = true;
    });
    this.getAuthority();
    this.getProjects();

  }

  logout() {
    this.token.signOut();
    window.location.reload();
  }

  getProjects() : void {
    this.projectService.getProjectsFromUserId(this.token.getId()).
      subscribe(data => {this.listProjects = data});
  }

  getAuthority(): void {
    if (this.token.getToken()) {
      this.isAuthorized = true;
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

  open(content: any) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${SidebarComponent.getDismissReason(reason)}`;
    });
  }

  private static getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
  }

  onSubmit() {
    console.log(this.currentProject);
    this.projectService.addProject(this.currentProject)
      .subscribe((result) => {
        this.ngOnInit(); //reload the table
      });
    this.modalService.dismissAll(); //dismiss the modal
  }
}
