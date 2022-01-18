import { Component, OnInit } from '@angular/core';
import {CardSettingsModel, ColumnsModel, SwimlaneSettingsModel} from "@syncfusion/ej2-angular-kanban";
import {kanbanData} from "./datasource";

@Component({
  selector: 'app-kanban-board',
  templateUrl: './kanban-board.component.html',
  styleUrls: ['./kanban-board.component.css']
})
export class KanbanBoardComponent implements OnInit {
  public data: Object[] = kanbanData;
  public cardSettings: CardSettingsModel = {
    headerField: 'Id',
    contentField: 'Summary',
    selectionType: 'Multiple'
  };
  public columns: ColumnsModel[] = [
    { headerText: 'To Do', keyField: 'Open' },
    { headerText: 'In Progress', keyField: 'InProgress' },
    { headerText: 'In Review', keyField: 'Review' },
    { headerText: 'Done', keyField: 'Close' }
  ];

  constructor() { }

  ngOnInit(): void {
  }

  public swimlaneSettings: SwimlaneSettingsModel = { keyField: 'Assignee' };

}
