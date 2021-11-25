import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { ClipboardModule } from 'ngx-clipboard';

import { AdminLayoutRoutes } from './admin-layout.routing';
import { DashboardComponent } from '../../pages/dashboard/dashboard.component';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import {UsersComponent} from "../../pages/admin/users/users.component";
import {ProjectDetailsComponent} from "../../pages/projects/project-details/project-details.component";
import {ProjectListComponent} from "../../pages/projects/project-list/project-list.component";
import {PeopleComponent} from "../../pages/projects/people/people.component";
import {AddProjectComponent} from "../../pages/projects/add-project/add-project.component";
import {SearchPipe} from "../../pipes/search.pipe";
// import { ToastrModule } from 'ngx-toastr';

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(AdminLayoutRoutes),
    FormsModule,
    HttpClientModule,
    NgbModule,
    ClipboardModule
  ],
    declarations: [
        DashboardComponent,
        UsersComponent,
        ProjectDetailsComponent,
        ProjectListComponent,
        PeopleComponent,
        AddProjectComponent,
        SearchPipe
    ]
})

export class AdminLayoutModule {}
