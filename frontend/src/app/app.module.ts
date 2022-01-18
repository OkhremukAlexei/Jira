import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import {RouteReuseStrategy, RouterModule} from "@angular/router";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {ComponentsModule} from "./components/components.module";
import { AuthLayoutComponent } from './layouts/auth-layout/auth-layout.component';
import {httpInterceptorProviders} from "./interceptors/auth-interceptor";
import {DataTransferService} from "./services/data-transfer.service";
import { AdminLayoutComponent } from './layouts/admin-layout/admin-layout.component';
import { Ng2SearchPipeModule} from "ng2-search-filter";
import { Ng2OrderModule} from "ng2-order-pipe";
import {NgxPaginationModule} from "ngx-pagination";
import {KanbanModule} from "@syncfusion/ej2-angular-kanban";

@NgModule({
  declarations: [
    AppComponent,
    AuthLayoutComponent,
    AdminLayoutComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    NgbModule,
    BrowserAnimationsModule,
    ComponentsModule,
    RouterModule,
    Ng2SearchPipeModule,
    Ng2OrderModule,
    NgxPaginationModule,
    RouterModule,
    ReactiveFormsModule
  ],
  providers: [
    httpInterceptorProviders,
    DataTransferService
  ],

  exports: [
  ],
  bootstrap: [AppComponent]
})

export class AppModule { }
