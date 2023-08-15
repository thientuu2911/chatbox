import { Component, OnInit } from '@angular/core';
import { Spinner } from 'spin.js';
import { AnimationOptions } from 'ngx-lottie';
import { LoadingScreenService } from '../loading-screen/loading-screen.service';
import { HttpClient } from '@angular/common/http';
import { select, Store } from '@ngrx/store';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Users } from '../model/users';

@Component({
  selector: 'app-custom-toast',
  templateUrl: './custom-toast.component.html',
  styleUrls: ['./custom-toast.component.scss']
})
export class CustomToastComponent implements OnInit {
  isLoading = false;
  isSaved = false;
  user$:Observable<Users> | undefined;
  user: any;
  constructor(private http: HttpClient, private store: Store<{ user: any }>, private route: ActivatedRoute, private router: Router) { }
  options: AnimationOptions = {};
  ngOnInit(): void {
    this.user$ = this.store.pipe(select('user'));
    this.user$.subscribe(u => this.user = u);
  }
  saveData() {
    this.isLoading = true;
    this.options= {
      ...this.options,
      path: '../assets/loading.json',
      autoplay: true,
      loop: true
    }
    this.http.post<any[]>('http://localhost:8080/chatbox/user/login', this.user ).subscribe({
        next: data => {
          this.user = data;
          this.isLoading = false;
          this.isSaved = true;
          this.options= {
            ...this.options, // In case you have other properties that you want to copy
            path: '../assets/saved.json',
            loop: false
          };
        },
        error: error => {
          this.isSaved = false;
          console.error('There was an error!', error);
        },
      })
  }
}
