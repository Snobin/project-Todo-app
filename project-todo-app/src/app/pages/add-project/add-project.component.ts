import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { ProjectService } from 'src/app/services/project.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-add-project',
  templateUrl: './add-project.component.html',
  styleUrls: ['./add-project.component.css']
})
export class AddProjectComponent implements OnInit {

  categories = []

  Data = {
    title: '',
  }
  validationMessage={
    title:'',
  } ;

  constructor(private project: ProjectService, private snack: MatSnackBar,private router:Router) { }

  ngOnInit(): void {
   
  }
  addProject() {
    if (this.Data.title.trim() == '' || this.Data.title == null) {
      this.snack.open('Title Required !!', '', {
        duration: 3000,
      });
      return;
    }

    this.project.addproject(this.Data).subscribe(
      (data: any) => {
        if(!data.details){ 
           Swal.fire('Success', 'Project is added', 'success');
        this.Data = {
          title: '',          
        }
        const projectId=data.pid;
        this.router.navigate(['/todo/'+projectId]);
      }else{
          if(data.details){
            data.details.forEach(element => {
              var key = Object.keys(element)[0];
              this.validationMessage[key] = element[key];
            });
          }
        }
      
      },
      (error) => {
        Swal.fire('Error !!', 'error while adding quiz', 'error');
      }
    )
  }
}
