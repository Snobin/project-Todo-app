import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router, ActivatedRoute } from '@angular/router';
import { TodoService } from 'src/app/services/todo.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-update-todo',
  templateUrl: './update-todo.component.html',
  styleUrls: ['./update-todo.component.css']
})
export class UpdateTodoComponent implements OnInit {
  todoData: any = {
    description: '',
    status: '',
    projectId:''
  };
  validationMessage: any = {};
  pid: any;
  tid:any;

  constructor(private todoService: TodoService, private router: Router,private route:ActivatedRoute,private snack:MatSnackBar
  ) { }
  ngOnInit(): void {
    this.pid=this.route.snapshot.params.pid;
    this.tid=this.route.snapshot.params.tid;

    this.todoData.projectId=this.pid;
    this.getTodoById();
  }
  viewtodo(){
    this.router.navigate(['/todo-list'+this.pid]);
  }

  getTodoById(){
    this.todoService.getTodoById(this.pid,this.tid).subscribe((data:any)=>{
      this.todoData.description=data.response.description;
    })
  }
  update() {
    if (this.todoData.description.trim() == '' || this.todoData.description == null) {
      this.snack.open('Description Required !!', '', {
        duration: 3000,
      });
      return;
    }

    this.todoService.updateTodo(this.pid,this.tid,this.todoData).subscribe(
      (data: any) => {
        if (!data.details) {
          Swal.fire('Success', 'Project is Updated', 'success');
          this.todoData = {
            title: '',
          }
          this.router.navigate(['todo-list/',this.pid])
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
        Swal.fire('Error !!', 'error while updating quiz', 'error');
      }
    )
  }
 
  
}