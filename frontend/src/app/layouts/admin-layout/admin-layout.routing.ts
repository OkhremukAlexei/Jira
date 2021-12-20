import { Routes } from '@angular/router';

import { DashboardComponent } from '../../pages/dashboard/dashboard.component';
import {UsersComponent} from "../../pages/admin/users/users.component";
import {ProjectListComponent} from "../../pages/projects/project-list/project-list.component";
import {ProjectDetailsComponent} from "../../pages/projects/project-details/project-details.component";
import {PeopleComponent} from "../../pages/projects/people/people.component";


export const AdminLayoutRoutes: Routes = [
  {
    path: 'dashboard',
    component: DashboardComponent
  },
  {
    path: 'users',
    component: UsersComponent
  },
  {
    path: 'projects',
    component: ProjectListComponent
  },
  {
    path: 'projects/project-details/:id',
    component:ProjectDetailsComponent
  },
  {
    path: 'projects/people',
    component: PeopleComponent
  }
];
