import java.io.*;
import java.util.*;

class Genre implements Comparable<Genre>{
	String genre;
	int playback_genre;
	
	public Genre(String genre, int playback_genre) {
		this.genre = genre;
		this.playback_genre = playback_genre;
	}
	
	@Override
	public int compareTo(Genre g) {
		return g.playback_genre - this.playback_genre;
	}
}

class Song implements Comparable<Song>{
	int playback_song;
	int title;
	
	public Song(int playback_song, int title) {
		this.playback_song = playback_song;
		this.title = title;
	}
	
	@Override
	public int compareTo(Song song) {
		if(this.playback_song == song.playback_song) {
			return this.title - song.title; // 오름차순
		}else
			return song.playback_song - this.playback_song; // 내림차순
	}
}


class Solution{
    public int[] solution(String[] genres, int[] plays) {
    	HashMap<String, Integer> map = new HashMap<>();

        PriorityQueue<Genre> pq_genre = new PriorityQueue<>();
        HashMap<String,PriorityQueue<Song>> map_song = new HashMap<>();
        ArrayList<Integer> list = new ArrayList<>();
        
        
        for(int i =0; i<genres.length; i++) {
        	map.put(genres[i], map.getOrDefault(genres[i], 0)+plays[i]);
        	
        	Song song = new Song(plays[i],i); // 재생 횟수, 노래 제목
        	if(map_song.containsKey(genres[i]))
        		map_song.get(genres[i]).offer(song);
        	else {
        		PriorityQueue<Song> pq = new PriorityQueue<>();
        		pq.offer(song);
        		map_song.put(genres[i], pq);
        	}	
        }
        
        for(String key : map.keySet()) 
        	pq_genre.offer(new Genre(key, map.get(key)));
        
        
        
        while(!pq_genre.isEmpty()) {
        	String genre = pq_genre.poll().genre;
        	PriorityQueue<Song> genre_song = map_song.remove(genre);
        	int count = 0;
        	while(!genre_song.isEmpty()) {
        		list.add(genre_song.poll().title);
        		count++;
        		if(count == 2)
        			break;
        	}
        }
        
        int[] answer = new int[list.size()];
        
        for(int i =0; i<answer.length; i++) 
        	answer[i] = list.get(i);
        	
        	
        
        return answer;
    }
}