import { Component, OnInit } from '@angular/core';
import {SignUpInfo} from "../../models/auth/signup-info";
import {AuthService} from "../../services/auth.service";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  form: any = {};
  signupInfo!: SignUpInfo;
  isManager = false;
  isSignedUp = false;
  isSignUpFailed = false;
  errorMessage = '';

  constructor(private authService: AuthService, private modalService: NgbModal) { }

  ngOnInit() { }

  onChange() {
    this.form.isManager = !this.form.isManager;
  }

  onSubmit(content: any) {
    console.log(this.form);

    this.signupInfo = new SignUpInfo(
      this.form.name,
      this.form.surname,
      this.form.email,
      this.form.login,
      this.form.password,
      this.form.isManager);

    this.authService.signUp(this.signupInfo).subscribe(
      data => {
        this.isSignedUp = true;
        this.isSignUpFailed = false;
      },
      error => {
        this.errorMessage = error.error.message;
        this.isSignUpFailed = true;
      }
    );

    if (!this.isSignedUp) {
      this.modalService.open(content);
    }
  }

}
