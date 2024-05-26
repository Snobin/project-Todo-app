import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProjectService } from 'src/app/services/project.service';
import { TodoService } from 'src/app/services/todo.service';
import Swal from 'sweetalert2';

interface Todo {
  id: number;
  description: string;
  status: string;
  createdDate: string;
  updatedDate: string | null;
}

@Component({
  selector: 'app-todo-list',
  templateUrl: './todo-list.component.html',
  styleUrls: ['./todo-list.component.css']
})
export class TodoListComponent implements OnInit {

  todos: Todo[] = [];
  pendingTodos: Todo[] = [];
  completedTodos: Todo[] = [];
  projectTitle: string = '';
  completedTodosCount: number = 0;
  totalTodosCount: number = 0;
  pid: any;
  projectdata: any;

  constructor(private todoService: TodoService, private router: Router, private route: ActivatedRoute, private project: ProjectService) { }

  ngOnInit(): void {
    this.pid = this.route.snapshot.params.pid;

    this.fetchTodos();
  }

  addTodo() {
    this.router.navigate(['/todo/' + this.pid]);
  }
  fetchTodos(): void {
    this.todoService.getTodos(this.pid).subscribe(
      (data: any) => {
        this.todos = data.response.todos;
        this.projectTitle = data.response.title;
        this.totalTodosCount = this.todos.length;
        this.completedTodos = this.todos.filter(todo => todo.status === 'completed');
        this.pendingTodos = this.todos.filter(todo => todo.status === 'pending');
        this.completedTodosCount = this.completedTodos.length;
        console.log(this.todos);
      },
      (error) => {
        Swal.fire('Error !!', 'Error while fetching todos', 'error');
      }
    );
  }
  export() {
    this.project.exportGist(this.pid).subscribe((data: any) => {
      Swal.fire('Success', 'Exported project summary as gist.', 'success');
    }, (error) => {
      Swal.fire('Error!', 'Error in exporting gist. Please try again later.', 'error');
    }
    )
  }

  markAsComplete(todoId: number): void {
    this.todoService.markTodoAsComplete(this.pid, todoId).subscribe(
      () => {
        Swal.fire('Success', 'Todo marked as complete', 'success');
        this.fetchTodos();
      },
      (error) => {
        Swal.fire('Error !!', 'error while marking todo as complete', 'error');
      }
    );
  }
  markAsPending(todoId: number): void {
    this.todoService.markTodoAsPending(this.pid, todoId).subscribe(
      () => {
        Swal.fire('Success', 'Todo marked as pending', 'success');
        this.fetchTodos();
      },
      (error) => {
        Swal.fire('Error !!', 'error while marking todo as pending', 'error');
      }
    );
  }

  editTodo(tid: any): void {
    this.router.navigate(['update-todo', this.pid, tid]);
  }


  deleteTodo(todoId: number): void {
    Swal.fire({
      title: 'Are you sure?',
      text: 'You will not be able to recover this todo!',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Yes, delete it!',
      cancelButtonText: 'No, keep it'
    }).then((result) => {
      if (result.isConfirmed) {
        this.todoService.deleteTodo(this.pid, todoId).subscribe(
          () => {
            Swal.fire('Deleted!', 'Todo has been deleted.', 'success');
            this.fetchTodos();
          },
          (error) => {
            Swal.fire('Error !!', 'error while deleting todo', 'error');
          }
        );
      }
    });
  }
}
