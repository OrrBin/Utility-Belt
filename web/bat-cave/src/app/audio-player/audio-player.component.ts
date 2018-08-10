import { Component, OnInit, Input, ViewChild, ElementRef, Output, EventEmitter } from '@angular/core';

import { Howl, Howler } from "howler";
import { Song } from '../core/types/song';
import { PlaybackServiceService } from '../services/audio/playback-service.service';

@Component({
  selector: 'app-audio-player',
  templateUrl: './audio-player.component.html',
  styleUrls: ['./audio-player.component.css']
})
export class AudioPlayerComponent implements OnInit {
  
  SONGS = [
    {
      url: "http://localhost:4200/resources/RedHotChiliPeppers-GoodbyeAngels.mp3"
    },
    {
      url: "http://localhost:4200/resources/ACDC-Thunderstruck.mp3"
    },
    {
      url: "http://localhost:4200/resources/Blur-Song2.mp3"
    },
    {
      url: "http://localhost:4200/resources/RedHotChiliPeppers-CantStop.mp3"
    },        
    {
      url: "http://localhost:4200/resources/GreenDaybasketcase.mp3"
    }
  ];
  
  @Input()
  song : Song;
  
  @Output() anlayserChange = new EventEmitter<AnalyserNode>();
  analyser : AnalyserNode;
  
  sound : Howl;
  
  private isPlaying : boolean;
  private currentSong : number = 0;
  
  constructor(private playbackService : PlaybackServiceService) { 
    
  }
  
  ngOnInit() {
    this.sound = new Howl( {
      src:[this.SONGS[this.currentSong].url]
    })
    this.sound.play();
    this.isPlaying = true;
    
  }
  ngAfterViewInit() {
    this.analyser = Howler.ctx.createAnalyser();
    Howler.masterGain.connect(this.analyser);
    this.analyser.connect(Howler.ctx.destination)
    this.analyser.fftSize = 256;
    this.playbackService.announceNewSong(null, this.analyser);
    this.anlayserChange.emit(this.analyser);
    
  }
  
  play() {
    this.sound.play();
    this.isPlaying = true;
  }
  
  pause() {
    this.sound.pause();
    this.isPlaying = false;
  }
  
  nextSong() {
    if(this.currentSong < this.SONGS.length - 1) {
      this.sound.stop();
      this.sound.unload();
      
      this.currentSong += 1;
      this.sound = new Howl( {
        src:[this.SONGS[this.currentSong].url]
      })

      this.sound.play();
      this.isPlaying = true;
      this.analyser = Howler.ctx.createAnalyser();
      Howler.masterGain.connect(this.analyser);
      this.analyser.connect(Howler.ctx.destination)
      this.analyser.fftSize = 256;
      this.playbackService.announceNewSong(null, this.analyser);
      this.anlayserChange.emit(this.analyser);
    }
  }
}
