import { Component } from '@angular/core';
import { PlaybackServiceService } from './services/audio/playback-service.service';
import { NewSongEvent } from './core/events/new-song-event';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [PlaybackServiceService]
})
export class AppComponent {
  title = 'bat-cave';
  analyser : AnalyserNode;
  
  constructor(private playbackService : PlaybackServiceService) {
    // playbackService.newSong$.subscribe(
    //   (event : NewSongEvent) => {
    //     this.analyser = event.analyser;
    //   });  
  }
  
  // onAnalyserChange(analyser : AnalyserNode) {
  //   console.log("analyser ", analyser);
  //   this.analyser = analyser;
  // }
}
