export class User {
  id: number;
  name: string;
  email: string;
  password: string;
  isSubscribedForMail: boolean;
  isSubscribedForNotifications: boolean;
  daysBetweenNotifications: number;
  maxDaysBeforeUpcoming: number;


  constructor(id: number, name: string, email: string, password: string, isSubscribedForMail: boolean,
              isSubscribedForNotifications: boolean, daysBetweenNotifications: number, maxDaysBeforeUpcoming: number) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.password = password;
    this.isSubscribedForMail = isSubscribedForMail;
    this.isSubscribedForNotifications = isSubscribedForNotifications;
    this.daysBetweenNotifications = daysBetweenNotifications;
    this.maxDaysBeforeUpcoming = maxDaysBeforeUpcoming;
  }
}
