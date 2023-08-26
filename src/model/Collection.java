package model;

public class Collection {

    private String name;
    private Entry[] entries;


    public Collection(String name, Entry[] entries) {
        this.name = name;
        this.entries = entries;
    }

    public String getName() {
        return name;
    }

    public Entry[] getEntries() {
        return entries;
    }

    public void setEntries(Entry[] entries) {
        this.entries = entries;
    }

    
}
