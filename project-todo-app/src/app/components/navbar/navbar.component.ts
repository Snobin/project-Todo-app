import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { NavigationEnd, Router } from '@angular/router';
import { Subject, Subscription } from 'rxjs';
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


flag:boolean;
  constructor(
    public login: AuthService,
    private router: Router,
    private snack: MatSnackBar
  ) { 
    this.flag = true;

  }

  ngOnInit(): void {
    this.login.myEventEmitter.subscribe((data) => {
      console.log(data);
      this.flag=true;
    });   
  }

  isLogged() {

    this.user = this.login.getUser();
   
  }

  logout() {
    this.flag = false;

    this.login.logout();
    this.user = this.login.getUser();
    this.router.navigate([`login`]);
  }
}



