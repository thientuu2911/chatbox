import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroupDirective, NgForm, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { select, Store } from '@ngrx/store';
import { Observable } from 'rxjs';
import { AppState } from '../store/app.state';
import { updateUsers } from '../store/users/user.actions';
import {ErrorStateMatcher} from '@angular/material/core';
import { AnimationOptions } from 'ngx-lottie';
import { HttpClient } from '@angular/common/http';
import { Users } from '../model/users';
import { MatDialog } from '@angular/material/dialog';
import { ConfirmationDialog } from '../confirmation-dialog/confirmation-dialog.component';
import { MessagingService } from '../services/messaging.service';
import { UserState } from '../store/users/user.state';

export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {
  nameFormControl = new FormControl('', [Validators.required]);

  matcher = new MyErrorStateMatcher();
  isLoading = false;
  isSaved = false;
  username: string = "";
  options: AnimationOptions = {};

  constructor(private http: HttpClient, private store: Store<AppState>, private route: ActivatedRoute, private router: Router, private dialog: MatDialog,
    private messageingService: MessagingService) {
  }
  ngOnInit(): void {
  }

  openDialog() {
    const dialogRef = this.dialog.open(ConfirmationDialog,{
      data:{
        message: 'Could you please confirm that you want to get notifications from us?',
        buttonText: {
          ok: 'Yes',
          cancel: 'Not now'
        }
      }
    });

    dialogRef.afterClosed().subscribe((confirmed: boolean) => {
      if (confirmed) {
        this.messageingService.requestPermission();
      }
      this.router.navigate(['/chatbox']);
    });
  }

  saveData() {
    if(!this.username)
      return;
    this.isLoading = true;
    this.options= {
      ...this.options,
      path: '../assets/loading.json',
      autoplay: true,
      loop: true
    }
    console.log({name: this.username, token: this.messageingService.token});
    this.http.post<Users>('http://localhost:8080/chatbox/user/login', {name: this.username, token: this.messageingService.token}).subscribe({
        next: data => {
          this.store.dispatch(updateUsers({user: data, currentUserId: data.id}));  
          this.isLoading = false;
          this.isSaved = true;
          this.options= {
            ...this.options, // In case you have other properties that you want to copy
            path: '../assets/saved.json',
            loop: false
          };
          if(Notification.permission == 'default')
            this.openDialog()
          else
            setTimeout(() => {
              this.router.navigate(['/chatbox']);
            }, 2000);
        },
        error: error => {
          this.isSaved = false;
          console.error('There was an error!', error);
        },
      })
  }

}
