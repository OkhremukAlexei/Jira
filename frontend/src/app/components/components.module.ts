import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {NgbModule} from "@ng-bootstrap/ng-bootstrap";
import {RouteReuseStrategy, RouterModule} from "@angular/router";
import {FooterComponent} from "./footer/footer.component";
import {NavbarComponent} from "./navbar/navbar.component";
import {SidebarComponent} from "./sidebar/sidebar.component";
import {FormsModule} from "@angular/forms";
import { KanbanBoardComponent } from './kanban-board/kanban-board.component';
import {KanbanModule} from "@syncfusion/ej2-angular-kanban";

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    NgbModule,
    FormsModule,
    KanbanModule
  ],
  declarations: [
    FooterComponent,
    NavbarComponent,
    SidebarComponent,
    KanbanBoardComponent
  ],
    exports: [
        FooterComponent,
        NavbarComponent,
        SidebarComponent,
        KanbanBoardComponent
    ]
})
export class ComponentsModule { }
