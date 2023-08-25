package model;

public abstract class Character {

    private String name;
    private String[] abilities;
    private String universe;
    private String firstAppearance;
    private String image;
    private String affinity;

    public Character(String name, String[] abilities, String universe, String firstAppearance,
            String image, String affinity) {
        this.name = name;
        this.abilities = abilities;
        this.universe = universe;
        this.firstAppearance = firstAppearance;
        this.image = image;
        this.affinity = affinity;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getAbilities() {
        return abilities;
    }

    public void setAbilities(String[] abilities) {
        this.abilities = abilities;
    }

    public String getUniverse() {
        return universe;
    }

    public void setUniverse(String universe) {
        this.universe = universe;
    }

    public String getFirstAppearance() {
        return firstAppearance;
    }

    public void setFirstAppearance(String firstAppearance) {
        this.firstAppearance = firstAppearance;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAffinity() {
        return affinity;
    }

    public void setAffinity(String affinity) {
        this.affinity = affinity;
    }

    public String getCatchPhrase() {
        return "Hello, I'm " + name + ".";
    }

}
