package model;

public class Comicbook {

    private String title;
    private String[] authors;
    private String publisher;
    private String[] genres;
    private String description;
    private String cover;
    private String releaseDate;
    private Character[] characters;

    public Comicbook(String title, String[] authors, String publisher,
            String cover, String releaseDate, Character[] characters) {
        this.title = title;
        this.authors = authors;
        this.publisher = publisher;
        this.cover = cover;
        this.releaseDate = releaseDate;
        this.characters = characters;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getAuthors() {
        return authors;
    }

    public void setAuthors(String[] authors) {
        this.authors = authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String[] getGenres() {
        return genres;
    }

    public void setGenres(String[] genres) {
        this.genres = genres;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String[] getCharacters() {
        String[] characterNames = new String[characters.length];
        for (int i = 0; i < characters.length; i++) {
            characterNames[i] = characters[i].getName();
        }
        return characterNames;
    }

    public void setCharacters(Character[] characters) {
        this.characters = characters;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String date) {
        this.releaseDate = date;
    }

}
