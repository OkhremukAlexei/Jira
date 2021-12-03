import { Injectable } from '@angular/core';
import {BehaviorSubject} from "rxjs";
import {Projects} from "../models/projects-info";

@Injectable({
  providedIn: 'root'
})
export class DataTransferService {

  private project!: Projects;

  project$ = new BehaviorSubject<Projects>(this.project);
  currentProject = this.project$.asObservable();

  constructor() { }

  setProject(data: Projects){
    this.project$.next(data);
  }

}
