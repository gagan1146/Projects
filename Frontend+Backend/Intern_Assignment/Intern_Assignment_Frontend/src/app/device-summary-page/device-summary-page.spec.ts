import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeviceSummaryPage } from './device-summary-page';

describe('DeviceSummaryPage', () => {
  let component: DeviceSummaryPage;
  let fixture: ComponentFixture<DeviceSummaryPage>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DeviceSummaryPage]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DeviceSummaryPage);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
