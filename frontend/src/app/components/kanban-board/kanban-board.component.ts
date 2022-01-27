import { Component, OnInit } from '@angular/core';
import { L10n } from '@syncfusion/ej2-base';
import {CardSettingsModel, ColumnsModel, DialogEventArgs, SwimlaneSettingsModel} from "@syncfusion/ej2-angular-kanban";
import {kanbanData} from "../../pages/tasks/task-list/datasource";

L10n.load({
  'ru': {
    'kanban': {
      'items': 'Задача',
      'min': 'Мин',
      'max': 'Макс',
      'cardsSelected': 'Задача выбрана',
      'addTitle': 'Добавить задачу',
      'editTitle': 'Изменить задачу',
      'deleteTitle': 'Удалить задачу',
      'deleteContent': 'Вы уверены, что хотите удалить эту задачу?',
      'save': 'Сохранить',
      'delete': 'Удалить',
      'cancel': 'Отмена',
      'yes': 'Да',
      'no': 'Нет',
      'close': 'Закрыть',
      'noCard': 'Нет задач для отображения',
      'unassigned': 'Не назначен'
    }
  }
});

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
    { headerText: 'Новая', keyField: 'Open' },
    { headerText: 'В процессе', keyField: 'InProgress' },
    { headerText: 'Выполнена', keyField: 'Review' },
    { headerText: 'Закрыта', keyField: 'Close' }
  ];

  constructor() { }

  ngOnInit(): void {
  }

  public swimlaneSettings: SwimlaneSettingsModel = { keyField: 'Assignee' };

  dialogOpen(args: DialogEventArgs): void {

  }
}
