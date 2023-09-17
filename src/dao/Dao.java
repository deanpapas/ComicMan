package dao;

import java.sql.SQLException;
import model.Character;
import model.Comicbook;

//Data Access Object

//Defines the methods that will be used to access the database
//Helps to separate the business logic from the data access logic

public interface Dao {
	void setup() throws SQLException;
	Comicbook[] getComicbooks() throws SQLException;
	Character[] getCharacters() throws SQLException;
	Comicbook getComicbook(String searchType, String searchValue) throws SQLException;
	Character getCharacter(String searchType, String searchValue) throws SQLException;
}