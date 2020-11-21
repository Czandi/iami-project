import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TeacherHubComponent } from './teacher-hub.component';

describe('TeacherHubComponent', () => {
  let component: TeacherHubComponent;
  let fixture: ComponentFixture<TeacherHubComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TeacherHubComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TeacherHubComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
