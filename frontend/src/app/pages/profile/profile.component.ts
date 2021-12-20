import { Component, OnInit } from '@angular/core';
import {TokenStorageService} from "../../services/token-storage.service";


@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
    public id:string;
    public ngOnInit() {
      this.id=this.tokenStorageService.getId();
      console.log(this.id);
    }
    public constructor(private tokenStorageService:TokenStorageService) {

    }
}
