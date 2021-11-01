import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from "@angular/router";
import {HomeComponent} from "./home/home.component";
import {UserComponent} from "./user/user.component";
import {ManagerComponent} from "./manager/manager.component";
import {AdminComponent} from "./admin/admin.component";
import {LoginComponent} from "./login/login.component";
import {RegisterComponent} from "./register/register.component";
import {ProjectsComponent} from "./projects/project-list/projects.component";

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
    path: 'admin',
    component:AdminComponent
  },
  {
    path: 'auth/login',
    component:LoginComponent
  },
  {
    path: 'signup',
    component:RegisterComponent
  },
  {
    path: 'projects',
    component:ProjectsComponent
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
