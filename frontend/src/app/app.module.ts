import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { StoreModule } from '@ngrx/store';
import { ChatboxComponent } from './chatbox/chatbox.component';
import { appReducer, metaReducers } from './store/app.state';
import { StoreDevtoolsModule } from '@ngrx/store-devtools';
import { environment } from '../environments/environment';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from './material/material.module';
import { LoadingScreenComponent } from './loading-screen/loading-screen.component';
import { CustomToastComponent } from './custom-toast/custom-toast.component';
import { LottieModule } from 'ngx-lottie';
import { AngularFireAuthModule } from '@angular/fire/compat/auth';
import { AngularFireModule } from '@angular/fire/compat';
import { AngularFireMessagingModule} from '@angular/fire/compat/messaging';
import { AsyncPipe } from '@angular/common';
import player from 'lottie-web';
import { MessagingService } from './services/messaging.service';
import { ConfirmationDialog } from './confirmation-dialog/confirmation-dialog.component';
import { HydrationEffects } from './store/hydration/hydration.effects';
import { EffectsModule } from "@ngrx/effects";

export function playerFactory() {
  return player;
}
@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    ChatboxComponent,
    LoadingScreenComponent,
    CustomToastComponent,
    ConfirmationDialog,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    StoreModule.forRoot(appReducer, { metaReducers }),
    EffectsModule.forRoot([HydrationEffects]),
    // The HttpClientInMemoryWebApiModule module intercepts HTTP requests
    // and returns simulated server responses.
    // Remove it when a real server is ready to receive requests.
    FormsModule,
    StoreDevtoolsModule.instrument({ maxAge: 25, logOnly: environment.production }),
    BrowserAnimationsModule,
    MaterialModule, 
    ReactiveFormsModule,
    LottieModule.forRoot({player:playerFactory}),
    AngularFireAuthModule,
    AngularFireMessagingModule,
    AngularFireModule.initializeApp(environment.firebase)
  ],

  providers: [MessagingService, AsyncPipe],
  bootstrap: [AppComponent]
})
export class AppModule { }
