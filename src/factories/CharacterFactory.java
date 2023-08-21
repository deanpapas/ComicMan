package factories;

import model.Character;
import model.Hero;
import model.Villain;

public class CharacterFactory {

    public Character newCharacter(String type, String name, String[] abilities,
            String universe, String firstAppearance, String description,
            String image, String identity,String[] villains, String[] allies,
            String[] teams, String nemesis) {
                if (type.equalsIgnoreCase("hero")) {
                    return new Hero(name, abilities, universe, firstAppearance, description,
                            image, identity, villains, allies, teams);
                } else if (type.equalsIgnoreCase("villain")) {
                    return new Villain(name, abilities, universe, firstAppearance, description,
                            image, nemesis);
                }
        return null;
    }

}
