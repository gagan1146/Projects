import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShelfCreationByDevice } from './shelf-creation-by-device';

describe('ShelfCreationByDevice', () => {
  let component: ShelfCreationByDevice;
  let fixture: ComponentFixture<ShelfCreationByDevice>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ShelfCreationByDevice]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ShelfCreationByDevice);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
