import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RoutingPage } from './routing-page';

describe('RoutingPage', () => {
  let component: RoutingPage;
  let fixture: ComponentFixture<RoutingPage>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RoutingPage]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RoutingPage);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
