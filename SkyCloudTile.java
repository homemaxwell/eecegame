public class SkyCloudTile extends Tile {
    public SkyCloudTile (int id) {
        super(Assets.sky, id);
    }

    @Override
    public boolean isSolid()
    {
        return false;
    }
}