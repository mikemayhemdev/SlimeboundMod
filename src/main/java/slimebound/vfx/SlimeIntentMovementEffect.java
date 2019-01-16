/*    */ package slimebound.vfx;
/*    */ 
/*    */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.orbs.AbstractOrb;

/*    */
/*    */
/*    */
/*    */

/*    */
/*    */ public class SlimeIntentMovementEffect extends com.megacrit.cardcrawl.vfx.AbstractGameEffect
/*    */ {
/*    */   private static final float DURATION = .5F;
/*    */   private static final float FLASH_INTERVAL = 0.17F;
/* 13 */   private float intervalTimer = 0.0F;
/*    */   private AbstractOrb o;
/*    */
/*    */   public SlimeIntentMovementEffect(AbstractOrb o) {
/* 18 */     this.duration = .5F;
/* 20 */     this.o = o;
/*    */   }
/*    */   
/*    */   public void update()
/*    */   {
/* 25 */     this.intervalTimer -= Gdx.graphics.getDeltaTime();
/* 26 */
/* 29 */       com.megacrit.cardcrawl.dungeons.AbstractDungeon.effectsQueue.add(new SlimeIntentMovement(this.o));


/* 33 */     this.duration -= Gdx.graphics.getDeltaTime();
/*    */
/* 35 */     if (this.duration < 0.0F) {
               /*    */
               /*    */

/* 36 */       this.isDone = true;
/*    */     }
/*    */   }
/*    */   
/*    */   public void render(SpriteBatch sb) {}
/*    */ }


/* Location:              C:\Users\Computer\IdeaProjects\lib\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\SlimeIntentEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */