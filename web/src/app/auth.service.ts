import {Injectable} from '@angular/core';
import {BehaviorSubject} from 'rxjs';
import {Router} from '@angular/router';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {User} from './dto/user';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  public isAuthenticated = new BehaviorSubject<boolean>(false);
  currentUser: User = null;

  constructor(private router: Router) {
  }


  private USER_NAME = Object.freeze('currentUserName');

  private EMAIL = Object.freeze('currentEmail');

  private USER_ID = Object.freeze('currentUserId');
  private IS_MAIL = Object.freeze('isSubscribedForMail');

  private IS_PUSH_NOT = Object.freeze('isSubscribedForNotifications');

  private EMAIL_INTERVAL = Object.freeze('daysBetweenNotifications');

  private MAX_DAYS = Object.freeze('maxDaysBeforeUpcoming');
  private isSuccessful = true;

  login(username: string, password: string) {
    // const headers = new HttpHeaders()
    //   .set('Content-Type', 'application/json');

    return this.isSuccessful;
  }


  saveInStorage() {
    localStorage.setItem(this.USER_NAME, this.currentUser.name);
    localStorage.setItem(this.EMAIL, this.currentUser.name);
    localStorage.setItem(this.USER_ID, String(this.currentUser.id));
    localStorage.setItem(this.IS_MAIL, String(this.currentUser.isSubscribedForMail));
    localStorage.setItem(this.IS_PUSH_NOT, String(this.currentUser.isSubscribedForNotifications));
    localStorage.setItem(this.EMAIL_INTERVAL, String(this.currentUser.daysBetweenNotifications));
    localStorage.setItem(this.MAX_DAYS, String(this.currentUser.maxDaysBeforeUpcoming));
  }

  async logout(redirect: string) {
    try {
      localStorage.clear();
      this.isAuthenticated.next(false);
      this.router.navigate([redirect]);
    } catch (err) {
      console.error(err);
    }
  }

  checkAuthenticated() {
    const item = localStorage.getItem(this.USER_NAME);
    if (item != null && item.length > 0) {

      const isSubForMail = localStorage.getItem(this.IS_MAIL) === 'true';
      const isSubForPushNot = localStorage.getItem(this.IS_PUSH_NOT) === 'true';
      this.currentUser = new User(+localStorage.getItem(this.USER_ID), item,
        localStorage.getItem(this.EMAIL), null, isSubForMail, isSubForPushNot,
        +localStorage.getItem(this.EMAIL_INTERVAL), +localStorage.getItem(this.MAX_DAYS));
      return true;
    }
    return false;
  }

  getCurrentUser() {

    return this.currentUser;
  }

}
