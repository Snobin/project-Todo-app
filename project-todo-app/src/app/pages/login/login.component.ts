import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginData = {
    email: '',
    password: ''
  }
  hidePassword = true;
  validationMessage={
    email:'',
    password:''
  };

  constructor(private snack: MatSnackBar, private login: AuthService, private router: Router) { }


  ngOnInit(): void {
  }

  formSubmit() {
    if (this.loginData.email.trim() == '' || this.loginData.email.trim() == null) {
      this.snack.open("Username is required !", '', {
        duration: 3000,
        verticalPosition: 'top'
      });
      return;
    }
    if (this.loginData.password.trim() == '' || this.loginData.password.trim() == null) {
      this.snack.open("Password is required !", '', {
        duration: 3000,
        verticalPosition: 'top'
      });
      return;
    }

    this.login.generateToken(this.loginData).subscribe(
      (data: any) => {
        if(!data.details)
        {
          this.login.loginUser(data.token);
          this.login.navigationlogin();
          this.router.navigate(['/home']);
        }else{
          data.details.forEach((element) => {
            var key = Object.keys(element)[0];
            this.validationMessage[key] = element[key];
        }
          );
      }
      },
      (error) => {
        console.log(error);
        this.snack.open("Invalid Details....Try again", "", {
          duration: 2000,
        });
      }
    )
  }

  togglePasswordVisibility() {
    this.hidePassword = !this.hidePassword;
  }

  signup() {
    this.router.navigate(['/signup']);
  }
}
