import {WheelDTO} from './WheelDTO';

export class TaskDTO {
  id: number;
  dateCreated: Date;
  dateScheduled: Date;
  taskType: string;
  details: string;
  travelledKmWhenCompleted: number;
  price: number;
  isCompleted: boolean;
  wheel: WheelDTO;

  constructor(id: number, dateCreated: Date, dateScheduled: Date, taskType: string,
              details: string, travelledKmWhenCompleted: number, price: number, wheel: WheelDTO, isCompleted: boolean) {
    this.id = id;
    this.dateCreated = dateCreated;
    this.dateScheduled = dateScheduled;
    this.taskType = taskType;
    this.details = details;
    this.travelledKmWhenCompleted = travelledKmWhenCompleted;
    this.price = price;
    this.wheel = wheel;
    this.isCompleted = isCompleted;
  }
}
