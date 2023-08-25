package model;

import java.sql.SQLException;

import dao.Dao;
import dao.DaoImpl;

public class Instance {

    public DaoImpl comicbookDAO;
    public DaoImpl characterDAO;
    
    public Instance() {
        comicbookDAO = new DaoImpl("comicbook");
        characterDAO = new DaoImpl("character");
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


}
