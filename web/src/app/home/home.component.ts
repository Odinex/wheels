import {Component, OnInit} from '@angular/core';


import {AuthService} from '../auth.service';
import {Router} from '@angular/router';
import {FormGroup} from '@angular/forms';
import {WheelDTO} from '../dto/WheelDTO';
import {TaskDTO} from '../dto/TaskDTO';
import {TaskComponent} from '../task/task.component';
import {MatDialog, MatDialogConfig} from '@angular/material/dialog';
import {User} from '../dto/user';

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
    'details', 'isCompleted', 'price', 'wheelName', 'actions'];
  tasks: Array<TaskDTO>;
  private haveOpenLink: false;

  constructor(public authService: AuthService,
              private router: Router,
              private dialog: MatDialog) {
    this.currentUser = authService.getCurrentUser();

  }

  async ngOnInit(): Promise<void> {
    if (this.currentUser == null || this.currentUser.name.length === 0) {

      await this.router.navigate(['login']);
    }
    this.wheels = [new WheelDTO(1, 'test1', 'model1', 'name1', 'variant1', 123, this.currentUser),
      new WheelDTO(2, 'test2', 'model2', 'name2', 'variant2', 123, this.currentUser),
      new WheelDTO(3, 'test3', 'model3', 'name3', 'variant3', 123, this.currentUser)];
    this.tasks = [];
    let i = 1;

    const now = new Date();
    for (const wheel of this.wheels) {
      this.tasks.push(new TaskDTO(i++, now, new Date(now.getTime() + (i * 84000000)),
        'Застраховка', 'details' + i, 1, 1,
        wheel, false));
      this.tasks.push(new TaskDTO(i++, now, new Date(now.getTime() + (i * 84000000)),
        'TaskTypeEnum.INSURANCE', 'details' + i, 1, 1,
        wheel, false));
      this.tasks.push(new TaskDTO(i++, now, new Date(now.getTime() + (i * 84000000)),
        'Странен Тип', 'detaidddddddddddddddddddddddddddddddddddddddddddls' + i, 1, 1,
        wheel, false));
    }
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
}
