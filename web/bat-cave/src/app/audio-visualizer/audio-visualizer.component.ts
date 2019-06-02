import { Component, OnInit, Input, ViewChild, ElementRef } from '@angular/core';
import { PlaybackServiceService } from '../services/audio/playback-service.service';
import { NewSongEvent } from '../core/events/new-song-event';

@Component({
  selector: 'app-audio-visualizer',
  templateUrl: './audio-visualizer.component.html',
  styleUrls: ['./audio-visualizer.component.css']
})
export class AudioVisualizerComponent implements OnInit {

  @Input()
  private _analyser: AnalyserNode;

  /** Template reference to the canvas element */
  @ViewChild('canvasEl') canvasEl: ElementRef;
  /** Canvas 2d context */
  private ctx: CanvasRenderingContext2D;

  private bufferLength: number;
  private dataArray: Uint8Array;
  private canvasWidth: number;
  private canvasHeight: number;

  private stopDrawing: boolean;

  constructor(private playbackService: PlaybackServiceService) {
    playbackService.newSong$.subscribe(
      (event: NewSongEvent) => {
        console.log("setting analyzer");

        this.analyser = event.analyser;
      });
  }


  get analyser(): AnalyserNode {
    return this._analyser;
  }


  // @Input()
  set analyser(analyser: AnalyserNode) {
    // console.log("in analyser input", analyser);

    this._analyser = analyser;
    // this.analyser.fftSize = 256;
    this.bufferLength = this.analyser.frequencyBinCount;
    // console.log(this.bufferLength);
    this.dataArray = new Uint8Array(this.bufferLength);
    this.ctx = (this.canvasEl.nativeElement as HTMLCanvasElement).getContext('2d');
    this.bufferLength = this.analyser.frequencyBinCount;
    this.dataArray = new Uint8Array(this.bufferLength);
    this.stopDrawing = false;

  }

  ngOnInit() {
    this.canvasWidth = document.body.clientWidth;
    this.canvasHeight = document.body.clientHeight;

  }

  ngAfterViewInit() {
    // this.ctx.clearRect(0, 0, 500, 500); 
    // this.canvasWidth = this.canvasEl.nativeElement.width;
    // this.canvasHeight = this.canvasEl.nativeElement.height;
    this.draw();
    // this.drawBall();
    // this.draw2();
  }

  /**
  * Draws something using the context we obtained earlier on
  */
  private draw() {

    if (!this.analyser) {
      let drawVisual = requestAnimationFrame(() => { this.draw() });
      return;
    }
    if (!this.stopDrawing) {
      let drawVisual = requestAnimationFrame(() => { this.draw() });
    }
    else {
      this.stopDrawing = false;
    }

    this.analyser.getByteFrequencyData(this.dataArray);

    var gradient = this.ctx.createLinearGradient(0, 0, 0, this.canvasHeight);
    gradient.addColorStop(0, 'rgb(50,20,60)');
    gradient.addColorStop(1, 'black');
    this.ctx.fillStyle = gradient;

    //this.ctx.fillStyle = 'rgb(0, 0, 0)';
    this.ctx.fillRect(0, 0, this.canvasWidth, this.canvasHeight);

    var barWidth = (this.canvasWidth / this.bufferLength) * 2.5;
    var barHeight;
    var x = 0;
    for (var i = 0; i < this.bufferLength; i++) {
      barHeight = this.dataArray[i] * 2;

      var r = barHeight / 4 + (15 * (i / this.bufferLength));
      var g = 100 * (i / this.bufferLength);
      var b = 100;

      this.ctx.fillStyle = "rgb(" + r + "," + g + "," + b + ")";
      this.ctx.fillRect(x, this.canvasHeight - barHeight, barWidth, barHeight);

      x += barWidth + 1;
    }
  }

  // private draw2() {
  //   var drawVisual = requestAnimationFrame(() => {this.draw2()});
  //   // if(this.timeToDraw == 3) {
  //     // this.timeToDraw = 0;
  //     this.analyser.getByteTimeDomainData(this.dataArray2);    
  //   // }
  //   // this.timeToDraw += 1;
  //   // this.analyser.getByteTimeDomainData(this.dataArray);
  //   // this.ctx.fillStyle = 'rgb(0, 0, 0)';
  //   // this.ctx.fillRect(0, 0, this.WIDTH, this.HEIGHT);

  //   this.ctx.lineWidth = 4;
  //   this.ctx.strokeStyle = 'rgb(80, 60, 120)';
  //   this.ctx.beginPath();

  //   var sliceWidth = this.WIDTH * 1.0 / this.bufferLength;
  //   var x = 0;

  //   for(var i = 0; i < this.bufferLength; i++) {

  //     var v = this.dataArray2[i] / 128.0;
  //     v = v*v;
  //     var y = 1*this.HEIGHT/4 + (v * this.HEIGHT/4);

  //     if(i === 0) {
  //       this.ctx.moveTo(x, y);
  //     } else {
  //       this.ctx.lineTo(x, y);
  //     }

  //     x += sliceWidth;
  //   }

  //   // this.ctx.lineTo(this.WIDTH, this.HEIGHT/2);
  //   this.ctx.lineTo(this.WIDTH, y);
  //     this.ctx.stroke();
  //   }

  // }
}