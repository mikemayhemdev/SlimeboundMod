/*     */ package slimebound.orbs;
/*     */
/*     */ import com.badlogic.gdx.Gdx;
/*     */
/*     */
/*     */ import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */
/*     */
/*     */ import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*     */ import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */
/*     */
import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */
/*     */
/*     */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*     */
/*     */
/*     */ import com.megacrit.cardcrawl.orbs.AbstractOrb;
/*     */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*     */
import com.megacrit.cardcrawl.powers.RegenPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.*;
/*     */
/*     */
/*     */
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import slimebound.SlimeboundMod;
import slimebound.powers.PotencyPower;
import slimebound.vfx.SlimeDeathParticleEffect;
import slimebound.vfx.SlimeFlareEffect;
import slimebound.vfx.SlimeIntentEffect;
import slimebound.vfx.SlimeIntentMovementEffect;
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
/*     */ public abstract class SpawnedSlime
/*     */   extends AbstractOrb
/*     */ {
    /*  43 */   private float vfxTimer = 1.0F;
    /*  44 */   private float vfxIntervalMin = 0.2F;
    /*  45 */   private float vfxIntervalMax = 0.7F;
    /*     */
    /*     */   private static final float ORB_WAVY_DIST = 0.04F;
    /*     */   private static final float PI_4 = 12.566371F;
    /*     */   public AbstractCard lockedCard;
    /*  50 */   protected boolean showChannelValue = true;
    /*     */public static final Logger logger = LogManager.getLogger(SlimeboundMod.class.getName()); // lets us log output
/*
/*  55 */   public boolean upgraded = false;
    /*  56 */   public boolean showPassive = true;
                public boolean activatedThisTurn = false;
                public int UniqueFocus;
                public boolean movesToAttack;
    /*  57 */   public String originalRelic = "";
    /*     */   public String[] descriptions;
                public com.badlogic.gdx.graphics.Texture intentImage;
                private SlimeFlareEffect.OrbFlareColor OrbVFXColor;
                private Color deathColor;

    /*     */   public String customDescription;

    /*     */
    /*     */
    public SpawnedSlime(String ID, int passive, boolean movesToAttack, Color deathColor, SlimeFlareEffect.OrbFlareColor OrbFlareColor, Texture intentImage, String IMGURL)
    /*     */ {
        /*  63 */
        this.ID = ID;
        /*  64 */
        this.img = ImageMaster.loadImage(IMGURL);
        /*  65 */
        /*     */
        /*     */
        /*  72 */
        this.basePassiveAmount = passive;
        this.movesToAttack = movesToAttack;
        /*  73 */
        this.deathColor = deathColor;
        /*  74 */
        this.evokeAmount = 1;

        this.passiveAmount = this.basePassiveAmount;
        this.OrbVFXColor = OrbFlareColor;
        this.intentImage = intentImage;
        /*     */

        /*     */
        /*  78 */
        this.channelAnimTimer = 0.5F;
        /*  79 */
        /*     */
        /*  83 */
        this.descriptions = CardCrawlGame.languagePack.getOrbString(this.ID).DESCRIPTION;
        /*  84 */
        this.name = CardCrawlGame.languagePack.getOrbString(this.ID).NAME;

        /*  87 */
        this.applyFocus();
        AbstractDungeon.actionManager.addToBottom(new VFXAction(new SlimeFlareEffect(this,OrbVFXColor), .5F));

        updateDescription();

        /*     */
    }

    /*     */
    /*     */
    public void onEndOfTurn()
    /*     */ {
        /*  92 */
        this.activatedThisTurn = false;
        /*     */
    }
    /*     */

    /*     */
    /*     */
    /*     */
    public void onCardUse(AbstractCard c) {
    }


    /*     */
    /*     */
    public void onCardDraw(AbstractCard c) {
    }

    /*     */
    /*     */
    /*     */


    public void onStartOfTurn() {





        activateEffect();

    }

    public void atTurnStartPostDraw() {

    }


    /*     */
    /*     */
    public void onVictory() {
    }

    public void applyFocus() {
        AbstractPower power = AbstractDungeon.player.getPower("PotencyPower");
        if (power != null) {
            this.passiveAmount = this.basePassiveAmount + power.amount + this.UniqueFocus;
            updateDescription();
        }
    }

        public void applyUniqueFocus(int StrAmount){
            logger.info("Torch head getting buffed by "+ StrAmount);
        this.UniqueFocus = this.UniqueFocus + StrAmount;
        this.passiveAmount = this.passiveAmount + StrAmount;
        updateDescription();
        AbstractDungeon.effectsQueue.add(new FireBurstParticleEffect(this.cX, this.cY));
    }


    public void onEvoke()
    /*     */ {
        /* 118 */

        if (this.ID == "HexSlime") {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, 1), 1));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new PotencyPower(AbstractDungeon.player, AbstractDungeon.player, 1), 1));
            com.megacrit.cardcrawl.dungeons.AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, 6));
            AbstractDungeon.actionManager.addToBottom(new HealAction(AbstractDungeon.player, AbstractDungeon.player, 6));

        } else {
            if (evokeAmount == 1) {
                com.megacrit.cardcrawl.dungeons.AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, 3));
                AbstractDungeon.actionManager.addToBottom(new HealAction(AbstractDungeon.player, AbstractDungeon.player, 3));
            }
        }
        triggerEvokeAnimation();
        /*     */
    }

    /*     */
    /*     */
    /*     */
    public void triggerEvokeAnimation()
    /*     */ {
        /* 125 */
        CardCrawlGame.sound.play("DUNGEON_TRANSITION", 0.1F);
        /* 126 */
        AbstractDungeon.effectsQueue.add(new SlimeDeathParticleEffect(this.cX, this.cY,this.deathColor));
        /*     */
    }

    /*     */
    /*     */
    /*     */
    public void updateDescription()
    /*     */ {
        /* 132 */


        /*     */

        /*     */
    }

        /*     */

    /*     */
    /*     */
    /*     */


    /*     */
    /*     */
    public void activateEffect() {
        /* 154 */
        float speedTime = 0.3F;
        /* 155 */
        if (Settings.FAST_MODE) {
            /* 156 */
            speedTime = 0.15F;
            /*     */
        }
        if (SlimeboundMod.slimeDelay == true){
            AbstractDungeon.actionManager.addToTop(new WaitAction(1.4F));
            SlimeboundMod.slimeDelay = false;
        }
        /* 158 */
        //AbstractDungeon.actionManager.addToBottom(new WaitAction(0.1F));
        AbstractDungeon.actionManager.addToBottom(new VFXAction(new SlimeIntentEffect(this.intentImage,this), speedTime));
        if (this.movesToAttack) {
            AbstractDungeon.actionManager.addToBottom(new VFXAction(new SlimeIntentMovementEffect(this), speedTime));
        }
        activateEffectUnique();
        /*     */
    }

    public void activateEffectUnique() {}


    /*     */
    /*     */
    /*     */
    /*     */
    public void playChannelSFX()
    /*     */ {
        /* 165 */
        CardCrawlGame.sound.play("SLIMED_ATK", 0.1F);
        /*     */
    }

    /*     */
    /*     */
    /*     */
    public void render(SpriteBatch sb)
    /*     */ {
        /* 171 */
        sb.setColor(this.c);
        /* 172 */
        sb.draw(this.img, this.cX - 48.0F + this.bobEffect.y / 4.0F, this.cY - 48.0F + this.bobEffect.y / 4.0F, 48.0F, 48.0F, 96.0F, 96.0F, this.scale, this.scale, 0.0F, 0, 0, 96, 96, false, false);
        /*     */
        /* 174 */
        renderText(sb);
        /* 175 */
        this.hb.render(sb);
        /*     */
    }

    /*     */
    /*     */
    /*     */
    public void updateAnimation()
    /*     */ {
        /* 181 */
        super.updateAnimation();
        /* 182 */
        this.angle += Gdx.graphics.getDeltaTime() * 45.0F;
        /*     */
        /* 184 */
        this.vfxTimer -= Gdx.graphics.getDeltaTime();
        /* 185 */

    }
}
/*     */   
/*     */


/* Location:              C:\Program Files (x86)\Steam\steamapps\common\SlayTheSpire\mods\TheDisciple.jar!\chronomuncher\orbs\SpawnedSlime.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */