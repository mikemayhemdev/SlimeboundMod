/*    */ package slimebound.vfx;
/*    */ 
/*    */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.orbs.AbstractOrb;

/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */

/*    */
/*    */ public class SlimeIntentMovement extends com.megacrit.cardcrawl.vfx.AbstractGameEffect
/*    */ {

    /*    */   private float ox;
            public AbstractOrb o;

/*    */
/* 22 */   public SlimeIntentMovement(AbstractOrb o) {
            this.duration = 0.5F;
            this.o = o;
            this.ox = o.cX;

/* 27 */     this.renderBehind = true;
/*    */   }
/*    */
/*    */   public void update()
/*    */ {
    /* 32 */

           o.cX = Interpolation.pow2.apply(ox, ox + 20, this.duration );



/* 33 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 34 */     if (this.duration < 0.0F) {
/* 35 */       this.isDone = true;
/*    */     }
/*    */   }
/*    */   
/*    */

    public void render(SpriteBatch sb, float x, float y) {}
    /*    */
    /*    */
    /*    */
    /*    */
    /*    */   public void render(SpriteBatch sb)
    /*    */   {
        sb.setBlendFunction(770, 1);
        /* 50 */      sb.setBlendFunction(770, 771);
        /* 49 */
        /*    */   }
/*    */ }


/* Location:              C:\Users\Computer\IdeaProjects\lib\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\SlimeIntentParticle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */