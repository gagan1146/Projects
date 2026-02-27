import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShelfCreationPage } from './shelf-creation-page';

describe('ShelfCreationPage', () => {
  let component: ShelfCreationPage;
  let fixture: ComponentFixture<ShelfCreationPage>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ShelfCreationPage]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ShelfCreationPage);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
