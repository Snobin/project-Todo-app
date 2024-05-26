import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { TodoService } from 'src/app/services/todo.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-todo',
  templateUrl: './todo.component.html',
  styleUrls: ['./todo.component.css']
})
export class TodoComponent implements OnInit {

  todoData: any = {
    description: '',
    status: '',
    projectId:''
  };
  validationMessage: any = {};
  pid: any;

  constructor(private todoService: TodoService, private router: Router,private route:ActivatedRoute,
  ) { }
  ngOnInit(): void {
    this.pid=this.route.snapshot.params.pid;
    this.todoData.projectId=this.pid;
  }
  viewtodo(){
    this.router.navigate(['/todo-list'+this.pid]);
  }

  addTodo() {
    this.todoService.addTodo(this.todoData).subscribe(
      (data: any) => {
        if (!data.details) {
          Swal.fire('Success', 'Todo is added', 'success');
          this.todoData = {
            description: '',
            status: '',
            projectId:''
          };
          this.router.navigate(['/todo-list/'+this.pid]);
        } else {
          if (data.details) {
            data.details.forEach(element => {
              var key = Object.keys(element)[0];
              this.validationMessage[key] = element[key];
            });
          }
        }
      },
      (error) => {
        Swal.fire('Error !!', 'error while adding todo', 'error');
      }
    );
  }
  
}