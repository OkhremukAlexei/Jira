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
    ReactiveFormsModule
  ],
  providers: [
    httpInterceptorProviders,
    DataTransferService
  ],

  bootstrap: [AppComponent]
})

export class AppModule { }
