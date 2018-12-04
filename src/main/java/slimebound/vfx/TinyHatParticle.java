package slimebound.vfx;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.ImageMaster;


public class TinyHatParticle extends com.megacrit.cardcrawl.vfx.AbstractGameEffect {
    private static final float DURATION = 0.75F;
    private static final float START_SCALE = 1.2F * com.megacrit.cardcrawl.core.Settings.scale;
    private float scale = .75F;
    private static int W;
    private Texture img;
    private float x;
    private float px;
    public AbstractPlayer p;
    private float y;

    public TinyHatParticle(AbstractPlayer p) {
        this.duration = 0.05F;
        this.img = ImageMaster.loadImage("SlimeboundImages/relics/tinybowlerhatinverted.png");
        W = img.getWidth();
        this.p = p;
        this.px = p.hb.cX;
        this.x = (p.hb.cX - W / 2.0F - 22);
        this.y = ((p.hb.cY - W / 2.0F) + 50);
        this.renderBehind = false;


    }

    public void update() {


    }


    public void render(SpriteBatch sb, float x, float y) {
    }


    public void render(SpriteBatch sb) {


        sb.setColor(new Color(1F, 1F, 1F, 2F));

        sb.draw(this.img, this.x, this.y, W / 2.0F, W / 2.0F, W, W, this.scale, this.scale, 0.0F, 0, 0, W, W, false, false);


    }
}


