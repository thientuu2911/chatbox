import { Injectable } from '@angular/core';
import { BehaviorSubject, mergeMap, mergeMapTo } from 'rxjs';
import { AngularFireMessaging } from "@angular/fire/compat/messaging";

@Injectable({
  providedIn: 'root'
})
export class MessagingService {
  currentMessage = new BehaviorSubject<any>(null);
  token:string | undefined | null;
  constructor(private angularFireMessaging: AngularFireMessaging){
    this.angularFireMessaging.getToken.subscribe((token) => {
      this.token = token;  
      console.log('Token: '+token);
    })
  }

  requestPermission(){
    this.angularFireMessaging.requestPermission.pipe(mergeMapTo(this.angularFireMessaging.tokenChanges))
      .subscribe(
        (token) =>{
          console.log("Permission granted! Token: "+ token);
        },
        (error) => {
          console.error(error);
        }
      )
  }

  getToken(){
    return this.token;
  }

  receiveMessaging(){
    this.angularFireMessaging.messages.subscribe((payload) =>{
        console.log('new message received: '+payload);
        this.currentMessage.next(payload);
    })
  }
}
