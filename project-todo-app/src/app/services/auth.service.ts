import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {


 
  public loginStatusSubject = new Subject<boolean>();

  baseUrl = 'http://localhost:8082/auth';

  constructor(private http: HttpClient) { }
  flag=false
  public getCurrentUser() {
    return this.http.get(`${this.baseUrl}/current-user`);
  }

  getData() {
    return this.http.get(`${this.baseUrl}/userlist`);
  }

  signup(signupData:any) {
    return this.http.post(`${this.baseUrl}/signup`, signupData);
  }

  //generate token
  public generateToken(loginData: any) {
    return this.http.post(`${this.baseUrl}/login`, loginData);
  }

  //login user: set token in localStorage
  public loginUser(token) {
    localStorage.setItem('token', token);
    this.flag=true;
    return true;
  }

  //isLogin: user is logged or not
  public isloggedin() {
    if(localStorage.getItem('user')){
      return true;
    }
   
  }

  //logout: remove token from localStorage
  public logout() {
    localStorage.removeItem("user");
    localStorage.removeItem("token");
    return true;
  }

  //get token
  public getToken() {
    return localStorage.getItem("token");
  }

  //set userDetails
  public setUser(user:string) {
    localStorage.setItem("user",user );
  }

  public navigationlogin() {
    return true;
  }

  //get userDetails
  public getUser() {
    let userStr = localStorage.getItem("user");
    // if (userStr != null) {
    //   return JSON.parse(userStr);
    // } else {
    //   this.logout();
    //   return null;
    // }
  }

}
