import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';
import { SignupComponent } from './pages/signup/signup.component';
import { HomeComponent } from './pages/home/home.component';
import { UpdateProjectComponent } from './pages/update-project/update-project.component';
import { AddProjectComponent } from './pages/add-project/add-project.component';
import { TodoComponent } from './pages/todo/todo.component';
import { TodoListComponent } from './pages/todo-list/todo-list.component';
import { UpdateTodoComponent } from './pages/update-todo/update-todo.component';


const routes: Routes = [
  { path: '', component: LoginComponent },
  { path: 'login', component: LoginComponent },
  { path: 'signup', component: SignupComponent },
  { path: 'home', component: HomeComponent },
  {
    path: 'update-project/:pid',
    component: UpdateProjectComponent
  }, {
    path: 'add-project',
    component: AddProjectComponent
  },{
    path:'todo/:pid',
    component:TodoComponent
  },
  {
    path:'todo-list/:pid',
    component:TodoListComponent
  },{
    path: 'update-todo/:pid/:tid',
    component: UpdateTodoComponent
}
,{path:'update-project/:pid',
  component:UpdateProjectComponent
}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
