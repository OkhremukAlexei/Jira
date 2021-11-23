import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from "@angular/router";
import {HomeComponent} from "./home/home.component";
import {UserComponent} from "./user/user.component";
import {ManagerComponent} from "./manager/manager.component";
import {LoginComponent} from "./login/login.component";
import {RegisterComponent} from "./register/register.component";
import {AccountComponent} from "./account/account.component";
import {UserlistComponent} from "./admin/userlist/userlist.component";
import {ProjectsComponent} from "./projects/project-list/projects.component";
import {UserProjectComponent} from "./projects/user-project/user-project.component";
import {ProjectDetailsComponent} from "./projects/project-details/project-details.component";
import {AddProjectComponent} from "./projects/add/add.component";
import {TasksComponent} from "./tasks/task-list/tasks.component";
import {TaskDetailsComponent} from "./tasks/task-details/task-details.component";
import {AddTaskComponent} from "./tasks/add-task/add-task.component";

const routes: Routes = [
  {
    path: 'home',
    component:HomeComponent
  },
  {
    path: 'user',
    component:UserComponent
  },
  {
    path: 'manager',
    component:ManagerComponent
  },
  {
    path: 'admin/userlist',
    component:UserlistComponent
  },
  {
    path: 'account',
    component:AccountComponent
  },
  {
    path: 'projects/project-list',
    component:ProjectsComponent
  },
  {
    path: 'projects/user-project',
    component:UserProjectComponent
  },
  {
    path: 'projects/project-details/:id',
    component:ProjectDetailsComponent
  },
  {
    path: 'projects/add',
    component:AddProjectComponent
  },
 /* {
    path: 'tasks/task-list',
    component:TasksComponent
  },
  {
    path: 'tasks/task-details/:id',
    component:TaskDetailsComponent
  },
  {
    path: 'tasks/add-task',
    component:AddTaskComponent
  },*/
  {
    path: 'signin',
    component:LoginComponent
  },
  {
    path: 'signup',
    component:RegisterComponent
  },
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full'
  }
]

@NgModule({
  declarations: [],
  exports: [RouterModule],
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ]
})
export class AppRoutingModule { }
