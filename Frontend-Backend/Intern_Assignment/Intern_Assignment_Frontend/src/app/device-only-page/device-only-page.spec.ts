import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeviceOnlyPage } from './device-only-page';

describe('DeviceOnlyPage', () => {
  let component: DeviceOnlyPage;
  let fixture: ComponentFixture<DeviceOnlyPage>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DeviceOnlyPage]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DeviceOnlyPage);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
