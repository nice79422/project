import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MemberInsertComponent } from './member-insert.component';

describe('MemberInsertComponent', () => {
  let component: MemberInsertComponent;
  let fixture: ComponentFixture<MemberInsertComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MemberInsertComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MemberInsertComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
