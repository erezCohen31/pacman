package utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ImageLoader {
    private static ImageLoader instance;
    private final Map<String, BufferedImage> imageCache;
    
    private ImageLoader() {
        imageCache = new HashMap<>();
    }
    
    public static ImageLoader getInstance() {
        if (instance == null) {
            instance = new ImageLoader();
        }
        return instance;
    }
    
    public BufferedImage loadImage(String path) {
        // Vérifie si l'image est déjà en cache
        if (imageCache.containsKey(path)) {
            return imageCache.get(path);
        }
        
        try {
            // Essaie d'abord de charger depuis les ressources
            BufferedImage image = ImageIO.read(getClass().getResourceAsStream(path));
            if (image == null) {
                // Si pas trouvé dans les ressources, essaie de charger depuis le système de fichiers
                image = ImageIO.read(new File(path));
            }
            // Met l'image en cache
            imageCache.put(path, image);
            return image;
        } catch (IOException e) {
            System.err.println("Erreur lors du chargement de l'image: " + path);
            e.printStackTrace();
            return null;
        }
    }
    
    public void clearCache() {
        imageCache.clear();
    }
}
