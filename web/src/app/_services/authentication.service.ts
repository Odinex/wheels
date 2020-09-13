
import {map} from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import {Encoding} from 'tslint/lib/utils';
import {JsonObject} from '@angular/compiler-cli/ngcc/src/packages/entry_point';


@Injectable()
export class AuthenticationService {

  constructor(private http: HttpClient) { }

    login(username: string, password: string) {
      const headers = new HttpHeaders()
        .set('Content-Type', 'application/json');
      let body = { username: username, password: password };
      return this.http.post<any>('http://localhost:8080/public/users/login', body, {headers}).pipe(
            map(user => {
                // login successful if there's a jwt token in the response
                if (user && user.id) {
                    // store user details and jwt token in local storage to keep user logged in between page refreshes
                    localStorage.setItem('currentUser', JSON.stringify(user));
                }

                return user;
            }));
    }

    logout() {
        // remove user from local storage to log user out
        localStorage.removeItem('currentUser');
    }

  register(username: any, password: any) {

    const headers = new HttpHeaders()
      .set('Content-Type', 'application/json');
    return this.http.post<any>('http://localhost:8080/public/users/register', { username: username, password: password }, {headers}).pipe(
      map(user => {
        // login successful if there's a jwt token in the response
        if (user && user.id) {
          // store user details and jwt token in local storage to keep user logged in between page refreshes
          localStorage.setItem('currentUser', JSON.stringify(user));
        }
        console.log(JSON.stringify(user));
        console.log(user);
        return user;
      }));
  }
}
