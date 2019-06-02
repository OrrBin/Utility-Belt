import { Component, OnInit, Input, ViewChild, ElementRef, Output, EventEmitter } from '@angular/core';

import { Howl, Howler } from "howler";
import { Song } from '../core/types/song';
import { PlaybackServiceService } from '../services/audio/playback-service.service';
import { DataFetcherService } from '../services/network/data-fetcher.service';
import { SELECT_PANEL_INDENT_PADDING_X } from '@angular/material';

@Component({
  selector: 'app-audio-player',
  templateUrl: './audio-player.component.html',
  styleUrls: ['./audio-player.component.css']
})
export class AudioPlayerComponent implements OnInit {

  audioFiles: AudioRecord[] = [];

  SONGS = [
    {
      // url: "http://localhost:4200/resources/RedHotChiliPeppers-GoodbyeAngels.mp3"
      url: "test.mp3"
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
  song: Song;

  @Output() anlayserChange = new EventEmitter<AnalyserNode>();
  analyser: AnalyserNode;

  sound: Howl;

  private isPlaying: boolean;
  private currentSong: number = 0;

  constructor(
    private playbackService: PlaybackServiceService,
    private dataFetcher: DataFetcherService) {

  }


  ngOnInit() {
    // this.audioFiles = await this.dataFetcher.listAudioFiles().toPromise();
    this.dataFetcher.listAudioFiles().subscribe((filesList: AudioRecord[]) => {
      console.log(filesList[0].name);

      this.audioFiles = filesList;

      // this.nextSong();

      this.sound = new Howl({
        src: [this.fileNameToUrl(this.audioFiles[this.currentSong].name)],
        format: ['mp3']
      });
      this.isPlaying = true;
      this.sound.play();

      this.setupSong();

      setTimeout(() => {
        this.isPlaying = true;
      }, 200);
    });
    // console.log(this.SONGS[0].url);
    // this.sound = new Howl({
    //   src: [this.fileNameToUrl(this.SONGS[this.currentSong].url)],
    //   format: ['mp3']
    // })
    // this.sound.play();

    // setTimeout(() => {
    //   this.isPlaying = true;
    // }, 200);
  }

  async getAudioFiles() {

  }

  fileNameToUrl(fileName: string): string {
    return 'http://localhost:8080/audio/' + fileName;
  }

  ngAfterViewInit() {
    // if (this.sound) {
    //   this.analyser = Howler.ctx.createAnalyser();
    //   Howler.masterGain.connect(this.analyser);
    //   this.analyser.connect(Howler.ctx.destination)
    //   this.analyser.fftSize = 256;
    //   this.playbackService.announceNewSong(null, this.analyser);
    //   this.anlayserChange.emit(this.analyser);
    // }
  }

  play() {
    this.sound.play();
    this.isPlaying = true;
  }

  pause() {
    this.sound.pause();
    this.isPlaying = false;
  }

  setupSong() {
    if (this.currentSong < this.audioFiles.length - 1) {
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


  nextSong() {
    if (this.currentSong < this.audioFiles.length - 1) {

      if (this.sound) {
        this.sound.stop();
        this.sound.unload();
      }

      this.currentSong += 1;
      this.sound = new Howl({
        src: [this.fileNameToUrl(this.audioFiles[this.currentSong].name)]
      })

      this.setupSong();

      // this.sound.play();
      // this.isPlaying = true;
      // this.analyser = Howler.ctx.createAnalyser();
      // Howler.masterGain.connect(this.analyser);
      // this.analyser.connect(Howler.ctx.destination)
      // this.analyser.fftSize = 256;
      // this.playbackService.announceNewSong(null, this.analyser);
      // this.anlayserChange.emit(this.analyser);
    }
  }
}
