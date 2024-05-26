import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TodoService {
  private baseUrl = 'http://localhost:8082/api/projects';
  private todoUrl = 'http://localhost:8082/api/todos';
  constructor(private http: HttpClient) { }

  addTodo(todo: any): Observable<any> {
    return this.http.post(`${this.todoUrl}`, todo);
  }

  getTodos(pid: any): Observable<any> {
    return this.http.get(`${this.baseUrl}/` + pid);
  }
  getTodoById(pid: any, tid: any): Observable<any> {
    return this.http.get(`${this.todoUrl}/${pid}/todos/${tid}`);
  }


  markTodoAsComplete(pid:any,todoId: number): Observable<any> {
    return this.http.put(`${this.todoUrl}/${pid}/complete/${todoId}`, {});
  }

  markTodoAsPending(pid:any,todoId: number): Observable<any> {
    return this.http.put(`${this.todoUrl}/${pid}/pending/${todoId}`, {});
  }

  deleteTodo(pid:any,todoId: number): Observable<any> {
    return this.http.delete(`${this.todoUrl}/${pid}/todos/${todoId}`);
  }
  updateTodo(pid: any, tid: any, data: any) {
    console.log(pid);
    
    return this.http.put(`${this.todoUrl}/${pid}/todos/${tid}`, data);
  }
}
