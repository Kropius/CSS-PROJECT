import { TestBed } from '@angular/core/testing';

import { AdditionServiceService } from './operation-service';

describe('AdditionServiceService', () => {
  let service: AdditionServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AdditionServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
