import java.awt.image.BufferedImage;

public class Assets
{
    private static final int width = 32, height = 32;

    public static BufferedImage dirt, grass, sky, sky_cloud, sky_crow;
    public static BufferedImage[] player_right, player_left, player_jump;

    public static void init()
    {
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/res/textures/sheet.png"));
        SpriteSheet character = new SpriteSheet(ImageLoader.loadImage("/res/textures/characters.png"));

        player_right = new BufferedImage[5];
        player_left = new BufferedImage[5];
        player_jump = new BufferedImage[3];

        //player moving right animation
        player_right[0] = character.crop(0, 0, 103, 418);
        player_right[1] = character.crop(114, 0, 251, 411);
        player_right[2] = character.crop(0, 0, 103, 418);
        player_right[3] = character.crop(374, 0, 257, 414);
        player_right[4] = character.crop(0, 0, 103, 418);
        //player moving left animation
        player_left[0] = character.crop(0, 473, 103, 417);
        player_left[1] = character.crop(114, 478, 255, 414);
        player_left[2] = character.crop(0, 473, 103, 417);
        player_left[3] = character.crop(392, 477, 253, 415);
        player_left[4] = character.crop(0, 473, 103, 417);
        //player jumping right
        player_jump[0] = character.crop(665, 0, 203, 422);
        //player jumping left
        player_jump[1] = character.crop(698, 481, 202, 426);

        //textures
        dirt = sheet.crop(width * 2, 0, width, height);
        grass = sheet.crop(width, 0, width, height);
        sky = sheet.crop(width * 3, 0, width, height);
        sky_cloud = sheet.crop(width, height, width, height);
        sky_crow = sheet.crop(width * 2, height, width, height);
    }
}
