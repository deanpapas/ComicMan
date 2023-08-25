package dao;

import model.Comicbook;
import factories.CharacterFactory;
import javafx.scene.Cursor;
import model.Character;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.sqlite.SQLiteDataSource;

public class DaoImpl implements Dao {
	private String type;
	private final String COMICBOOK_TABLE_NAME = "comicbooks";
	private final String CHARACTER_TABLE_NAME = "characters";

	public DaoImpl(String type) {
		this.type = type;
	}

	// Create Database
	@Override
	public void setup() throws SQLException {
		if (type.equals("comicbook")) {
			try (Connection connection = Database.getConnection(COMICBOOK_TABLE_NAME);
					Statement stmt = connection.createStatement();) {
				String sql = "CREATE TABLE IF NOT EXISTS " + COMICBOOK_TABLE_NAME
						+ " (title VARCHAR(20) NOT NULL," + "authors VARCHAR(50) NOT NULL,"
						+ "publisher VARCHAR(20) NOT NULL,"
						+ "cover VARCHAR(20),"
						+ "releaseDate VARCHAR(10) NOT NULL," + "characters VARCHAR(50),"
						+ "PRIMARY KEY (title))";
				stmt.executeUpdate(sql);
			}
		} else if (type.equals("character")) {
			try (Connection connection = Database.getConnection(CHARACTER_TABLE_NAME);
					Statement stmt = connection.createStatement();) {
				String sql = "CREATE TABLE IF NOT EXISTS " + CHARACTER_TABLE_NAME
						+ " (name VARCHAR(20) NOT NULL," + "abilities VARCHAR(100),"
						+ "universe VARCHAR(20)," + "firstAppearance VARCHAR(20) NOT NULL,"
						+ "description VARCHAR(250)," + "image VARCHAR(20),"
						+ "identity VARCHAR(20)," + "villains VARCHAR(100),"
						+ "allies VARCHAR(100)," + "teams VARCHAR(100),"
						+ "nemesis VARCHAR(20)," + "category VARCHAR(10),"
						+ "PRIMARY KEY (name))";
				stmt.executeUpdate(sql);
			}
		}

	}

	// Get User
	@Override
	public Comicbook getComicbook(String searchType, String searchValue) throws SQLException {
		String sql = "SELECT * FROM " + COMICBOOK_TABLE_NAME + " WHERE " + searchType + " = ?";
		try (Connection connection = Database.getConnection(COMICBOOK_TABLE_NAME);
				PreparedStatement stmt = connection.prepareStatement(sql);) {
			stmt.setString(1, searchValue);

			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					String title = rs.getString("title");
					String[] authors = (rs.getString("authors")).split(",");
					String publisher = rs.getString("publisher");
					String cover = rs.getString("cover");
					String releaseDate = rs.getString("releaseDate");
					String[] characters = (rs.getString("characters")).split(",");

					Character[] characterArray = new Character[characters.length];
					for (int i = 0; i < characters.length; i++) {
						characterArray[i] = getCharacter("name", characters[i]);
					}
					
					return new Comicbook(title, authors, publisher, cover, releaseDate,
							characterArray);

				}
				return null;
			}
		}
	}

	@Override
	public Character getCharacter(String searchType, String searchValue) throws SQLException {

		String sql = "SELECT * FROM " + CHARACTER_TABLE_NAME + " WHERE " + searchType + " = ?";
		try (Connection connection = Database.getConnection(CHARACTER_TABLE_NAME);
				PreparedStatement stmt = connection.prepareStatement(sql);) {
			stmt.setString(1, searchValue);

			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					String name = rs.getString("name");
					String[] abilities = (rs.getString("abilities")).split(",");
					String universe = rs.getString("universe");
					String firstAppearance = rs.getString("firstAppearance");
					String image = rs.getString("image");
					String identity = rs.getString("identity");
					String[] villains = (rs.getString("villains")).split(",");
					String[] allies = (rs.getString("allies")).split(",");
					String[] teams = (rs.getString("teams")).split(",");
					String nemesis = rs.getString("nemesis");
					String category = rs.getString("category");

					CharacterFactory characterFactory = new CharacterFactory();
					Character newChar = characterFactory.newCharacter(name, abilities, universe, firstAppearance,
							 image, category, identity, villains, allies, teams, nemesis);
					return newChar;

				}
				return null;
			}
		}
	}

	@Override
	public Comicbook[] getComicbooks() {
		String sql = "SELECT * FROM " + COMICBOOK_TABLE_NAME;
		try (Connection connection = Database.getConnection(COMICBOOK_TABLE_NAME);
				PreparedStatement stmt = connection.prepareStatement(sql);) {
			try (ResultSet rs = stmt.executeQuery()) {
				Comicbook[] comicbookArray = new Comicbook[17];
				int i = 0;
				while (rs.next()) {
					String title = rs.getString("title");
					String[] authors = (rs.getString("authors")).split(",");
					String publisher = rs.getString("publisher");
					String cover = rs.getString("cover");
					String releaseDate = rs.getString("releaseDate");
					String[] characters = (rs.getString("characters")).split(",");

					Character[] characterArray = new Character[characters.length];
					for (int j = 0; j < characters.length; j++) {
						characterArray[j] = getCharacter("name", characters[j]);
					}

					comicbookArray[i] = new Comicbook(title, authors, publisher, cover, releaseDate,
							characterArray);
					i++;
				}
				return comicbookArray;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public Character[] getCharacters(){
		String sql = "SELECT * FROM " + CHARACTER_TABLE_NAME;
		try (Connection connection = Database.getConnection(CHARACTER_TABLE_NAME);
				PreparedStatement stmt = connection.prepareStatement(sql);) {
			try (ResultSet rs = stmt.executeQuery()) {
				Character[] characterArray = new Character[17];
				int i = 0;
				while (rs.next()) {
					String name = rs.getString("name");
					String[] abilities = (rs.getString("abilities")).split(",");
					String universe = rs.getString("universe");
					String firstAppearance = rs.getString("firstAppearance");
					String image = rs.getString("image");
					String identity = rs.getString("identity");
					String[] villains = (rs.getString("villains")).split(",");
					String[] allies = (rs.getString("allies")).split(",");
					String[] teams = (rs.getString("teams")).split(",");
					String nemesis = rs.getString("nemesis");
					String category = rs.getString("category");

					CharacterFactory characterFactory = new CharacterFactory();
					Character newChar = characterFactory.newCharacter(name, abilities, universe, firstAppearance,
							 image, category, identity, villains, allies, teams, nemesis);
					characterArray[i] = newChar;
					i++;
				}
				return characterArray;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}