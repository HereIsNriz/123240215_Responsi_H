package model;

public abstract class Entitas {
    protected int id;

    public Entitas() {
        
    }
    public Entitas(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}