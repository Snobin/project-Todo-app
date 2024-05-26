import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProjectService {
  constructor(private http: HttpClient) { }
  baseUrl = 'http://localhost:8082/api/projects';

  getProjects(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }
  exportGist(id: any): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}/export-gist`);
  }

  addproject(data: any) {
    return this.http.post(`${this.baseUrl}`, data);
  }
  deleteProject(pid: any) {
    return this.http.delete(`${this.baseUrl}/${pid}`);
  }
  UpdateProject(data: any,id:any) {
    return this.http.put(`${this.baseUrl}/${id}`, data);
  }
 
  getProjectById(pid:any){
    return this.http.get(`${this.baseUrl}/getProject/${pid}`);
  }


}
