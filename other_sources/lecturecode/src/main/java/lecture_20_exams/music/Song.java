package lecture_20_exams.music;

import java.util.Date;

/**
 * This class describes a song
 * @author Martin Vatshelle
 */
public class Song {

	private String artist;
	private String title;
	private String genre;
	private Date releaseDate;
	
	public Song(String artist, String title, String genre, Date releaseDate) {
		this.artist = artist;
		this.title = title;
		this.genre = genre;
		this.releaseDate = releaseDate;
	}

	public String getArtist() {
		return artist;
	}

	public String getTitle() {
		return title;
	}

	public String getGenre() {
		return genre;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}
}

