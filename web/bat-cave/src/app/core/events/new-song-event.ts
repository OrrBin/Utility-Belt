import { Song } from "../types/song";

export class NewSongEvent {

    constructor(private _song : Song, private _analyser : AnalyserNode) {}
    
    
    public get analyser() : AnalyserNode {
        return this._analyser;
    }

    
    public set analyser(v : AnalyserNode) {
        this._analyser = v;
    }
    
    
    public get song() : Song {
        return this._song;
    }

    
    public set song(v : Song) {
        this._song = v;
    }
    

    
    
}
