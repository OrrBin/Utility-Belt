import { Component, OnInit } from '@angular/core';

import { Howl, Howler } from "howler";

@Component({
  selector: 'app-audio-player',
  templateUrl: './audio-player.component.html',
  styleUrls: ['./audio-player.component.css']
})
export class AudioPlayerComponent implements OnInit {

  sound : Howl;

  constructor() { 
  }

  ngOnInit() {
    this.sound = new Howl( {
      src:['http://localhost:4200/resources/Blur%20-%20Song%202.mp3']
    })
    this.sound.play();
  }

}
