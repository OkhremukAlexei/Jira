import { Injectable } from '@angular/core';

const TOKEN_KEY = 'AuthToken';
const LOGIN_KEY = 'AuthLogin';
const AUTHORITIES_KEY = 'AuthAuthorities';

@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {
  private roles: Array<string> = [];

  constructor() { }

  signOut() {
    window.sessionStorage.clear();
  }

  public saveToken(token: string) {
    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.setItem(TOKEN_KEY,token);
  }

  public getToken():string {
    return <string>sessionStorage.getItem(TOKEN_KEY);
  }

  public saveLogin(login: string) {
    window.sessionStorage.removeItem(LOGIN_KEY);
    window.sessionStorage.setItem(LOGIN_KEY,login);
  }

  public getLogin():string {
    return <string>sessionStorage.getItem(LOGIN_KEY);
  }

  public saveAuthorities(authorities: string[]) {
    console.log(authorities);
    window.sessionStorage.removeItem(AUTHORITIES_KEY);
    window.sessionStorage.setItem(AUTHORITIES_KEY, JSON.stringify(authorities));
  }

  public getAuthorities():string[] {
    this.roles = [];

    if (sessionStorage.getItem(TOKEN_KEY)) {
      console.log(sessionStorage.getItem(AUTHORITIES_KEY));
      JSON.parse(<string>sessionStorage.getItem(AUTHORITIES_KEY)).forEach((authority: any) => {
        this.roles.push(authority);
      });
    }

    return this.roles;
  }
}
