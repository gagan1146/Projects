import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GenerateList } from './generate-list';

describe('GenerateList', () => {
  let component: GenerateList;
  let fixture: ComponentFixture<GenerateList>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GenerateList]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GenerateList);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
