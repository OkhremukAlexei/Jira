import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import {RouterModule} from '@angular/router';
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
import {SearchPipe} from "../../pipes/search.pipe";
import {TasksComponent} from "../../pages/tasks/task-list/tasks.component";
import {TaskDetailsComponent} from "../../pages/tasks/task-details/task-details.component";
import {AddTaskComponent} from "../../pages/tasks/add-task/add-task.component";
import {Ng2OrderModule} from "ng2-order-pipe";
import {NgxPaginationModule} from "ngx-pagination";
import {ComponentsModule} from "../../components/components.module";
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
        Ng2OrderModule,
        NgxPaginationModule,
        ComponentsModule
    ],
    declarations: [
        DashboardComponent,
        UsersComponent,
        ProjectDetailsComponent,
        ProjectListComponent,
        PeopleComponent,
        SearchPipe,
        TasksComponent,
        TaskDetailsComponent,
        AddTaskComponent
    ]
})

export class AdminLayoutModule {}
