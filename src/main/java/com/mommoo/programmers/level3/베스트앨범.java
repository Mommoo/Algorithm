package com.mommoo.programmers.level3;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class 베스트앨범 {
    public static void main(String[] args) {
        String[] genres = new String[]{
                "classic", "pop", "classic", "classic", "pop"
        };
        int[] plays = new int[] {
                500, 600, 150, 800, 2500
        };
        System.out.println(Arrays.toString(new 베스트앨범().solution(genres, plays)));

        genres = new String[] {
                "a", "b", "c", "a", "b", "c", "a"
        };
        plays = new int[] {
                10, 20, 30, 50, 30, 5, 100
        };
        System.out.println(Arrays.toString(new 베스트앨범().solution(genres, plays)));
    }

    public int[] solution(String[] genreStrings, int[] plays) {
        Genres genres = Genres.from(genreStrings, plays);
        return genres
                .getAscendingOrderGenres()
                .stream()
                .map(genres::findTopTwoSongIndexes)
                .flatMap(List::stream)
                .mapToInt(Integer::intValue)
                .toArray();
    }

    private static class Genres {
        private final Map<String, List<Song>> songs;

        private Genres(Map<String, List<Song>> songs) {
            this.songs = songs;
        }

        public static Genres from(String[] genres, int[] plays) {
            Map<String, List<Song>> songs = new HashMap<>();

            for (int index = 0; index < genres.length; index++) {
                String genre = genres[index];
                int playCount = plays[index];
                songs.computeIfAbsent(genre, key -> new LinkedList<>())
                     .add(new Song(index, playCount));
            }

            doSongsAscendingOrder(songs);

            return new Genres(songs);
        }

        private static void doSongsAscendingOrder(Map<String, List<Song>> songs) {
            for (List<Song> genreSongs : songs.values()) {
                Collections.sort(genreSongs);
            }
        }


        public List<Genre> getAscendingOrderGenres() {
            return songs.entrySet().stream()
                        .map(Genre::from)
                        .sorted()
                        .collect(Collectors.toList());
        }

        public List<Integer> findTopTwoSongIndexes(Genre genre) {
            return songs.get(genre.name)
                        .stream()
                        .map(song -> song.index)
                        .limit(2)
                        .collect(Collectors.toList());
        }
    }

    private static class Genre implements Comparable<Genre> {
        private final String name;
        private final int totalSongCount;

        private Genre(String name, int totalSongCount) {
            this.name = name;
            this.totalSongCount = totalSongCount;
        }

        public static Genre from(Map.Entry<String, List<Song>> genreEntry) {
            String genreName = genreEntry.getKey();
            int totalSongCount = genreEntry.getValue()
                                           .stream()
                                           .mapToInt(song -> song.playsCount)
                                           .sum();

            return new Genre(genreName, totalSongCount);
        }

        @Override
        public int compareTo(Genre o) {
            return o.totalSongCount - this.totalSongCount;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (!(o instanceof Genre))
                return false;
            Genre genre = (Genre) o;
            return totalSongCount == genre.totalSongCount &&
                   Objects.equals(name, genre.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, totalSongCount);
        }
    }

    private static class Song implements Comparable<Song> {
        private final int index;
        private final int playsCount;

        public Song(int index, int playsCount) {
            this.index = index;
            this.playsCount = playsCount;
        }

        @Override
        public int compareTo(Song o) {
            return o.playsCount - this.playsCount;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (!(o instanceof Song))
                return false;
            Song song = (Song) o;
            return index == song.index &&
                   playsCount == song.playsCount;
        }

        @Override
        public int hashCode() {
            return Objects.hash(index, playsCount);
        }
    }
}
