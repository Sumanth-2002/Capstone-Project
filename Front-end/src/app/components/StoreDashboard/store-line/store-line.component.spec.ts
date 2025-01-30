import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StoreLineComponent } from './store-line.component';

describe('StoreLineComponent', () => {
  let component: StoreLineComponent;
  let fixture: ComponentFixture<StoreLineComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [StoreLineComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(StoreLineComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
