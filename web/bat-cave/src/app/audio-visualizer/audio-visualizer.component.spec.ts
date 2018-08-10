import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AudioVisualizerComponent } from './audio-visualizer.component';

describe('AudioVisualizerComponent', () => {
  let component: AudioVisualizerComponent;
  let fixture: ComponentFixture<AudioVisualizerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AudioVisualizerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AudioVisualizerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
