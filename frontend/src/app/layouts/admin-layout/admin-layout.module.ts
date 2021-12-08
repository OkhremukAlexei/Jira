import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import {RouteReuseStrategy, RouterModule} from '@angular/router';
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
import {TasksComponent} from "../../pages/tasks/task-list/tasks.component";
import {TaskDetailsComponent} from "../../pages/tasks/task-details/task-details.component";
import {AddTaskComponent} from "../../pages/tasks/add-task/add-task.component";
import {UpdateProjectComponent} from "../../pages/projects/update-project/update-project.component";
// import { ToastrModule } from 'ngx-toastr';

@NgModule({
    imports: [
        CommonModule,
        RouterModule.forChild(AdminLayoutRoutes),
        FormsModule,
        HttpClientModule,
        NgbModule,
        ClipboardModule,
        ReactiveFormsModule,
    ],
    declarations: [
        DashboardComponent,
        UsersComponent,
        ProjectDetailsComponent,
        ProjectListComponent,
        PeopleComponent,
        AddProjectComponent,
        SearchPipe,
        TasksComponent,
        TaskDetailsComponent,
        AddTaskComponent,
        UpdateProjectComponent
    ]
})

export class AdminLayoutModule {}
