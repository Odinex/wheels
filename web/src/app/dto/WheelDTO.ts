import {User} from './user';


export class WheelDTO {
  id: number;
  make: string;
  model: string;
  name: string;
  variant: string;
  travelledKm: number;
  user: User;

  constructor(id: number, make: string, model: string, name: string, variant: string, travelledKm: number, user: User) {
    this.id = id;
    this.make = make;
    this.model = model;
    this.name = name;
    this.variant = variant;
    this.travelledKm = travelledKm;
    this.user = user;
  }
}
