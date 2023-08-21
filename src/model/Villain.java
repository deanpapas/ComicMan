package model;

public class Villain extends Character{

    private String nemesis;

    public Villain(String name, String[] abilities, String universe, String firstAppearance, String description,
            String image, String nemesis) {
        super(name, abilities, universe, firstAppearance, description, image);
        this.nemesis = nemesis;
    }
    

    public String getNemesis() {
        return nemesis;
    }

    public void setNemesis(String nemesis) {
        this.nemesis = nemesis;
    }
    
}