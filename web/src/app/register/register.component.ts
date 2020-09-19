import {Component, OnInit} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';

import {FormBuilder, FormGroup, Validators} from '@angular/forms';
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
  checkedEmail = true;
  checkedPush = true;

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

      emailInterval: ['', Validators.required],

      maxDays: ['', Validators.required],
    });

    if (await this.authService.checkAuthenticated()) {
      await this.router.navigate([this.returnUrl]);
    }

    this.form.controls.maxDays.setValue(7);

    this.form.controls.emailInterval.setValue(1);
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
        const isSubscribedForMail = this.checkedEmail;
        const isSubscribedForNotifications = this.checkedPush;
        const daysBetweenNotifications = this.form.get('emailInterval').value;
        const maxDaysBeforeUpcoming = this.form.get('maxDays').value;
        const transaction = this.httpClient.post<User>('http://localhost:8080/public/users/register',
          {
            username, password, email, isSubscribedForMail, isSubscribedForNotifications,
            daysBetweenNotifications, maxDaysBeforeUpcoming
          }).subscribe((data: User) => {
          this.authService.currentUser = {...data};
          if (this.authService.currentUser.name !== null && this.authService.currentUser.name.length > 0) {
            this.authService.saveInStorage();
            this.authService.isAuthenticated.next(true);
            this.router.navigate(['/']).then();
          } else {
            throw Error('We cannot handle the ' + transaction + ' status');
          }
        }, () => {
          this.loginInvalid = true;
        });
      } catch (err) {
        this.loginInvalid = true;
      }
      debugger;
    } else {
      debugger;
      this.formSubmitAttempt = true;
    }
  }

  hideMaxDays() {

    const wasEmailIntervalHidden = !(this.checkedPush || this.checkedEmail);
    this.checkedPush = !this.checkedPush;
    const isMaxDaysHidden = !(this.checkedPush || this.checkedEmail);
    if (isMaxDaysHidden) {
      this.form.controls.maxDays.reset();
      this.form.controls.maxDays.clearValidators();
      this.form.controls.maxDays.updateValueAndValidity();
    } else if (wasEmailIntervalHidden !== isMaxDaysHidden) {
      this.form.controls.maxDays.clearValidators();
      this.form.controls.maxDays.setValidators(Validators.required);
      this.form.controls.maxDays.updateValueAndValidity();
      this.form.controls.maxDays.setValue(7);
    }
  }

  hideEmailInterval() {
    const wasEmailIntervalHidden = !(this.checkedPush || this.checkedEmail);
    this.checkedEmail = !this.checkedEmail;
    const isMaxDaysHidden = !(this.checkedPush || this.checkedEmail);

    if (isMaxDaysHidden) {
      this.form.controls.maxDays.reset();
      this.form.controls.maxDays.clearValidators();
      this.form.controls.maxDays.updateValueAndValidity();
    } else if (wasEmailIntervalHidden !== isMaxDaysHidden) {
      this.form.controls.maxDays.clearValidators();
      this.form.controls.maxDays.setValidators(Validators.required);
      this.form.controls.maxDays.updateValueAndValidity();
      this.form.controls.maxDays.setValue(7);
    }
    if (this.checkedEmail) {
      this.form.controls.emailInterval.clearValidators();
      this.form.controls.emailInterval.setValidators(Validators.required);
      this.form.controls.emailInterval.updateValueAndValidity();
      this.form.controls.emailInterval.setValue(1);
    } else {
      this.form.controls.emailInterval.reset();
      this.form.controls.emailInterval.clearValidators();
      this.form.controls.emailInterval.updateValueAndValidity();
    }
  }
}
