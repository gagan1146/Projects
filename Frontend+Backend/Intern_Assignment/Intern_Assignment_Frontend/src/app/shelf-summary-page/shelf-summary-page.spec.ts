import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShelfSummaryPage } from './shelf-summary-page';

describe('ShelfSummaryPage', () => {
  let component: ShelfSummaryPage;
  let fixture: ComponentFixture<ShelfSummaryPage>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ShelfSummaryPage]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ShelfSummaryPage);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
