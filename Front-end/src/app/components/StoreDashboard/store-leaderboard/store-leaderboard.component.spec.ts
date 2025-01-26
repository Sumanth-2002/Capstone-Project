import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StoreLeaderboardComponent } from './store-leaderboard.component';

describe('StoreLeaderboardComponent', () => {
  let component: StoreLeaderboardComponent;
  let fixture: ComponentFixture<StoreLeaderboardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [StoreLeaderboardComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(StoreLeaderboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
