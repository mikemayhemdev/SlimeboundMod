/*    */ package slimebound.vfx;
/*    */ 
/*    */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;

/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */

/*    */
/*    */ public class TinyHatParticle extends com.megacrit.cardcrawl.vfx.AbstractGameEffect
/*    */ {
/*    */   private static final float DURATION = 0.75F;
/* 15 */   private static final float START_SCALE = 1.2F * com.megacrit.cardcrawl.core.Settings.scale;
/* 16 */   private float scale = .75F;
/*    */   private static int W;
/*    */   private Texture img;
/*    */   private float x;
    /*    */   private float px;
            public AbstractPlayer p;
/*    */   private float y;
/*    */
/* 22 */   public TinyHatParticle(AbstractPlayer p) {
            this.duration = 0.05F;
/* 23 */     this.img = ImageMaster.loadImage("SlimeboundImages/relics/tinybowlerhatinverted.png");
/* 24 */     W = img.getWidth();
            this.p = p;
            this.px = p.hb.cX;
/* 25 */     this.x = (p.hb.cX - W / 2.0F -22);
/* 26 */     this.y = ((p.hb.cY - W / 2.0F) + 50);
/* 27 */     this.renderBehind = false;


/*    */   }
/*    */   
/*    */   public void update()
/*    */ {
    /* 32 */


            // this.scale = Interpolation.pow2Out.apply(START_SCALE, 0.01F, this.duration);



/* 33 */     //this.duration -= Gdx.graphics.getDeltaTime();
/* 34 */    // if (this.duration < 0.0F) {
/* 35 */     //  this.isDone = true;
/*    */     //}
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void render(SpriteBatch sb, float x, float y) {}
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void render(SpriteBatch sb)
/*    */   {

                   // sb.setBlendFunction(770, 1);
                    /* 50 */     sb.setColor(new Color(1F, 1F, 1F, 2F));

                    /* 51 */     sb.draw(this.img, this.x, this.y, W / 2.0F, W / 2.0F, W, W, this.scale, this.scale, 0.0F, 0, 0, W, W, false, false);
                    /* 52 */     //sb.setBlendFunction(770, 771);

/* 49 */
/*    */   }
/*    */ }


/* Location:              C:\Users\Computer\IdeaProjects\lib\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\SlimeIntentParticle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */