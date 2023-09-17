package factories;

import model.Character;
import model.Hero;
import model.Villain;

/**
 * CharacterFactory
 * Creates a new character object
 * Returns the character object
 */

public class CharacterFactory {

    public Character newCharacter(String name, String[] abilities,
            String universe, String firstAppearance, 
            String image, String type, String identity, String[] villains, String[] allies,
            String[] teams, String nemesis) {
                if (type.equalsIgnoreCase("hero")) {
                    return new Hero(name, abilities, universe, firstAppearance,
                            image, type, identity, villains, allies, teams);
                } else if (type.equalsIgnoreCase("villain")) {
                    return new Villain(name, abilities, universe, firstAppearance,
                            image, type, nemesis);
                } 
        return null;
    }

}
