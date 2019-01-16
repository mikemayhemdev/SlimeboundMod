/*     */ package slimebound.vfx;
/*     */ 
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.Texture;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.combat.WaterSplashParticleEffect;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ public class SlimeWaterDropEffect extends com.megacrit.cardcrawl.vfx.AbstractGameEffect
/*     */ {
/*     */   private float x;
/*     */   private float y;
/*  16 */   private static Texture[] imgs = null;
/*  17 */   private int frame = 0;
/*  18 */   private float animTimer = 0.1F;
/*     */   private static final int W = 64;
/*     */   
/*     */   public SlimeWaterDropEffect(float x, float y) {
/*  22 */     this.x = x;
/*  23 */     this.y = y;
/*     */     
/*  25 */     if (imgs == null) {
/*  26 */       imgs = new Texture[6];
/*  27 */       imgs[0] = ImageMaster.loadImage("images/vfx/water_drop/drop1.png");
/*  28 */       imgs[1] = ImageMaster.loadImage("images/vfx/water_drop/drop2.png");
/*  29 */       imgs[2] = ImageMaster.loadImage("images/vfx/water_drop/drop3.png");
/*  30 */       imgs[3] = ImageMaster.loadImage("images/vfx/water_drop/drop4.png");
/*  31 */       imgs[4] = ImageMaster.loadImage("images/vfx/water_drop/drop5.png");
/*  32 */       imgs[5] = ImageMaster.loadImage("images/vfx/water_drop/drop6.png");
/*     */     }
/*     */     
/*  35 */     this.frame = 0;
/*  36 */     this.scale = (MathUtils.random(2.5F, 3.0F) * Settings.scale);
/*  37 */     this.rotation = 0.0F;
/*  38 */     this.scale *= Settings.scale;
/*  39 */     this.color = new Color(0.5F, 0.8F, 0.05F, 0.0F);
/*     */   }
/*     */   
/*     */   public void update() {
/*  43 */     this.color.a = com.megacrit.cardcrawl.helpers.MathHelper.fadeLerpSnap(this.color.a, 1.0F);
/*  44 */     this.animTimer -= Gdx.graphics.getDeltaTime();
/*  45 */     if (this.animTimer < 0.0F) {
/*  46 */       this.animTimer += 0.1F;
/*  47 */       this.frame += 1;
/*     */       
/*  49 */       if (this.frame == 3) {
/*  50 */         for (int i = 0; i < 3; i++) {
/*  51 */           com.megacrit.cardcrawl.dungeons.AbstractDungeon.effectsQueue.add(new SlimeDeathParticleEffect(this.x, this.y,new Color(0.05F,0.8F,0.05F,1F)));
/*     */         }
/*     */       }
/*     */       
/*  55 */       if (this.frame > 5) {
/*  56 */         this.frame = 5;
/*  57 */         this.isDone = true;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb) {
/*  63 */     sb.setColor(this.color);
/*  64 */     switch (this.frame) {
/*     */     case 0: 
/*  66 */       sb.draw(imgs[0], this.x - 32.0F, this.y - 32.0F + 40.0F * Settings.scale, 32.0F, 32.0F, 64.0F, 64.0F, this.scale, this.scale, this.rotation, 0, 0, 64, 64, false, false);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  83 */       break;
/*     */     case 1: 
/*  85 */       sb.draw(imgs[1], this.x - 32.0F, this.y - 32.0F + 20.0F * Settings.scale, 32.0F, 32.0F, 64.0F, 64.0F, this.scale, this.scale, this.rotation, 0, 0, 64, 64, false, false);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 102 */       break;
/*     */     case 2: 
/* 104 */       sb.draw(imgs[2], this.x - 32.0F, this.y - 32.0F + 10.0F * Settings.scale, 32.0F, 32.0F, 64.0F, 64.0F, this.scale, this.scale, this.rotation, 0, 0, 64, 64, false, false);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 121 */       break;
/*     */     case 3: 
/* 123 */       sb.draw(imgs[3], this.x - 32.0F, this.y - 32.0F, 32.0F, 32.0F, 64.0F, 64.0F, this.scale, this.scale, this.rotation, 0, 0, 64, 64, false, false);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 140 */       break;
/*     */     case 4: 
/* 142 */       sb.draw(imgs[4], this.x - 32.0F, this.y - 32.0F, 32.0F, 32.0F, 64.0F, 64.0F, this.scale, this.scale, this.rotation, 0, 0, 64, 64, false, false);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 159 */       break;
/*     */     case 5: 
/* 161 */       sb.draw(imgs[5], this.x - 32.0F, this.y - 32.0F, 32.0F, 32.0F, 64.0F, 64.0F, this.scale, this.scale, this.rotation, 0, 0, 64, 64, false, false);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 178 */       break;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Computer\IdeaProjects\lib\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\SlimeWaterDropEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */