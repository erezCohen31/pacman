package model;

public class Fruit extends GameEntity {
    int points;
    boolean isEaten;

void appear(){}
void disappear(){}



    @Override
    public int eaten() {
        return points;
    }
}
