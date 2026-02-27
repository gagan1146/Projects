import { TestBed } from '@angular/core/testing';

import { ShelfPosiiton } from './shelf-posiiton';

describe('ShelfPosiiton', () => {
  let service: ShelfPosiiton;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ShelfPosiiton);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
