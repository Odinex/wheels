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
  private currentUser: User = null;

  constructor(private router: Router, private httpClient: HttpClient) {
  }


  private USER_NAME = Object.freeze('currentUserName');

  private EMAIL = Object.freeze('currentEmail');

  private USER_ID = Object.freeze('currentUserId');

  login(username: string, password: string) {
    // const headers = new HttpHeaders()
    //   .set('Content-Type', 'application/json');
    const transaction = this.httpClient.post<User>('http://localhost:8080/public/users/login',
      {username, password}).subscribe((data: User) => {
      debugger;
      this.currentUser = {...data};
      if (this.currentUser.name !== null && this.currentUser.name.length > 0) {
        this.saveInStorage();
        this.isAuthenticated.next(true);
        debugger;
        this.router.navigate(['/']).then();
      } else {
        throw Error('We cannot handle the ' + transaction + ' status');
      }
    });
    console.log(transaction);

  }

  private saveInStorage() {
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
    debugger;
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

  register(username: any, password: any, email: string) {
    debugger;
    // TODO add email
    const transaction = this.httpClient.post<User>('http://localhost:8080/public/users/register',
      {username, password, email}).subscribe((data: User) => {
      debugger;
      this.currentUser = {...data};
      if (this.currentUser.name !== null && this.currentUser.name.length > 0) {
        this.saveInStorage();
        this.isAuthenticated.next(true);
      } else {
        throw Error('We cannot handle the ' + transaction + ' status');
      }
    });

  }
}
