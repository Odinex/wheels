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

  constructor(private router: Router, private httpClient: HttpClient) {
  }


  private USER_NAME = Object.freeze('currentUserName');

  private EMAIL = Object.freeze('currentEmail');

  private USER_ID = Object.freeze('currentUserId');
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
    const item = localStorage.getItem('currentUserName');
    if (item != null && item.length > 0) {
      this.currentUser = new User(+localStorage.getItem('currentUserId'), item,
        localStorage.getItem('currentEmail'), null);
      return true;
    }
    return false;
  }

  getCurrentUser() {

    return this.currentUser;
  }

}
