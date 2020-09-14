import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { AlertService, AuthenticationService } from '../_services/index';
import {FormBuilder, FormGroup, Validators } from '@angular/forms';
import {AuthService} from '../auth.service';
declare var module: {
  id: string;
}
@Component({
    moduleId: module.id,
    templateUrl: 'login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit {
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
    private authenticationService: AuthenticationService,
    private alertService: AlertService
  ) {
  }

  async ngOnInit() {
    this.returnUrl = this.route.snapshot.queryParams.returnUrl || '';

    this.form = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });

    if (await this.authService.checkAuthenticated()) {
      await this.router.navigate([this.returnUrl]);
    }
  }

  async onSubmit() {
    this.loginInvalid = false;
    this.formSubmitAttempt = false;
    if (this.form.valid) {
      try {
        const username = this.form.get('username').value;
        const password = this.form.get('password').value;
        await this.authService.login(username, password);
      } catch (err) {
        this.loginInvalid = true;
      }
    } else {
      this.formSubmitAttempt = true;
    }
  }
    // model: any = {};
    // loading = false;
    // returnUrl: string;
    //
    //
  //   constructor(
  //       private route: ActivatedRoute,
  //       private router: Router,
  //       private authenticationService: AuthenticationService,
  //       private alertService: AlertService) { }
  //
  //   ngOnInit() {
  //       // reset login status
  //       this.authenticationService.logout();
  //
  //       // get return url from route parameters or default to '/'
  //       this.returnUrl = this.route.snapshot.queryParams.returnUrl || '/';
  //   }
  //
    login() {
        this.loading = true;
        this.authenticationService.login(this.model.username, this.model.password)
            .subscribe(
                data => {
                    this.router.navigate([this.returnUrl]);
                },
                error => {
                    this.alertService.error(error);
                    this.loading = false;
                });
    }

  register() {
    this.loading = true;
    this.authenticationService.register(this.model.username, this.model.password)
      .subscribe(
        data => {
          this.router.navigate([this.returnUrl]);
        },
        error => {
          this.alertService.error(error);
          this.loading = false;
        });
  }
}
