

import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';

import { MemberService } from '../shared/member.service';


@Component({
  selector: 'app-member-insert',
  templateUrl: './member-insert.component.html',
  styleUrls: ['./member-insert.component.css']
})
export class MemberInsertComponent implements OnInit {
  memberId : string;
  memberPw : string;
  memberAddr : string;
  memberTel : string;
  

  constructor(private memberService : MemberService, public http:HttpClient) { }
  body = {
    id:this.memberId,
    pw:this.memberPw,
    addr:this.memberAddr,
    tel:this.memberTel
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

  submit(){
    let httpParams = new HttpParams()
    .set('id',this.memberId)
    .set('pw',this.memberPw)
    .set('addr', this.memberAddr)
    .set('tel',this.memberTel);

    this.http.post(this.url+"angular/insertMember.do", this.body, {params: httpParams,
  })
  .subscribe();
  alert("저장되었습니다.")

  }
}