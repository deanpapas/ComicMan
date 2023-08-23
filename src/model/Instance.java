package model;

import java.sql.SQLException;

import dao.Dao;
import dao.DaoImpl;

public class Instance {

    public DaoImpl comicDAO;
    public DaoImpl characterDAO;
    
    public Instance() {
        comicDAO = new DaoImpl("comicbook");
        characterDAO = new DaoImpl("character");
    }

    public void setup() throws SQLException {
        comicDAO.setup();
        characterDAO.setup();
    }

    public Dao getComicDao()  {
        return comicDAO;
    }

    public Dao getCharacterDao()  {
        return characterDAO;
    }

    public Comicbook getComicbook(String searchType, String searchValue) throws SQLException {
        return comicDAO.getComicbook(searchType, searchValue);
    }

    public Character getCharacter(String searchType, String searchValue) throws SQLException {
        return characterDAO.getCharacter(searchType, searchValue);
    }


}
