package model;

public class Pellet extends GameEntity {
    int points;

    @Override
    public int eaten() {
        return points;
    }
}
