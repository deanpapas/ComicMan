package model;

public class Villain extends Character{

    private String nemesis;

    public Villain(String name, String[] abilities, String universe, String firstAppearance,
            String image, String type, String nemesis) {
        super(name, abilities, universe, firstAppearance, image, type);
        this.nemesis = nemesis;
    }
    
    public String getAffinity() {
        return "Villain";
    }
    
    public String getNemesis() {
        return nemesis;
    }

    public void setNemesis(String nemesis) {
        this.nemesis = nemesis;
    }

    public String getCatchPhrase() {
        return "Hello, I'm the Villain they call " + getName() + ".";
    }
    
}