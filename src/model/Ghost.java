package model;

public class Ghost extends GameEntity {
    String state;

    void chasePacMan(PacMan pacMan) {
    }

    void flee() {
    }

    @Override
    public int eaten() {
        return 0;
    }
}
