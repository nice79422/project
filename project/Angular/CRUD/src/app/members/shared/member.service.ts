import { Injectable } from '@angular/core';

import { Member } from './member.model';
@Injectable({
  providedIn: 'root'
})
export class MemberService {
  selectedMember : Member
  constructor() { };
}


