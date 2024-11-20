package model;

import control.CollisionChecker;
import control.InputHandler;
import view.GameWindow;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public abstract class Ghost extends GameEntity {

    public static final int TILE_SIZE = 24;
    public static final int DEFAULT_SPEED = 1;
    private static final int CHASE_MODE_DELAY = 15000; // 15 secondes en millisecondes
    private static final int ESCAPE_MODE_DURATION = 10000; // 10 secondes en millisecondes

    String name;
    Point basePos;
    Point target;
    PacMan pacMan;
    public BufferedImage image;
    Point shortPath;
    Point prevPos;
    Map<String, Point> mapDirection = new HashMap<>();
    public Map<Direction, BufferedImage> directionImage = new HashMap<>();
    private Timer behaviorTimer;
    private Timer escapeTimer;
    private GhostMode currentMode;

    private enum GhostMode {
        SCATTER,
        CHASE,
        ESCAPE
    }

    Point goodDirection;


    public Ghost(String name, Point basePos) {
        cChecker = new CollisionChecker(GameWindow.getInstance());
        this.name = name;
        this.basePos = basePos;
        this.pos = basePos;
        loadPlayerImage();
        target = new Point();
        this.pacMan = PacMan.getInstance(GameWindow.getInstance(), InputHandler.getInstance());
        prevPos = pos;
        goodDirection = new Point();
        setSolidArea();
        setMapDirection();
        collisionOn = true;
        direction = Direction.DOWN;
        speed = DEFAULT_SPEED;

        // Démarre en mode scatter et programme le passage en mode chase
        startBehaviorTimer();
    }

    private void startBehaviorTimer() {
        currentMode = GhostMode.SCATTER;
        scatterMode();

        behaviorTimer = new Timer();
        behaviorTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (currentMode != GhostMode.ESCAPE) {
                    currentMode = GhostMode.CHASE;
                    chaseMode();
                }
            }
        }, CHASE_MODE_DELAY);
    }

    public void resetBehavior() {
        if (behaviorTimer != null) {
            behaviorTimer.cancel();
        }
        startBehaviorTimer();
    }

    public void startEscapeMode() {
        // Annule le timer d'escape précédent s'il existe
        if (escapeTimer != null) {
            escapeTimer.cancel();
        }

        // Active le mode escape
        currentMode = GhostMode.ESCAPE;
        shortPath=null;
        escapeMode();
        speed = DEFAULT_SPEED ; // Ralentit le fantôme

        // Crée un nouveau timer pour revenir au mode précédent
        escapeTimer = new Timer();
        escapeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                speed = DEFAULT_SPEED;
                if (behaviorTimer != null && behaviorTimer.purge() > 0) {
                    currentMode = GhostMode.CHASE;
                    chaseMode();
                } else {
                    currentMode = GhostMode.SCATTER;
                    scatterMode();
                }
            }
        }, ESCAPE_MODE_DURATION);
    }

    @Override
    protected void finalize() throws Throwable {
        if (behaviorTimer != null) {
            behaviorTimer.cancel();
        }
        if (escapeTimer != null) {
            escapeTimer.cancel();
        }
        super.finalize();
    }

    public boolean isVulnerable() {
        return currentMode == GhostMode.ESCAPE;
    }

    public void setSolidArea() {
        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 24;
        solidArea.height = 24;

    }

    private void loadPlayerImage() {
        try {
            for (Direction dir : Direction.values()) {
                BufferedImage image;

                image = ImageIO.read(getClass().getResourceAsStream("/ghosts/" + name + "_" + dir.name().toLowerCase() + ".png"));
                directionImage.put(dir, image);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setMapDirection() {
        mapDirection.put("UP", new Point(0, -1));
        mapDirection.put("LEFT", new Point(-1, 0));
        mapDirection.put("DOWN", new Point(0, 1));
        mapDirection.put("RIGHT", new Point(1, 0));
    }

    public abstract void chaseMode();

    public abstract void scatterMode();

    private void escapeMode() {
        // Inverse la direction (rotation 180 degrés) uniquement au début du mode escape
        if (shortPath == null) {
            switch (direction) {
                case UP -> direction = Direction.DOWN;
                case DOWN -> direction = Direction.UP;
                case LEFT -> direction = Direction.RIGHT;
                case RIGHT -> direction = Direction.LEFT;
            }
        }

        // Change la direction aléatoirement
        if (Math.random() < 0.05 || shortPath == null) { // 5% de chance ou si pas de destination
            Direction[] possibleDirections = Direction.values();
            direction = possibleDirections[new Random().nextInt(possibleDirections.length)];

            // Trouve une nouvelle position valide sur la map
            int randomX = new Random().nextInt(0, 24);
            int randomY = new Random().nextInt(4, 28);
            Point newTarget = new Point(randomX * GameWindow.TILE_SIZE, randomY * GameWindow.TILE_SIZE);

            // Vérifie si la position est accessible
            if (TileManager.mapTileNum[randomX][ randomY]==1) {
                shortPath = newTarget;
            }
        }

        // Si pas de chemin valide, continue dans la direction actuelle
        if (shortPath == null) {
            move();
        } else {
            // Met à jour la direction vers la destination
            updateDirection();
        }
    }

    public void eatenMode() {
        target = basePos;
    }

    public void findShortPath() {
        // Ne cherche une nouvelle direction qu'aux intersections de la grille
        if (pos.x % TILE_SIZE == 0 && pos.y % TILE_SIZE == 0) {
            List<Point> directions = toPoint();
            if (!directions.isEmpty()) {
                // Trouve la direction qui rapproche le plus de la cible
                Point bestDirection = directions.get(0);
                double minDistance = bestDirection.distance(target);

                for (Point newPos : directions) {
                    double distance = newPos.distance(target);
                    if (distance < minDistance) {
                        bestDirection = newPos;
                        minDistance = distance;
                    }
                }

                shortPath = bestDirection;
                // Met à jour la direction en fonction de la nouvelle destination
                updateDirection();
            }
        } else {
            // Continue dans la même direction
            continueCurrentDirection();
        }
    }

    private void continueCurrentDirection() {
        switch (direction) {
            case UP -> shortPath = new Point(pos.x, pos.y - speed);
            case DOWN -> shortPath = new Point(pos.x, pos.y + speed);
            case LEFT -> shortPath = new Point(pos.x - speed, pos.y);
            case RIGHT -> shortPath = new Point(pos.x + speed, pos.y);
        }
    }

    private void updateDirection() {
        if (shortPath.x < pos.x) {
            direction = Direction.LEFT;
        } else if (shortPath.x > pos.x) {
            direction = Direction.RIGHT;
        } else if (shortPath.y < pos.y) {
            direction = Direction.UP;
        } else if (shortPath.y > pos.y) {
            direction = Direction.DOWN;
        }
    }

    public List<Point> toPoint() {
        List<Point> trueDirection = new ArrayList<>();
        List<String> direction = cChecker.getDirection(this);
        for (int i = 0; i < direction.size(); i++) {
            Point point = new Point(mapDirection.get(direction.get(i)).x + pos.x, mapDirection.get(direction.get(i)).y + pos.y); //mapDirection.get(direction.get(i));
            trueDirection.add(point);
        }
        return trueDirection;
    }

    public String getCurrentDirection() {
        if (direction == Direction.RIGHT) {
            return "RIGHT";
        } else if (direction == Direction.LEFT) {
            return "LEFT";
        } else if (direction == Direction.DOWN) {
            return "DOWN";
        } else if (direction == Direction.UP) {
            return "UP";
        } else {
            return "UP";
        }

    }

    public void move() {
        findShortPath();
        pos.setLocation(shortPath);
    }


    public int eaten() {
        return 0;
    }

}
