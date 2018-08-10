import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { NewSongEvent } from '../../core/events/new-song-event';
import { Song } from '../../core/types/song';

@Injectable({
  providedIn: 'root'
})
export class PlaybackServiceService {
  
  private newSongPlayingSource = new Subject<NewSongEvent>();
  
  newSong$ = this.newSongPlayingSource.asObservable();
  
  announceNewSong(song : Song, analyser : AnalyserNode) {
    this.newSongPlayingSource.next(new NewSongEvent(song, analyser));
    console.log("NEW SONG ANNOUNCED");
  }
}
