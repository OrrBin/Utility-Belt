import { Genres } from "./enums/genres.enum";

export class Song {

    
    constructor(private audioURL : String, private imageURL : String,
        name : String, artist : String, album : String, genre : Genres, milliseconds : number) {
	}
}
