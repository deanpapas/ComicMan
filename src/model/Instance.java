package model;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.Dao;
import dao.DaoImpl;

public class Instance {

    public DaoImpl comicbookDAO;
    public DaoImpl characterDAO;
    public ArrayList<Collection> collectionArrayList;
    
    public Instance() {
        comicbookDAO = new DaoImpl("comicbook");
        characterDAO = new DaoImpl("character");
        collectionArrayList = new ArrayList<Collection>();

        Entry[] allEntries = new Entry[comicbookDAO.getComicbooks().length + characterDAO.getCharacters().length];
        for (int i = 0; i < comicbookDAO.getComicbooks().length; i++) {
            allEntries[i] = new Entry(comicbookDAO.getComicbooks()[i].getTitle(), "Comicbook");
        }
        for (int i = 0; i < characterDAO.getCharacters().length; i++) {
            allEntries[i + comicbookDAO.getComicbooks().length] = new Entry(characterDAO.getCharacters()[i].getName(), "Character");
        }

        collectionArrayList.add(new Collection("All", allEntries));
        Entry[] temp = new Entry[0];
        collectionArrayList.add(new Collection("Favorites", temp));
    }

    public void setup() throws SQLException {
        comicbookDAO.setup();
        characterDAO.setup();
    }

    public Dao getComicDao()  {
        return comicbookDAO;
    }

    public Dao getCharacterDao()  {
        return characterDAO;
    }

    public Comicbook getComicbook(String searchType, String searchValue) throws SQLException {
        return comicbookDAO.getComicbook(searchType, searchValue);
    }

    public Character getCharacter(String searchType, String searchValue) throws SQLException {
        return characterDAO.getCharacter(searchType, searchValue);
    }

    public ArrayList<Collection> getCollections() {
        return collectionArrayList;
    }

    public void addCollection(Collection collection) {
        collectionArrayList.add(collection);
    }

}
