package slimebound.orbs;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.vfx.BobEffect;
import slimebound.actions.CheckForSixHexAction;
import slimebound.vfx.SlimeFlareEffect;


public class HexSlime
        extends SpawnedSlime {

    private BobEffect effect = new BobEffect(2.0F);
    private float activateTimer;
    public boolean activated = false;
    public boolean hidden = false;
    public boolean playedSfx = false;
    private Color color;
    private float x;
    private float y;
    private float particleTimer = 0.0F;
    private static final float PARTICLE_INTERVAL = 0.06F;


    public HexSlime() {
        super("HexSlime", 0,0, false, new Color(.36F, .55F, .85F, 1), SlimeFlareEffect.OrbFlareColor.HEX, new Texture("SlimeboundImages/orbs/sleep.png"), "SlimeboundImages/orbs/hex.png");
        this.x = (x * Settings.scale + MathUtils.random(-10.0F, 10.0F) * Settings.scale);
        this.y = (y * Settings.scale + MathUtils.random(-10.0F, 10.0F) * Settings.scale);
        this.color = Color.CHARTREUSE.cpy();
        this.color.a = 0.0F;
        this.activated = true;
        this.activated = true;
    }


    public void updateDescription() {

        this.description = this.descriptions[0];
    }

    @Override
    public void applyFocus() {

    }



    public void activateEffectUnique() {


    }

    public void update() {
        super.update();

        this.activateTimer -= Gdx.graphics.getDeltaTime();

        if (this.activateTimer < 0.0F) {


            this.color.a = MathHelper.fadeLerpSnap(this.color.a, 1.0F);

            this.effect.update();

            this.effect.update();

            this.particleTimer -= Gdx.graphics.getDeltaTime();

            if (this.particleTimer < 0.0F) {

                AbstractDungeon.effectList.add(new com.megacrit.cardcrawl.vfx.GhostlyWeakFireEffect(this.cX, this.cY));


                this.particleTimer = 0.06F;

            }

        } else {

            this.effect.update();

            this.particleTimer -= Gdx.graphics.getDeltaTime();

            if (this.particleTimer < 0.0F) {

                AbstractDungeon.effectList.add(new com.megacrit.cardcrawl.vfx.GhostlyWeakFireEffect(this.cX, this.cY));


                this.particleTimer = 0.06F;

            }

        }
    }


    public AbstractOrb makeCopy() {
        return new HexSlime();
    }
}


