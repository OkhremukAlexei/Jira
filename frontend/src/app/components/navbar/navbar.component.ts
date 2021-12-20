import { Component, OnInit, ElementRef } from '@angular/core';
import { ROUTES } from '../sidebar/sidebar.component';
import { Location, LocationStrategy, PathLocationStrategy } from '@angular/common';
import { Router } from '@angular/router';
import {TokenStorageService} from "../../services/token-storage.service";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  public focus: any;
  public listTitles: any[];
  public location: Location;
  public info :any;

  constructor(location: Location,  private element: ElementRef, private router: Router, private token: TokenStorageService) {
    this.location = location;
  }

  ngOnInit() {
    this.listTitles = ROUTES.filter(listTitle => listTitle);
    this.info = {
      login : this.token.getLogin(),
    };
  }
  getTitle(){
    let title = this.location.prepareExternalUrl(this.location.path());
    if(title.charAt(0) === '#'){
      title = title.slice( 1 );
    }

    for(let item = 0; item < this.listTitles.length; item++){
      if(this.listTitles[item].path === title){
        return this.listTitles[item].title;
      }
    }
    return 'Dashboard';
  }

}
