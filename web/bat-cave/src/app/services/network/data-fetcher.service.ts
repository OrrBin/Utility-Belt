import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Subject } from 'rxjs';
import { NewSongEvent } from '../../core/events/new-song-event';
import { Song } from '../../core/types/song';

@Injectable({
  providedIn: 'root'
})
export class DataFetcherService {

  private audioListUrl: string = "http://localhost:8080/list-audio";

  constructor(private http: HttpClient) { }

  listAudioFiles() {
    // now returns an Observable of Config
    return this.http.get<AudioRecord[]>(this.audioListUrl);
  }

}
