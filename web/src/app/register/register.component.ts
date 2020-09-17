import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import {FormBuilder, FormGroup, Validators } from '@angular/forms';
import {AuthService} from '../auth.service';
import {User} from '../dto/user';
import {HttpClient} from '@angular/common/http';
declare var module: {
  id: string;
};
@Component({
  moduleId: module.id,
  templateUrl: 'register.component.html',
  styleUrls: ['./register.component.css']
})

export class RegisterComponent implements OnInit {
  public form: FormGroup;
  public loginInvalid: boolean;
  private formSubmitAttempt: boolean;
  private returnUrl: string;
  loading = false;
  model: any = {};

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private authService: AuthService,
    private httpClient: HttpClient) {
  }

  async ngOnInit() {
    this.returnUrl = this.route.snapshot.queryParams.returnUrl || '';

    this.form = this.fb.group({
      username: ['', Validators.required],
      email: ['', Validators.compose([Validators.required, Validators.email])],
      password: ['', Validators.required],
    });

    if (await this.authService.checkAuthenticated()) {
      await this.router.navigate([this.returnUrl]);
    }
  }



  goToLogin() {
    this.authService.logout('login');
  }

   register() {
    this.loginInvalid = false;
    this.formSubmitAttempt = false;
    if (this.form.valid) {
      try {
        const username = this.form.get('username').value;
        const password = this.form.get('password').value;
        const email = this.form.get('email').value;
        const transaction = this.httpClient.post<User>('http://localhost:8080/public/users/register',
          {username, password, email}).subscribe((data: User) => {
          this.authService.currentUser = {...data};
          if (this.authService.currentUser.name !== null && this.authService.currentUser.name.length > 0) {
            this.authService.saveInStorage();
            this.authService.isAuthenticated.next(true);
            this.router.navigate(['/']).then();
          } else {
            throw Error('We cannot handle the ' + transaction + ' status');
          }
        }, () => {this.loginInvalid = true; });
      } catch (err) {
        this.loginInvalid = true;
      }
      debugger;
    } else {
      debugger;
      this.formSubmitAttempt = true;
    }
  }
}
