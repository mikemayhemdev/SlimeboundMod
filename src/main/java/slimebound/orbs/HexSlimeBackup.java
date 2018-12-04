/*    */ package slimebound.orbs;
/*    */ 

/*    */

/*    */

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

/*    */
/*    */
/*    */
/*    */
/*    */

/*    */
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class HexSlimeBackup
/*    */   extends SpawnedSlime{

        private BobEffect effect = new BobEffect(2.0F);
        /*    */   private float activateTimer;
        /* 25 */   public boolean activated = false; public boolean hidden = false; public boolean playedSfx = false;
        /*    */   private Color color;
        /*    */   private float x;
        /* 28 */   private float y; private float particleTimer = 0.0F;
        /*    */   private static final float PARTICLE_INTERVAL = 0.06F;

/*    */
/*    */   public HexSlimeBackup()
/*    */   {
/* 25 */     super("HexSlime", 1, false, new Color(.36F,.55F,.85F,1),SlimeFlareEffect.OrbFlareColor.HEX,new Texture("SlimeboundImages/orbs/sleep.png"),"SlimeboundImages/orbs/hex.png");
                this.x = (x * Settings.scale + MathUtils.random(-10.0F, 10.0F) * Settings.scale);
    /* 33 */     this.y = (y * Settings.scale + MathUtils.random(-10.0F, 10.0F) * Settings.scale);
    /* 35 */     this.color = Color.CHARTREUSE.cpy();
    /* 36 */     this.color.a = 0.0F;
    this.activated = true;
    this.activated = true;
            }
/*    */
/*    */
/*    */    public void updateDescription()

/*     */ {
    this.description = this.descriptions[0] + 1 + this.descriptions[1];}

    @Override
    public void applyFocus() {

    }

    public void activateEffectUnique()
        /*    */   {
        /* 38 */


        AbstractDungeon.actionManager.addToBottom(new CheckForSixHexAction(AbstractDungeon.player));
        /*    */     }

    public void update()
        /*    */ {
        /* 66 */
        this.activateTimer -= Gdx.graphics.getDeltaTime();
        /* 67 */
        if (this.activateTimer < 0.0F) {
            /* 68 */
            /* 77 */
            this.color.a = MathHelper.fadeLerpSnap(this.color.a, 1.0F);
            /* 78 */
            this.effect.update();
            /* 79 */
            this.effect.update();
            /* 80 */
            this.particleTimer -= Gdx.graphics.getDeltaTime();
            /* 81 */
            if (this.particleTimer < 0.0F) {
                /* 82 */
                AbstractDungeon.effectList.add(new com.megacrit.cardcrawl.vfx.GhostlyWeakFireEffect(this.cX, this.cY));
                /*    */
                /* 84 */
                this.particleTimer = 0.06F;
                /*    */
            }
            /*    */
        }
        /*    */
        else {
            /* 88 */
            this.effect.update();
            /* 89 */
            this.particleTimer -= Gdx.graphics.getDeltaTime();
            /* 90 */
            if (this.particleTimer < 0.0F) {
                /* 91 */
                AbstractDungeon.effectList.add(new com.megacrit.cardcrawl.vfx.GhostlyWeakFireEffect(this.cX, this.cY));
                /*    */
                /* 93 */
                this.particleTimer = 0.06F;
                /*    */
            }
            /*    */
        }
    }
            /*    */

            /*    */
        /*    */
    /*    */
/*    */
/*    */
/*    */

/*    */
/*    */   
/*    */   public AbstractOrb makeCopy() {
/* 54 */     return new HexSlimeBackup();
/*    */   }
/*    */ }


/* Location:              C:\Program Files (x86)\Steam\steamapps\common\SlayTheSpire\mods\TheDisciple.jar!\chronomuncher\orbs\BronzeSlime.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */