import { HttpClient } from '@angular/common/http';
import { Component, HostListener, Input, OnDestroy, OnInit, ViewChild, ElementRef } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { select, Store } from '@ngrx/store';
import { Observable, Subject } from 'rxjs';
import { LoadingScreenService } from '../loading-screen/loading-screen.service';
import { Users } from '../model/users';
import { MessagingService } from '../services/messaging.service';
import { AppState } from '../store/app.state';
import { removeUser, updateUsers } from '../store/users/user.actions';
import { selectCurrentUser, selectUsers } from '../store/users/user.selectors';
import { UserState } from '../store/users/user.state';
declare let EventSource:any;

@Component({
  selector: 'app-chatbox',
  templateUrl: './chatbox.component.html',
  styleUrls: ['./chatbox.component.scss']
})
export class ChatboxComponent implements OnInit, OnDestroy {
  @ViewChild('endOfChat')
  endOfChat!: ElementRef;
  source:any;
  message: any;
  isLoading:any;

  user: any;
  users: any;
  loginResponse:any;
  data: any;
  status: any;
  text:any;
  sseEmitter: any;

  constructor(private loadingService: LoadingScreenService, private http: HttpClient, private store: Store<AppState>, private route: ActivatedRoute, private router: Router, private messageingService: MessagingService) {
    this.http.get<any[]>('http://localhost:8080/chatbox/user/getAll').subscribe(u => this.users = u.filter(u => u.online));
    this.store.select(selectUsers).subscribe(u => this.users = u.filter(u => u.online));
    this.store.select(selectCurrentUser).subscribe(u => {console.log(u);this.user = u});
  }
  ngOnInit(): void {
    this.loadingService.isLoading.subscribe(result => this.isLoading = result)
    this.messageingService.receiveMessaging();
    this.message = this.messageingService.currentMessage;
    this.loadingService.show();
    this.source = new EventSource('http://localhost:8080/chatbox/message/stream');
    this.source.addEventListener('7dfe5f50-806c-47cd-aaa6-1019d96a28ab', (message:any) => {
      this.sseEmitter = message;
      this.data.push(JSON.parse(message.data));
      this.scrollToBottom();
    })
    this.source.addEventListener('userPresence', (user:any) => {
      console.log(this.user.id);
      this.store.dispatch(updateUsers({user: JSON.parse(user.data), currentUserId: this.user.id}));
    })
    if(this.source != undefined){
      this.http.get<any[]>('http://localhost:8080/chatbox/message/getAll').subscribe({
        next: data => {
            this.data = data;
            console.log(this.data)
        },
        error: error => {
            console.error('There was an error!', error);
        }
      });
      setTimeout(()=>{
        this.loadingService.hide();
        this.scrollToBottom();
      }, 5000);
    }
  }

  @HostListener('window:beforeunload')
  async ngOnDestroy() {
    this.source.close();
    this.http.post<any[]>('http://localhost:8080/chatbox/user/logout', this.user ).subscribe(() =>{
  })
  }

  onSubmit(){
    var mess = {sender: this.user, room: {id: '7dfe5f50-806c-47cd-aaa6-1019d96a28ab'}, text: this.text}
    this.http.post<any[]>('http://localhost:8080/chatbox/message/send', mess ).subscribe(() => {
  })
    this.text = '';
  }

  scrollToBottom() {
    setTimeout(() => {
      if (this.endOfChat) {
        this.endOfChat.nativeElement.scrollIntoView({ behavior: 'smooth', block: "end", inline: "nearest" });
      }
    }, 100);
  }

}
