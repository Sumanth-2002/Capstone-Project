import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LineAdminComponent } from './line-admin.component';

describe('LineAdminComponent', () => {
  let component: LineAdminComponent;
  let fixture: ComponentFixture<LineAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LineAdminComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LineAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
