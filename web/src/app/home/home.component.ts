import {Component, OnInit} from '@angular/core';


import {AuthService} from '../auth.service';
import {Router} from '@angular/router';
import {FormGroup} from '@angular/forms';
import {WheelDTO} from '../dto/WheelDTO';
import {TaskDTO} from '../dto/TaskDTO';
import {TaskComponent} from '../task/task.component';
import {MatDialog, MatDialogConfig} from '@angular/material/dialog';
import {User} from '../dto/user';
import {HttpClient, HttpHeaders} from '@angular/common/http';

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
  wheels: WheelDTO[];
  columnsToDisplay = ['wheelId', 'name', 'make', 'model', 'variant', 'travelledKm', 'actions'];
  columnsOfTasksToDisplay = ['taskId', 'dateCreated', 'dateScheduled', 'travelledKmWhenCompleted', 'taskType',
    'details', 'isCompleted', 'price', 'wheelName', 'actions', 'settings'];
  tasks: Array<TaskDTO>;
  private areAllTaskShown = false;
  options: any;

  constructor(public authService: AuthService,
              private router: Router,
              private dialog: MatDialog,
              private httpClient: HttpClient) {
    this.currentUser = authService.getCurrentUser();
    this.options = {year: 'numeric', month: 'numeric', day: 'numeric'};
  }

  async ngOnInit(): Promise<void> {
    if (this.currentUser == null || this.currentUser.name.length === 0) {

      await this.router.navigate(['login']);
    }
    this.getUserWheels();
    this.showOnlyUpcoming();
  }
  logout() {
    this.authService.logout('login');
  }

  editSelectedWheel(wheel: WheelDTO) {

  }


  deleteWheel(wheel: WheelDTO) {

  }



  editSelected(task: TaskDTO, i: number) {
    const dialogConfig = new MatDialogConfig<TaskDTO>();
    dialogConfig.width = '100vh';
    dialogConfig.height = '50vh';
    dialogConfig.data = task;
    const dialogRef = this.dialog.open(TaskComponent, dialogConfig);
    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`); // Pizza!
    });

  }


  deleteSaved(task: TaskDTO, i: number) {

  }

  getTasksFromBe(url = 'http://localhost:8080/tasks/userId') {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json; charset=utf-8',
        userId: this.currentUser.id.toString()
      })
    };

    const transaction = this.httpClient.get<TaskDTO[]>(url, httpOptions)
      .subscribe((data: TaskDTO[]) => {

        this.tasks = [];
        for (const dto of data) {
          dto.dateCreated = new Date(dto.dateCreated);
          dto.dateScheduled = new Date(dto.dateScheduled);
          this.tasks.push(dto);
        }
      }, error => {
        console.log(error);
        // TODO show error
      });
  }

  getUserWheels() {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json; charset=utf-8',
        userId: this.currentUser.id.toString()
      })
    };

    const transaction = this.httpClient.get<WheelDTO[]>('http://localhost:8080/wheels/userId', httpOptions)
      .subscribe((data: WheelDTO[]) => {

        this.wheels = [];
        for (const dto of data) {
          this.wheels.push(dto);
        }
      }, error => {
        console.log(error);
        // TODO show error
      });
  }

  showAlLTasks() {
    this.getTasksFromBe('http://localhost:8080/tasks/all/userId');
    this.areAllTaskShown = true;
  }

  showOnlyUpcoming() {
    this.getTasksFromBe('http://localhost:8080/tasks/userId');
    this.areAllTaskShown = false;
  }
}
