import { TestBed } from '@angular/core/testing';

import { InMemoryData.ServiceService } from './in-memory-data.service.service';

describe('InMemoryData.ServiceService', () => {
  let service: InMemoryData.ServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(InMemoryData.ServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
