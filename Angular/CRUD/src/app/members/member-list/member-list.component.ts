import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { NgForm } from '@angular/forms';
import { MemberService } from '../shared/member.service';


@Component({
  selector: 'app-member-list',
  templateUrl: './member-list.component.html',
  styleUrls: ['./member-list.component.css']
})
export class MemberListComponent implements OnInit {
  memberId : string;
  delete : string;
  constructor(public http:HttpClient) { }

  body = {
    id:this.memberId}

  private url="http://localhost:8282/57thAngular/";

  private dataArray = [];
 
  ngOnInit() {
    //this.Http();
  }
  
  Http(){
    this.http.get(this.url+"angular/findMemberList.do").subscribe((result : any[])=>{
      this.dataArray = result;
    });
  }

  deleteId(params){
    this.delete = params.data.id;

  }

  deleteMember(){

    let httpParams = new HttpParams()
    .set('id',this.delete)
    this.http.post(this.url+"angular/deleteMember.do", this.body, {params: httpParams,
    })
    .subscribe();
    alert("삭제 성공");
    }
  


    columnDefs = [
      {headerName: 'id', field: 'id', width: 100,  suppressSizeToFit:true, onCellClicked:params=>{this.deleteId(params);}},
      {headerName: 'pw', field: 'pw', width: 100, suppressSizeToFit:true, onCellClicked:params=>{this.deleteId(params);}},
      {headerName: 'addr', field: 'addr', width: 150, suppressSizeToFit:true, onCellClicked:params=>{this.deleteId(params);}},
      {headerName: 'tel', field: 'tel', width: 100, suppressSizeToFit:true, onCellClicked:params=>{this.deleteId(params);}}
    ];

}

