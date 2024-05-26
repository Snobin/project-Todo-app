import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { NavigationEnd, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  isLoggedIn = false;
  user = null;

  status: boolean = false;

  showTimer: boolean = false;
  categories: any;
  showdata: boolean;
  logoutdata: boolean;
  private storageKey = 'timerState';
  storedState;
flag:boolean;
  constructor(
    public login: AuthService,
    private router: Router,
    private snack: MatSnackBar
  ) { 
    this.isLoggedIn = true;

  }

  ngOnInit(): void {
    this.flag = true;
   
  }

  isLogged() {

    this.user = this.login.getUser();
    this.login.loginStatusSubject.asObservable().subscribe((data) => {
      this.isLoggedIn = this.login.isloggedin();
      this.user = this.login.getUser();
    });
  }


  logout() {
    this.login.logout();
    this.flag = false;

    this.isLoggedIn = this.login.isloggedin();
    this.isLoggedIn=false;
    this.user = this.login.getUser();
    this.router.navigate([`login`]);
  }

  logouthere(url: string): boolean {
    // Add logic to check if the timer should be visible for specific routes/components
    return (
      url.includes('/final') ||
      url.includes('/login')
      // Add more conditions as needed for other components
    );
  }


  submit() {
    localStorage.removeItem(this.storageKey);
    localStorage.removeItem("minutes");
    this.router.navigate(['./final']);
  }
}
