
export class TasksInfo {
  title: string;
  description: string;
  dateTime: string;
  spentTime: string;


  constructor(title: string, description: string, dateTime: string, spentTime: string) {
    this.title = title;
    this.description = description;
    this.dateTime = dateTime;
    this.spentTime = spentTime;
  }
}
