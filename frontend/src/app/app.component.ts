import { Component } from '@angular/core';
import {TokenStorageService} from "./auth/token-storage.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  isAuthorized: boolean = false;
  roles!: string[];
  authority!: string;
  title = 'frontend';

  constructor(private tokenStorage: TokenStorageService) { }

  ngOnInit() {
    if (this.tokenStorage.getToken()) {
      this.isAuthorized = true;
      this.roles = this.tokenStorage.getAuthorities();
      this.roles.every(role => {
        if (role === 'ROLE_ADMIN') {
          this.authority = 'admin';
          return false;
        } else if (role === 'ROLE_MANAGER') {
          this.authority = 'manager';
          return false;
        }
        this.authority = 'user';
        return true;
      });
    }
  }

}
