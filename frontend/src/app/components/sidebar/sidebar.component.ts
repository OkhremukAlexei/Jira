import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {TokenStorageService} from "../../services/token-storage.service";
import {ProjectsService} from "../../services/projects.service";
import {Projects} from "../../models/projects-info";

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

  public menuItems: any[];
  public isCollapsed = true;
  listProjects !: Projects[];
  isAuthorized: boolean = false;
  roles!: string[];
  authority!: string;

  constructor(private router: Router, private token: TokenStorageService, private projectService : ProjectsService) { }

  ngOnInit() {
    this.menuItems = ROUTES.filter(menuItem => menuItem);
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

  openProjectDetails(id: number) {
    this.router.navigate(['projects/project-details/' + id]);
  }
}
