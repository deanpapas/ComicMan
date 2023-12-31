package model;

public class Hero extends Character {

    private String identity;
    private String[] villains;
    private String[] allies;
    private String[] teams;

    public Hero(String name, String[] abilities, String universe,
            String firstAppearance, String image, String type,
            String identity, String[] villains, String[] allies,
            String[] teams) {

        super(name, abilities, universe, firstAppearance, image, type);
        this.identity = identity;
        this.villains = villains;
        this.allies = allies;
        this.teams = teams;
    }

    public String getAffinity() {
        return "Hero";
    }
    
    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String[] getVillains() {
        return villains;
    }

    public void setVillains(String[] villains) {
        this.villains = villains;
    }

    public String[] getAllies() {
        return allies;
    }

    public void setAllies(String[] allies) {
        this.allies = allies;
    }

    public String[] getTeams() {
        return teams;
    }

    public void setTeams(String[] teams) {
        this.teams = teams;
    }

    public String getCatchPhrase() {
        return "Hello, I'm the Hero they call " + getName() + ".";
    }

}
