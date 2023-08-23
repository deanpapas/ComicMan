package dao;

import java.sql.SQLException;
import model.Character;
import model.Comicbook;

//Data Access Object
public interface Dao {
	void setup() throws SQLException;
	Comicbook getComicbook(String searchType, String searchValue) throws SQLException;
	Character getCharacter(String searchType, String searchValue) throws SQLException;
	Boolean setCharacter(String name, String type) throws SQLException;
}