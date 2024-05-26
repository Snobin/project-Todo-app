import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  
  signupData = {
    email: '',
    password: ''
  }
  hidePassword = true;
  validationMessage={
    email:'',
    password:''
  };

  constructor(private snack: MatSnackBar, private auth: AuthService, private router: Router) { }


  ngOnInit(): void {
  }

  formSubmit() {
    if (this.signupData.email.trim() == '' || this.signupData.email.trim() == null) {
      this.snack.open("Username is required !", '', {
        duration: 3000,
        verticalPosition: 'top'
      });
      return;
    }
    if (this.signupData.password.trim() == '' || this.signupData.password.trim() == null) {
      this.snack.open("Password is required !", '', {
        duration: 3000,
        verticalPosition: 'top'
      });
      return;
    }

    this.auth.signup(this.signupData).subscribe(
      (data: any) => {
        if(!data.details)
        {
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

  clear() {
 
    this.router.navigate(['/login']);
  }

}
