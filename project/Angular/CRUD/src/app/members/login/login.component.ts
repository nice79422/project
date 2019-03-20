import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { MemberService } from '../shared/member.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  memberId : string;
  memberPw : string;
  constructor(private memberService : MemberService, public http:HttpClient) { }
  body = {
    id:this.memberId,
    pw:this.memberPw,
  };
  
  private url="http://localhost:8282/57thAngular/";

  ngOnInit() {
  }

  resetForm(form? : NgForm){
    if (form != null)
      form.reset();
      this.memberService.selectedMember = {
        id : null,
        pw : '',
        addr : '',
        tel : ''
      }
  } 
  private loginUrl = "http://localhost:4200/#/page3"
  loginOk(){
    alert("로그인성공");
    window.location.href=(this.loginUrl)
 }

      submit(){
        let httpParams = new HttpParams()
        .set('id',this.memberId)
        .set('pw',this.memberPw);
        this.http.post(this.url+"angular/login.do", this.body, {params: httpParams,
      })
      .subscribe(
        data => this.loginOk(),
        
        error => alert("로그인실패")

      );
   
    
      }
  }










