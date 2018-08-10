import { TestBed, inject } from '@angular/core/testing';

import { ImageFetcherService } from './image-fetcher.service';

describe('ImageFetcherService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ImageFetcherService]
    });
  });

  it('should be created', inject([ImageFetcherService], (service: ImageFetcherService) => {
    expect(service).toBeTruthy();
  }));
});
