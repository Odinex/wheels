import { Injectable } from '@angular/core';
import {BehaviorSubject} from 'rxjs';
import {Router} from '@angular/router';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {User} from './_models';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  public isAuthenticated = new BehaviorSubject<boolean>(false);
  private currentUser: User = null;

  constructor(private router: Router, private httpClient: HttpClient) {
  }



  async login(username: string, password: string) {
    // const headers = new HttpHeaders()
    //   .set('Content-Type', 'application/json');
    debugger;
    const transaction = this.httpClient.post<User>('http://localhost:8080/public/users/register',
      {username: username, password: password}).subscribe((data: User) => {
        debugger;
        this.currentUser = {...data};
    });
    debugger;
    console.log(transaction);
    if (this.currentUser !== null && this.currentUser.name !== null && this.currentUser.name.length > 0) {
      localStorage.setItem('currentUserName', this.currentUser.name);
      localStorage.setItem('currentUserId', String(this.currentUser.id));
      this.isAuthenticated.next(true);
    } else {
      throw Error('We cannot handle the ' + transaction + ' status');
    }
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
    debugger;
    if (item != null && item.length > 0) {
      this.currentUser = new User();
      this.currentUser.name = item;
      this.currentUser.id = +localStorage.getItem('currentUserId');
      return true;
    }
    return false;
  }

  getCurrentUser() {

    return this.currentUser;
  }
}
