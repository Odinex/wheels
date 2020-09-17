import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {TaskDTO} from '../dto/TaskDTO';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.css']
})
export class TaskComponent  {
  form: FormGroup;

  constructor(@Inject(MAT_DIALOG_DATA) public task: TaskDTO,
              private fb: FormBuilder,
              public dialogRef: MatDialogRef<TaskComponent>) {
    this.form = this.fb.group({
      dateScheduled: ['', Validators.compose([Validators.required])],
      taskType: ['', Validators.compose([Validators.required])],
      details: ['', Validators.compose([Validators.required])],
    });
    this.form.controls.dateScheduled.setValue(task.dateScheduled);
    this.form.controls.taskType.setValue(task.taskType);
    this.form.controls.details.setValue(task.details);
  }


  closeDialog() {
    this.dialogRef.close(this.task);
  }

  saveTask() {
      this.task.dateScheduled = this.form.controls.dateScheduled.value;
      this.task.taskType = this.form.controls.taskType.value;
      this.task.details = this.form.controls.details.value;
  }
}
