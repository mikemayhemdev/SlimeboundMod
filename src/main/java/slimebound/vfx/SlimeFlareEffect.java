/*     */ package slimebound.vfx;
/*     */ 
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*     */ import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
/*     */ import com.badlogic.gdx.math.Interpolation;
/*     */ import com.badlogic.gdx.math.Interpolation.ElasticIn;
/*     */ import com.badlogic.gdx.math.Interpolation.PowOut;
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*     */ import com.megacrit.cardcrawl.orbs.AbstractOrb;
/*     */ 
/*     */ public class SlimeFlareEffect extends com.megacrit.cardcrawl.vfx.AbstractGameEffect
/*     */ {
/*     */   private static TextureAtlas.AtlasRegion outer;
/*     */   private static TextureAtlas.AtlasRegion inner;
/*     */   private float scaleY;
/*     */   private static final float DUR = 0.5F;
/*     */   private AbstractOrb orb;
/*     */   private OrbFlareColor flareColor;
/*     */   private Color color2;
/*     */   
/*     */   public static enum OrbFlareColor
/*     */   {
/*  28 */     POISON, AGGRESSIVE, SLIMING, LICKING, HEX, CULTIST, TORCHHEAD, BRONZE;
/*     */     
/*     */     private OrbFlareColor() {} }
/*     */   
/*  32 */   public SlimeFlareEffect(AbstractOrb orb, OrbFlareColor setColor) { if (outer == null) {
/*  33 */       outer = ImageMaster.vfxAtlas.findRegion("combat/orbFlareOuter");
/*  34 */       inner = ImageMaster.vfxAtlas.findRegion("combat/orbFlareInner");
/*     */     }
/*     */     
/*  37 */     this.orb = orb;
/*  38 */     this.renderBehind = true;
/*  39 */     this.duration = 0.5F;
/*  40 */     this.startingDuration = 0.5F;
/*  41 */     this.flareColor = setColor;
/*  42 */     setColor();
/*  43 */     this.scale = (2.0F * Settings.scale);
/*  44 */     this.scaleY = 0.0F;
/*     */   }
/*     */   
/*     */   private void setColor() {
/*  48 */     switch (this.flareColor) {
/*     */     case POISON:
/*  50 */       this.color = Color.FOREST.cpy();
/*  51 */       this.color2 = Color.LIGHT_GRAY.cpy();
/*  52 */       break;
        /*     */     case AGGRESSIVE:
/*  54 */       this.color = Color.DARK_GRAY.cpy();
/*  55 */       this.color2 = Color.LIGHT_GRAY.cpy();
/*  56 */       break;
        /*     */     case LICKING:
/*  58 */       this.color = Color.YELLOW.cpy();
/*  59 */       this.color2 = Color.WHITE.cpy();
/*  60 */       break;
        /*     */     case SLIMING:
/*  62 */       this.color = Color.MAROON.cpy();
/*  63 */       this.color2 = Color.CYAN.cpy();
/*  64 */       break;
        /*     */     case HEX:
            /*  62 */       this.color = Color.CORAL.cpy();
            /*  63 */       this.color2 = Color.CYAN.cpy();
            /*  64 */       break;
        /*     */     case TORCHHEAD:
            /*  62 */       this.color = Color.GOLDENROD.cpy();
            /*  63 */       this.color2 = Color.GREEN.cpy();
            /*  64 */       break;
        /*     */     case CULTIST:
            /*  62 */       this.color = Color.VIOLET.cpy();
            /*  63 */       this.color2 = Color.BROWN.cpy();
            /*  64 */       break;
        /*     */     case BRONZE:
            /*  62 */       this.color = Color.GOLDENROD.cpy();
            /*  63 */       this.color2 = Color.GOLD.cpy();
            /*  64 */       break;
/*     */     }
/*     */     
/*     */   }
/*     */   
/*     */ 
/*     */   public void update()
/*     */   {
/*  72 */     this.duration -= Gdx.graphics.getDeltaTime();
/*  73 */     if (this.duration < 0.0F) {
/*  74 */       this.duration = 0.0F;
/*  75 */       this.isDone = true;
/*     */     }
/*     */     
/*  78 */     this.scaleY = Interpolation.elasticIn.apply(2.2F, 0.8F, this.duration * 2.0F);
/*  79 */     this.scale = Interpolation.elasticIn.apply(2.1F, 1.9F, this.duration * 2.0F);
/*  80 */     this.color.a = Interpolation.pow2Out.apply(0.0F, 0.6F, this.duration * 2.0F);
/*  81 */     this.color2.a = this.color.a;
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb)
/*     */   {
/*  86 */     sb.setColor(this.color2);
/*  87 */     sb.draw(inner, this.orb.cX - inner.packedWidth / 2.0F, this.orb.cY - inner.packedHeight / 2.0F, inner.packedWidth / 2.0F, inner.packedHeight / 2.0F, inner.packedWidth, inner.packedHeight, this.scale * Settings.scale * 1.1F, this.scaleY * Settings.scale, 
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  97 */       MathUtils.random(-1.0F, 1.0F));
/*  98 */     sb.setBlendFunction(770, 1);
/*  99 */     sb.setColor(this.color);
/* 100 */     sb.draw(outer, this.orb.cX - outer.packedWidth / 2.0F, this.orb.cY - outer.packedHeight / 2.0F, outer.packedWidth / 2.0F, outer.packedHeight / 2.0F, outer.packedWidth, outer.packedHeight, this.scale, this.scaleY * Settings.scale, 
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 110 */       MathUtils.random(-2.0F, 2.0F));
/* 111 */     sb.draw(outer, this.orb.cX - outer.packedWidth / 2.0F, this.orb.cY - outer.packedHeight / 2.0F, outer.packedWidth / 2.0F, outer.packedHeight / 2.0F, outer.packedWidth, outer.packedHeight, this.scale, this.scaleY * Settings.scale, 
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 121 */       MathUtils.random(-2.0F, 2.0F));
/* 122 */     sb.setBlendFunction(770, 771);
/* 123 */     sb.setColor(this.color2);
/* 124 */     sb.draw(inner, this.orb.cX - inner.packedWidth / 2.0F, this.orb.cY - inner.packedHeight / 2.0F, inner.packedWidth / 2.0F, inner.packedHeight / 2.0F, inner.packedWidth, inner.packedHeight, this.scale * Settings.scale * 1.1F, this.scaleY * Settings.scale, 
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 134 */       MathUtils.random(-1.0F, 1.0F));
/*     */   }
/*     */ }


/* Location:              C:\Users\Computer\IdeaProjects\lib\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\SlimeFlareEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */