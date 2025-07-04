package model;

import control.CollisionChecker;
import control.InputHandler;
import utils.ImageLoader;
import view.GameWindow;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public abstract class Ghost extends GameEntity {

    public static final int TILE_SIZE = 24;
    public static final int DEFAULT_SPEED = 1;
    private static final int SCATTER_MODE_DURATION = 7000; // 7 secondes en millisecondes
    private static final int CHASE_MODE_DURATION = 20000;  // 20 secondes en millisecondes
    private static final int ESCAPE_MODE_DURATION = 20000; // 20 secondes en millisecondes

    String name;
    public int point = 200;
     public boolean isPoint;
    protected final Point BASE_POS;
    protected Point target;
    protected PacMan pacMan;
    Point shortPath;
    Point prevPos;
    Map<String, Point> mapDirection = new HashMap<>();
    public Map<Direction, BufferedImage> directionImage = new HashMap<>();
    private Timer behaviorTimer;
    private Timer escapeTimer;
    private GhostMode currentMode;
    public BufferedImage afraidImage;
    public BufferedImage eyeImage;
    boolean collisionPacMan;
    public boolean escapeMode;
    public boolean isEaten = false;

    private enum GhostMode {
        SCATTER,
        CHASE,
        ESCAPE
    }

    Point goodDirection;

    public Ghost(String name, int posX, int posY) {
        cChecker = new CollisionChecker(GameWindow.getInstance());
        this.name = name;
        this.BASE_POS = new Point(posX, posY);
        this.pos = new Point(posX, posY);
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

        // Démarre en mode scatter et programme l'alternance entre scatter et chase
        startBehaviorTimer();
    }

    private void startBehaviorTimer() {
        currentMode = GhostMode.SCATTER;
        scatterMode();

        behaviorTimer = new Timer();
        // Programme l'alternance entre scatter et chase
        behaviorTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (currentMode != GhostMode.ESCAPE && !isEaten) {
                    if (currentMode == GhostMode.SCATTER) {
                        currentMode = GhostMode.CHASE;
                        chaseMode();
                    } else {
                        currentMode = GhostMode.SCATTER;
                        scatterMode();
                    }
                }
            }
        }, SCATTER_MODE_DURATION, SCATTER_MODE_DURATION + CHASE_MODE_DURATION);
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
        escapeMode = true;
        isPoint=true;
        escapeMode();

        // Crée un nouveau timer pour revenir au mode précédent
        escapeTimer = new Timer();
        escapeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                escapeMode = false;
                currentMode = GhostMode.CHASE;
                chaseMode();

                // Démarre un nouveau timer pour alterner entre chase et scatter
                if (behaviorTimer != null) {
                    behaviorTimer.cancel();
                }
                behaviorTimer = new Timer();
                behaviorTimer.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        if (currentMode != GhostMode.ESCAPE && !isEaten) {
                            if (currentMode == GhostMode.CHASE) {
                                currentMode = GhostMode.SCATTER;
                                scatterMode();
                            } else {
                                currentMode = GhostMode.CHASE;
                                chaseMode();
                            }
                        }
                    }
                }, CHASE_MODE_DURATION, SCATTER_MODE_DURATION + CHASE_MODE_DURATION);
            }
        }, ESCAPE_MODE_DURATION);
    }

    @Override protected void finalize() throws Throwable {
        if (behaviorTimer != null) {
            behaviorTimer.cancel();
        }
        if (escapeTimer != null) {
            escapeTimer.cancel();
        }
        super.finalize();
    }

    public void setSolidArea() {
        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 24;
        solidArea.height = 24;

    }

    private void loadPlayerImage() {
        ImageLoader loader = ImageLoader.getInstance();

            for (Direction dir : Direction.values()) {
                BufferedImage image;

                image = loader.loadImage("/ghosts/" + name + "_" + dir.name().toLowerCase() + ".png");
                directionImage.put(dir, image);
            }
            afraidImage = loader.loadImage("/ghosts/afraid.png");
            eyeImage = loader.loadImage("/ghosts/eyeGhost.png");


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
        switch (direction) {
            case UP -> direction = Direction.DOWN;
            case DOWN -> direction = Direction.UP;
            case LEFT -> direction = Direction.RIGHT;
            case RIGHT -> direction = Direction.LEFT;
        }

        // Change la direction aléatoirement
        if (Math.random() < 0.05 || shortPath == null) { // 5% de chance ou si pas de destination
            List<Point> validDirections = toPoint(); // Obtenir les directions valides

            if (!validDirections.isEmpty()) {
                // Choisir une direction aléatoire parmi les directions valides
                int randomIndex = new Random().nextInt(validDirections.size());
                shortPath = validDirections.get(randomIndex);
                updateDirection();
            } else {
                // Si aucune direction valide, continuer dans la direction actuelle
                continueCurrentDirection();
            }
        }

        // Mettre à jour la position
        if (shortPath != null) {
            // Vérifier si la nouvelle position est valide
            if (!cChecker.checkTile(this)) {
                move();
            } else {
                // Si collision détectée, trouver une nouvelle direction
                List<Point> validDirections = toPoint();
                if (!validDirections.isEmpty()) {
                    int randomIndex = new Random().nextInt(validDirections.size());
                    shortPath = validDirections.get(randomIndex);
                    updateDirection();
                }
            }
        }
    }

    public void eatenMode() {
        isEaten = true;
        speed = DEFAULT_SPEED * 8; // Move faster when eaten
        target.setLocation(BASE_POS);

    }

    public void findShortPath() {
        if (currentMode == GhostMode.CHASE) {
            chaseMode();
        }
        if (pos.x % TILE_SIZE == 0 && pos.y % TILE_SIZE == 0 || isEaten) {
            List<Point> directions = toPoint();
            if (!directions.isEmpty()) {
                // Trouve la direction qui rapproche le plus de la cible
                Point bestDirection = directions.getFirst();
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
        for (String s : direction) {
            Point point = new Point(mapDirection.get(s).x + pos.x, mapDirection.get(s).y + pos.y); //mapDirection.get(direction.get(i));
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
        collisionPacMan = false;
        findShortPath();
        pos.setLocation(shortPath);
        checkCollisions();

        // Check if eaten and reached base
        if (isEaten && pos.equals(BASE_POS)) {
            escapeMode = false;
            isEaten = false;
            speed = DEFAULT_SPEED;

            resetBehavior(); // Reset to same mode as other ghosts
        } else if (collisionPacMan && escapeMode) {

            eatenMode();
        }
    }

    private void checkCollisions() {
        collisionPacMan = cChecker.checkGhostCollision(this, pacMan);
    }


}
