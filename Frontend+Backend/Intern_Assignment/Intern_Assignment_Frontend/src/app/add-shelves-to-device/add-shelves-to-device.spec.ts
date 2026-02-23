import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddShelvesToDevice } from './add-shelves-to-device';

describe('AddShelvesToDevice', () => {
  let component: AddShelvesToDevice;
  let fixture: ComponentFixture<AddShelvesToDevice>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddShelvesToDevice]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddShelvesToDevice);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
