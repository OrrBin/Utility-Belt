import { TestBed, inject } from '@angular/core/testing';

import { PlaybackServiceService } from './playback-service.service';

describe('PlaybackServiceService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [PlaybackServiceService]
    });
  });

  it('should be created', inject([PlaybackServiceService], (service: PlaybackServiceService) => {
    expect(service).toBeTruthy();
  }));
});
