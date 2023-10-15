package utilz;

import entities.Crabby;
import main.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static utilz.Constans.EnemyConstans.*;

public class LoadSave {

    public static final String PLAYER_ATLAS = "res/Yagi_sprites.png";
    public static final String LEVEL_ATLAS = "res/outside_sprites.png";
    public static final String MENU_BUTTONS = "res/button_atlas.png";
//  public static final String LEVEL_ONE_DATA = "res/level_one_data.png";
    public static final String LEVEL_ONE_DATA = "res/level_one_data_long.png";
    public static final String MENU_BACKGROUND_IMAGE = "res/fondo.png";
    public static final String PLAYING_BACKGROUND_IMAGE = "res/playing_bg_img.png";
    public static final String BIG_CLOUDS = "res/big_clouds.png";
    public static final String SMALL_CLOUDS = "res/small_clouds.png";
    public static final String PAUSE_BACKGROUND_MENU = "res/fondo_ajustes+letras.png";
    public static final String SOUND_BUTTONS = "res/music.png";
    public static final String URM_BUTTONS = "res/urm_buttons.png";
    public static final String CRABBY_SPRITE = "res/crabby_sprite.png";

    public static BufferedImage GetSpriteAtlas(String fileName){
        BufferedImage img = null;
        InputStream is = LoadSave.class.getResourceAsStream("/" + fileName );

        try {
            img = ImageIO.read(is);

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try{
                is.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return img;
    }

    public static ArrayList<Crabby> GetCrabs(){
        BufferedImage img = GetSpriteAtlas(LEVEL_ONE_DATA);
        ArrayList<Crabby> list = new ArrayList<>();

        for (int j = 0; j < img.getHeight(); j++)
            for (int i = 0; i < img.getWidth(); i++){
                Color color = new Color(img.getRGB(i,j));
                int value = color.getGreen();
                if (value == CRABBY)
                    list.add(new Crabby(i*Game.TILES_SIZE, j*Game.TILES_SIZE));
            }
        return list;
    }

    public static int[][] GetLevelData(){

        BufferedImage img = GetSpriteAtlas(LEVEL_ONE_DATA);
        int[][] lvlData = new int[img.getHeight()][img.getWidth()];

        for (int j = 0; j < img.getHeight(); j++)
            for (int i = 0; i < img.getWidth(); i++){
                Color color = new Color(img.getRGB(i,j));
                int value = color.getRed();
                if (value >= 48)
                    value = 0;
                lvlData[j][i] = value;
            }
        return lvlData;
    }
}
