//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package slimebound.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

import java.util.ArrayList;

public class SlimeSpawnProjectile extends AbstractGameEffect {
    private static Texture img;
    private float sX;
    private float sY;
    private float cX;
    private float cY;
    private float dX;
    private float dY;
    private float yOffset;
    private float bounceHeight;
    private static final float DUR = 0.25F;
    private boolean playedSfx = false;
    private boolean rain = false;
    private float height = 100f;
    private AbstractOrb o;

    private ArrayList<Vector2> previousPos = new ArrayList();

    public SlimeSpawnProjectile(float srcX, float srcY, AbstractOrb o, float scale, Color color) {
        if (img == null) {
            img = ImageMaster.loadImage("SlimeboundImages/vfx/slimeballWhite.png");
        }

        this.sX = srcX;
        this.sY = srcY;
        this.cX = this.sX;
        this.cY = this.sY;
        this.o=o;
        this.scale = scale;
        this.rotation = 0.0F;
        this.duration = .5F;
        this.startingDuration = .5F;
        this.color = color;


    }

    public void update() {

        this.cX = Interpolation.linear.apply(this.o.cX, this.sX, this.duration / this.startingDuration);
        this.cY = Interpolation.linear.apply(this.o.cY, this.sY, this.duration / this.startingDuration);


        if (this.o.cX > this.sX) {
            this.rotation -= Gdx.graphics.getDeltaTime() * 300.0F;
        } else {
            this.rotation += Gdx.graphics.getDeltaTime() * 300.0F;
        }

        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration < 0.0F) {

            this.isDone = true;
        }

    }

    public void render(SpriteBatch sb) {
        sb.setBlendFunction(770, 1);

        sb.setColor(this.color);
        sb.draw(img, this.cX - (float)(img.getWidth() / 2), this.cY - (float)(img.getHeight() / 2) + this.yOffset, (float)img.getWidth() / 2.0F, (float)img.getHeight() / 2.0F, (float)img.getWidth(), (float)img.getHeight(), this.scale, this.scale, this.rotation, 0, 0, 38, 38, false, false);
        //sb.draw(img, this.cX - (float)(img.getWidth() / 2), this.cY - (float)(img.getHeight() / 2) + this.yOffset, (float)img.getWidth() / 2.0F, (float)img.getHeight() / 2.0F, (float)img.getWidth(), (float)img.getHeight(), this.scale, this.scale, this.rotation, 0, 0, 38, 38, false, false);
        sb.setBlendFunction(770, 771);
    }

    public void dispose() {
    }
}
