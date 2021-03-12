package com.mommoo.baekjoon.no20541;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class Main {
    private static final Console CONSOLE = new Console();

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int commandCount = Integer.parseInt(reader.readLine());

        Program program = new Program();
        while (commandCount-- > 0) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            Command command = Command.forName(tokenizer.nextToken());
            String parameter = tokenizer.nextToken();

            program.execute(command, parameter);
        }

        reader.close();
        CONSOLE.flush();
    }

    private static class Program {
        private final Album rootAlbum = new Album("album");
        private final Deque<Album> albumHistories = new LinkedList<>();

        public Program() {
            albumHistories.push(rootAlbum);
        }

        private Album currentAlbum() {
            return albumHistories.peekFirst();
        }

        public void execute(Command command, String parameter) {
            switch(command) {
                case MKALB:
                    executeMkalb(parameter);
                    break;

                case RMALB:
                    executeRmalb(parameter);
                    break;

                case INSERT:
                    executeInsert(parameter);
                    break;

                case DELETE:
                    executeDelete(parameter);
                    break;

                case CA:
                    executeCa(parameter);
                    break;
            }
        }

        public void executeMkalb(String parameter) {
            boolean isSuccess = currentAlbum().addAlbum(parameter);

            if (!isSuccess) {
                CONSOLE.writeDuplicateAlbumWarning();
            }
        }

        public void executeRmalb(String parameter) {
            if (!CommandParameter.isAll(parameter)) {
                Album removedAlbum = null;
                if (CommandParameter.isAscending(parameter)) {
                    removedAlbum = currentAlbum().removeFirstAlbum();
                } else if (CommandParameter.isDescending(parameter)) {
                    removedAlbum = currentAlbum().removeLastAlbum();
                } else {
                    removedAlbum = currentAlbum().removeAlbum(parameter);
                }

                if (removedAlbum == null) {
                    CONSOLE.write(0, 0);
                    return;
                }

                int removedAlbumCount = removedAlbum.deepCountChildAlbums() + 1;
                int removedPhotoCount = removedAlbum.deepCountChildAlbumPhotos() + removedAlbum.countPhotos();
                CONSOLE.write(removedAlbumCount, removedPhotoCount);
                return;
            }

            int removedAlbumsCount = currentAlbum().deepCountChildAlbums();
            int removedPhotosCount = currentAlbum().deepCountChildAlbumPhotos();

            currentAlbum().removeAllAlbum();
            CONSOLE.write(removedAlbumsCount, removedPhotosCount);
        }

        public void executeInsert(String parameter) {
            boolean isSuccess = currentAlbum().addPhoto(parameter);

            if (!isSuccess) {
                CONSOLE.writeDuplicatePhotoWarning();
            }
        }

        public void executeDelete(String parameter) {
            if (!CommandParameter.isAll(parameter)) {
                boolean isRemoved = false;
                if (CommandParameter.isAscending(parameter)) {
                    isRemoved = currentAlbum().removeFirstPhoto();
                } else if (CommandParameter.isDescending(parameter)) {
                    isRemoved = currentAlbum().removeLastPhoto();
                } else {
                    isRemoved = currentAlbum().removePhoto(parameter);
                }

                CONSOLE.write(isRemoved ? 1 : 0);
                return;
            }

            int removedPhotoCounts = currentAlbum().countPhotos();
            currentAlbum().removeAllPhotos();
            CONSOLE.write(removedPhotoCounts);
        }

        public void executeCa(String parameter) {
            if ("..".equals(parameter)) {
                if (albumHistories.size() > 1) {
                    this.albumHistories.pop();
                }

            } else if ("/".equals(parameter)) {
                this.albumHistories.clear();
                this.albumHistories.push(rootAlbum);
            } else {
                Album findAlbum = currentAlbum().findChildAlbumOrNull(parameter);
                if (findAlbum != null){
                    albumHistories.push(findAlbum);
                }
            }

            CONSOLE.write(currentAlbum().name);
        }
    }

    private static class Album {
        private final String name;
        private final TreeSet<String> photos = new TreeSet<>();

        private final TreeMap<String, Album> childAlbums = new TreeMap<>();

        public Album(String name) {
            this.name = name;
        }

        public boolean addPhoto(String imageName) {
            return photos.add(imageName);
        }

        public boolean removePhoto(String imageName) {
            if (imageName == null) {
                return false;
            }
            return photos.remove(imageName);
        }

        public boolean removeFirstPhoto() {
            if (photos.isEmpty()) {
                return false;
            }
            return removePhoto(photos.first());
        }

        public boolean removeLastPhoto() {
            if (photos.isEmpty()) {
                return false;
            }
            return removePhoto(photos.last());
        }

        public void removeAllPhotos() {
            photos.clear();
        }

        public boolean addAlbum(String albumName) {
            if (albumName == null || childAlbums.containsKey(albumName)) {
                return false;
            }

            childAlbums.put(albumName, new Album(albumName));
            return true;
        }

        public Album removeAlbum(String albumName) {
            return childAlbums.remove(albumName);
        }

        public Album removeFirstAlbum() {
            if (childAlbums.isEmpty()) {
                return null;
            }
            return removeAlbum(childAlbums.firstKey());
        }

        public Album removeLastAlbum() {
            if (childAlbums.isEmpty()) {
                return null;
            }
            return removeAlbum(childAlbums.lastKey());
        }

        public void removeAllAlbum() {
            childAlbums.clear();
        }

        public Album findChildAlbumOrNull(String albumName) {
            return childAlbums.get(albumName);
        }

        public int countPhotos() {
            return photos.size();
        }

        public int deepCountChildAlbumPhotos() {
            int countPhotos = 0;

            for (Album album : childAlbums.values()) {
                countPhotos += album.deepCountChildAlbumPhotos() + album.countPhotos();
            }

            return countPhotos;
        }

        public int deepCountChildAlbums() {
            int countAlbums = 0;

            for (Album album: childAlbums.values()) {
                countAlbums += album.deepCountChildAlbums() + 1;
            }

            return countAlbums;
        }
    }

    private enum Command {
        MKALB("mkalb"), RMALB("rmalb"), INSERT("insert"), DELETE("delete"), CA("ca");

        private final String value;

        Command(String value) {
            this.value = value;
        }

        public static Command forName(String value) {
            for (Command command : values()) {
                if (command.value.equals(value)) {
                    return command;
                }
            }

            return null;
        }
    }

    private static class CommandParameter {
        private static final String ASCENDING = "-1";
        private static final String DESCENDING = "1";
        private static final String ALL = "0";

        public static boolean isAscending(String parameter) {
            return parameter.equals(ASCENDING);
        }

        public static boolean isDescending(String parameter) {
            return parameter.equals(DESCENDING);
        }

        public static boolean isAll(String parameter) {
            return parameter.equals(ALL);
        }
    }

    private static class Console {
        private static final String DUPLICATED_ALBUM_NAME = "duplicated album name";
        private static final String DUPLICATED_PHOTO_NAME = "duplicated photo name";

        private final StringBuilder output = new StringBuilder();

        public void write(Object... values) {
            if(values.length == 0) {
                return;
            }
            output.append(values[0]);

            for (int i = 1 ; i < values.length; i++) {
                output.append(" ").append(values[i]);
            }

            output.append('\n');
        }

        public void writeDuplicateAlbumWarning() {
            write(DUPLICATED_ALBUM_NAME);
        }

        public void writeDuplicatePhotoWarning() {
            write(DUPLICATED_PHOTO_NAME);
        }

        public void flush() {
            System.out.println(output);
        }
    }
}
