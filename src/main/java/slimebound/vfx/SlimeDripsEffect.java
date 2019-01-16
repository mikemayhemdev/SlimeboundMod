/*    */ package slimebound.vfx;
/*    */ 
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */
/*    */

/*    */
/*    */ public class SlimeDripsEffect extends com.megacrit.cardcrawl.vfx.AbstractGameEffect
/*    */ {
    /* 13 */   private int count = 0;
    /* 14 */   private float timer = 0.0F;
                public float xPos;
                public float yPos;

    /*    */
    /*    */ public SlimeDripsEffect(float x, float y, int count) {
        this.xPos = x;
        this.yPos = y;
        this.count = count;
    }
        /*    */
        /*    */
        public void update ()
        /*    */
        {
            /* 20 */
            this.timer -= com.badlogic.gdx.Gdx.graphics.getDeltaTime();
            /* 21 */
            if (this.timer < 0.0F) {
                /* 22 */
                this.timer += 0.15F;
                /*    */
                /* 24 */
                switch (this.count) {
                    /*    */
                    case 0:
                        /* 27 */
                        CardCrawlGame.sound.playA("BLOOD_SPLAT", -0.75F);
                        /* 29 */
                        AbstractDungeon.effectsQueue.add(new SlimeWaterDropEffect(xPos, yPos + 50.0F * Settings.scale));
                        /*    */
                        /*    */
                        /*    */
                        /* 33 */
                        break;
                    /*    */
                    case 1:
                        /* 35 */
                        AbstractDungeon.effectsQueue.add(new SlimeWaterDropEffect(xPos + 50.0F * Settings.scale, yPos - 30.0F * Settings.scale));
                        /*    */
                        /*    */
                        /*    */
                        /* 39 */
                        break;
                    /*    */
                    case 2:
                        /* 41 */
                        AbstractDungeon.effectsQueue.add(new SlimeWaterDropEffect(xPos - 30.0F * Settings.scale, yPos + 50.0F * Settings.scale));
                        /*    */
                        /*    */
                        /*    */
                        /* 45 */
                        break;
                    /*    */
                    case 3:
                        /* 47 */
                        AbstractDungeon.effectsQueue.add(new SlimeWaterDropEffect(xPos + 40.0F * Settings.scale, yPos + 70.0F * Settings.scale));
                        /*    */
                        /*    */
                        /*    */
                        /* 51 */
                        break;
                    /*    */
                    case 4:
                        /* 53 */
                        AbstractDungeon.effectsQueue.add(new SlimeWaterDropEffect(xPos - 20.0F * Settings.scale, yPos - 50.0F * Settings.scale));
                        /*    */
                        /*    */
                        /*    */
                        /* 57 */
                        break;
                    /*    */
                }
                /*    */
                /*    */
                /*    */
                /* 62 */
                this.count += 1;
                /*    */
                /* 64 */
                if (this.count == 6) {
                    /* 65 */
                    this.isDone = true;
                    /*    */
                }
                /*    */
            }
            /*    */
        }
        /*    */
        /*    */
        public void render (SpriteBatch sb){
    }
        /*    */
    }


/* Location:              C:\Users\Computer\IdeaProjects\lib\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\SlimeDripsEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */