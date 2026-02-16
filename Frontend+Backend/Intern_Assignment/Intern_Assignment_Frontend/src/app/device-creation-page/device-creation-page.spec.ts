import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeviceCreationPage } from './device-creation-page';

describe('DeviceCreationPage', () => {
  let component: DeviceCreationPage;
  let fixture: ComponentFixture<DeviceCreationPage>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DeviceCreationPage]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DeviceCreationPage);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
