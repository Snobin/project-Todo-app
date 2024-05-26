import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { ProjectService } from 'src/app/services/project.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-update-project',
  templateUrl: './update-project.component.html',
  styleUrls: ['./update-project.component.css']
})
export class UpdateProjectComponent implements OnInit {

  categories = []

  Data = {
    title: '',
  }
  validationMessage = {
    title: '',
  };
  pid: any;
  constructor(private project: ProjectService, private snack: MatSnackBar, private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.pid = this.route.snapshot.params.pid;    
    this.getProjectById();
  }
  getProjectById(){
    this.project.getProjectById(this.pid).subscribe((data:any)=>{
      this.Data.title=data.response.title;
    })
  }
  update() {
    if (this.Data.title.trim() == '' || this.Data.title == null) {
      this.snack.open('Title Required !!', '', {
        duration: 3000,
      });
      return;
    }

    this.project.UpdateProject(this.Data,this.pid).subscribe(
      (data: any) => {
        if (!data.details) {
          Swal.fire('Success', 'Project is Updated', 'success');
          this.Data = {
            title: '',
          }
          this.router.navigate(['/home'])
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
