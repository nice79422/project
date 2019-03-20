import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
//import { HttpModule } from '@angular/http';
import { HttpClientModule } from '@angular/common/http';
import { HttpModule } from '@angular/http'

import {AppRouterModule} from './app.router.module';

import { AppComponent } from './app.component';
import { MembersComponent } from './members/members.component';
import { MemberComponent } from './members/member/member.component';

import { AgGridModule } from 'ag-grid-angular';
import { MemberListComponent } from './members/member-list/member-list.component';
import { MemberInsertComponent } from './members/member-insert/member-insert.component';
import { LoginComponent } from './members/login/login.component';
import { WelcomeComponent } from './welcome/welcome.component';
import { Page1Component } from './page1/page1.component';
import { Page2Component } from './page2/page2.component';
import { Page3Component } from './page3/page3.component';



@NgModule({
  declarations: [
    AppComponent,
    MembersComponent,
    MemberComponent,
    MemberListComponent,
    MemberInsertComponent,
    LoginComponent,
    WelcomeComponent,
    Page1Component,
    Page2Component,
    Page3Component
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    HttpClientModule,
    AppRouterModule,
    AgGridModule.withComponents([])
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
