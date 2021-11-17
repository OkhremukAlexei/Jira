export class TasksInfo {
  id: number;
  title: string;
  description: string;
  dateTime: string;
  spentTime: string;



  constructor(id: number,title: string, description: string, dateTime: string, spentTime: string) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.dateTime = dateTime;
    this.spentTime = spentTime;

  }
}


