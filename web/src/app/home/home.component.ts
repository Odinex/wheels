import {Component, OnInit} from '@angular/core';

import {User} from '../_models/index';
import {AuthService} from '../auth.service';
import {Router} from '@angular/router';
import {FormGroup} from '@angular/forms';
import {MatTableDataSource} from '@angular/material/table';
import {Wheel} from '../dto/Wheel';

declare var module: {
  id: string;
};

@Component({
  moduleId: module.id,
  templateUrl: 'home.component.html',
  styleUrls: ['./home.component.css']
})

export class HomeComponent implements OnInit {
  currentUser: User;
  form: FormGroup;
  wheels: Wheel[];
  columnsToDisplay = ['wheelId', 'name', 'make', 'model', 'variant'];
  constructor(public authService: AuthService,
              private router: Router) {
    this.currentUser = authService.getCurrentUser();

  }

  async ngOnInit(): Promise<void> {
    if (this.currentUser == null || this.currentUser.name.length === 0) {

      await this.router.navigate(['login']);
    }
    this.wheels = [new Wheel(1, 'test1', 'model1', 'name1', 'variant1', this.currentUser),
      new Wheel(2, 'test2', 'model2', 'name2', 'variant2', this.currentUser),
      new Wheel(3, 'test3', 'model3', 'name3', 'variant3', this.currentUser)];
  }

}
