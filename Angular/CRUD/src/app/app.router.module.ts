import { RouterModule, Routes } from '@angular/router';
import {Page1Component} from './page1/page1.component';
import {Page2Component} from './page2/page2.component';
import {Page3Component} from './page3/page3.component';
import {WelcomeComponent} from './welcome/welcome.component';

const AppRoutes: Routes = [
  // { path: '', redirectTo: '/login', pathMatch: 'full' }, // 첫 화면을 login 페이지로 설정
  { path: '', component: WelcomeComponent, }, // 첫 화면페이지로 설정
  { path: 'page1', component: Page1Component, }, // url 경로가 /login 일때 LoginComponent를 보여준다.
  { path: 'page2', component: Page2Component, }, // url 경로가 /main 일때 MainComponent를 보여준다.
  { path: 'page3', component: Page3Component, },
  { path: '**', redirectTo: '', pathMatch: 'full' }, // 잘못된 URL을 사용했을때 Login 페이지로 돌려보냄.
  
];

export const AppRouterModule = RouterModule.forRoot(AppRoutes, {useHash: true});

