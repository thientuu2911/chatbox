import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoadingScreenService {

  public isLoading = new Subject<boolean>();

  constructor() {
    this.hide();
   }
 
  show() {
   this.isLoading.next(true);
  }
 
  hide() {
   this.isLoading.next(false);
  }

}
