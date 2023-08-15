import { Component, OnInit } from '@angular/core';
import { Subject } from 'rxjs';
import { LoadingScreenService } from './loading-screen.service';

@Component({
  selector: 'app-loading-screen',
  templateUrl: './loading-screen.component.html',
  styleUrls: ['./loading-screen.component.scss']
})
export class LoadingScreenComponent implements OnInit {

  constructor(private loadingService: LoadingScreenService) { }

  isLoading:any;

  ngOnInit(): void {
    this.loadingService.isLoading.subscribe(result => this.isLoading = result)
  }

}
