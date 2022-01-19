import {Component, Input, OnInit} from '@angular/core';
import {TokenStorageService} from "../../services/token-storage.service";

@Component({
  selector: 'app-admin-layout',
  templateUrl: './admin-layout.component.html',
  styleUrls: ['./admin-layout.component.css']
})
export class AdminLayoutComponent implements OnInit {
  isAuthorized: boolean = false;
  roles!: string[];
  authority!: string;

  constructor(private token: TokenStorageService,) { }

  ngOnInit(): void {
    this.getAuthority();
  }

  getAuthority(): void {
    if (this.token.getToken()) {
      this.isAuthorized = true;
      this.roles = this.token.getAuthorities();
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
