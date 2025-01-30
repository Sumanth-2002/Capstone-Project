import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StoreDoughnutComponent } from './store-doughnut.component';

describe('StoreDoughnutComponent', () => {
  let component: StoreDoughnutComponent;
  let fixture: ComponentFixture<StoreDoughnutComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [StoreDoughnutComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(StoreDoughnutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
