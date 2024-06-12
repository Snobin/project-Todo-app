import { Component, OnInit } from '@angular/core';
import { ProjectService } from 'src/app/services/project.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
export(id: any) {
  this.project.exportGist(id).subscribe((data: any) => {
    window.location.href="";
    Swal.fire('Success', 'Exported project summary as gist.', 'success');
  }, (error) => {
    Swal.fire('Error!', 'Error in exporting gist. Please try again later.', 'error');
  }
  )}

  constructor(private project: ProjectService) { }
  projectdata = [
  ]
  ngOnInit(): void {
    this.getProjects();
  }
  getProjects() {
    this.project.getProjects().subscribe((data: any) => {
      this.projectdata = data.response.Projects;
      console.log(this.projectdata);     
    }, (error) => {
      // Swal.fire('Error!', 'Error in data fetching. Please try again later.', 'error');
    }
    )
  }
  deleteProject(qId) {
    Swal.fire({
      icon: 'info',
      title: 'Are you sure ?',
      confirmButtonText: 'Delete',
      showCancelButton: true,
    }).then((result) => {
       if(result.isConfirmed){
         this.project.deleteProject(qId).subscribe(
           (data: any) => {
             Swal.fire('Success', 'Quiz deleted', 'success');
             this.getProjects();
           },
           (error) => {
             Swal.fire('Error!', 'Error in deleting quiz. Please try again later.', 'error');
           }
         );
       }
    });
  }
}