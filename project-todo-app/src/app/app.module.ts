import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { LoginComponent } from './pages/login/login.component';
import { SignupComponent } from './pages/signup/signup.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatButtonModule} from '@angular/material/button';
import { HomeComponent } from './pages/home/home.component';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatSnackBarModule } from '@angular/material/snack-bar';

import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatListModule } from '@angular/material/list';
import { MatCardModule } from '@angular/material/card';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { MatSelectModule } from '@angular/material/select';
import { MatRadioModule } from '@angular/material/radio';
import { authInterceptorProviders } from './shared/auth.interceptor';
import { TodoComponent } from './pages/todo/todo.component';
import { AddProjectComponent } from './pages/add-project/add-project.component';
import { UpdateProjectComponent } from './pages/update-project/update-project.component';
import { TodoListComponent } from './pages/todo-list/todo-list.component';
import {MatCheckboxModule} from '@angular/material/checkbox';
import { UpdateTodoComponent } from './pages/update-todo/update-todo.component';


@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    LoginComponent,
    SignupComponent,
    HomeComponent,
    TodoComponent,
    AddProjectComponent,
    UpdateProjectComponent,
    TodoListComponent,
    UpdateTodoComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    FormsModule,
    MatButtonModule, 
    MatRadioModule, 
    MatSelectModule, 
    MatCardModule,
     MatToolbarModule, 
     MatIconModule, 
     MatListModule, 
     MatInputModule,
      MatFormFieldModule, 
      MatSnackBarModule, 
      MatSlideToggleModule,
      MatCheckboxModule

  ],
  providers: [authInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
