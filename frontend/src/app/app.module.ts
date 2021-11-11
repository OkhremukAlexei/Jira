import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { HomeComponent } from './home/home.component';
import { UserComponent } from './user/user.component';
import { ManagerComponent } from './manager/manager.component';
import {FormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {httpInterceptorProviders} from "./auth/auth-interceptor";
import { AccountComponent } from './account/account.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { UserlistComponent } from './admin/userlist/userlist.component';
import {ProjectsComponent} from "./projects/project-list/projects.component";
import {UserProjectComponent} from "./projects/user-project/user-project.component";
import { ProjectDetailsComponent } from './projects/project-details/project-details.component';
import { AddProjectComponent } from './projects/add/add.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    HomeComponent,
    UserComponent,
    ManagerComponent,
    AccountComponent,
    UserlistComponent,
    ProjectsComponent,
    UserProjectComponent,
    ProjectDetailsComponent,
    AddProjectComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    NgbModule
  ],
  providers: [httpInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
