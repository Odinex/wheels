import {Component, Provider} from '@angular/core';
import {Type} from '@angular/compiler';
import {AuthService} from './auth.service';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Wheels Diary';
  isAuthenticated = false;

  constructor(public authService: AuthService) {
    this.authService.isAuthenticated.subscribe(
      (isAuthenticated: boolean)  => this.isAuthenticated = isAuthenticated
    );
  }

  async ngOnInit() {
    this.isAuthenticated = await this.authService.checkAuthenticated();
  }

  logout() {
    this.authService.logout('/');
  }
}
declare module '@angular/core' {

  interface ModuleWithProviders<T = any> {
    ngModule: Type<T>;
    providers?: Provider[];
  }
}
