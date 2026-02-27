import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShelfOnlyPage } from './shelf-only-page';

describe('ShelfOnlyPage', () => {
  let component: ShelfOnlyPage;
  let fixture: ComponentFixture<ShelfOnlyPage>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ShelfOnlyPage]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ShelfOnlyPage);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
