import { Routes } from '@angular/router';

import { DashboardComponent } from '../../pages/dashboard/dashboard.component';
import {UsersComponent} from "../../pages/admin/users/users.component";
import {ProjectListComponent} from "../../pages/projects/project-list/project-list.component";
import {ProjectDetailsComponent} from "../../pages/projects/project-details/project-details.component";
import {PeopleComponent} from "../../pages/projects/people/people.component";
import {ProfileComponent} from "../../pages/profile/profile.component";
import {TaskDetailsComponent} from "../../pages/tasks/task-details/task-details.component";


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
  },
  {
    path: 'profile',
    component: ProfileComponent
  },
  {
    path: 'tasks/task-details/:id',
    component:TaskDetailsComponent
  }
];
