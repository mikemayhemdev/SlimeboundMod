/*    */ package slimebound.vfx;
/*    */ 
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.Graphics;
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
/*    */ import com.badlogic.gdx.math.Interpolation;
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.badlogic.gdx.math.Vector2;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*    */ 
/*    */ public class SlimeDeathParticleEffect extends com.megacrit.cardcrawl.vfx.AbstractGameEffect
/*    */ {
/*    */   private TextureAtlas.AtlasRegion img;
/*    */   private float x;
/*    */   private float y;
/*    */   private float vX;
/*    */   private float vY;
/*    */   private float floor;
/*    */   
/*    */   public SlimeDeathParticleEffect(float x, float y, Color deathColor)
/*    */   {
/* 25 */     this.img = ImageMaster.DECK_GLOW_1;
/* 26 */     this.duration = MathUtils.random(0.5F, 1.0F);
/* 27 */     this.x = (x - this.img.packedWidth / 2 + MathUtils.random(-10.0F, 10.0F) * Settings.scale);
/* 28 */     this.y = (y - this.img.packedHeight / 2 - MathUtils.random(-10.0F, 10.0F) * Settings.scale);
/* 29 */     this.color = deathColor;
/* 30 */     this.color.a = 0.0F;
/* 31 */     this.scale = (MathUtils.random(1.5F, 3.5F) * Settings.scale);
/* 32 */     this.vX = (MathUtils.random(-120.0F, 120.0F) * Settings.scale);
/* 33 */     this.vY = (MathUtils.random(150.0F, 300.0F) * Settings.scale);
/* 34 */     this.floor = (y - 40.0F * Settings.scale);
/*    */   }
/*    */   
/*    */   public void update() {
/* 38 */     this.vY -= 1000.0F * Settings.scale * Gdx.graphics.getDeltaTime();
/* 39 */     this.x += this.vX * Gdx.graphics.getDeltaTime();
/* 40 */     this.y += this.vY * Gdx.graphics.getDeltaTime();
/* 41 */     Vector2 test = new Vector2(this.vX, this.vY);
/* 42 */     this.rotation = (test.angle() + 45.0F);
/*    */     
/* 44 */     this.scale -= Gdx.graphics.getDeltaTime() / 2.0F;
/*    */     
/* 46 */     if ((this.y < this.floor) && (this.vY < 0.0F) && 
/* 47 */       (this.duration > 0.2F)) {
/* 48 */       this.duration = 0.2F;
/*    */     }
/*    */     
/*    */ 
/* 52 */     if (this.duration < 0.2F) {
/* 53 */       this.color.a = Interpolation.fade.apply(0.0F, 1.0F, this.duration * 5.0F);
/*    */     } else {
/* 55 */       this.color.a = 1.0F;
/*    */     }
/*    */     
/* 58 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 59 */     if (this.duration < 0.0F) {
/* 60 */       this.isDone = true;
/*    */     }
/*    */   }
/*    */   
/*    */   public void render(SpriteBatch sb)
/*    */   {
/* 66 */     sb.setColor(this.color);
/* 67 */     sb.draw(this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale, this.scale * 0.54F, this.rotation);
/*    */   }
/*    */ }


/* Location:              C:\Users\Computer\IdeaProjects\lib\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\SlimeDeathParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */