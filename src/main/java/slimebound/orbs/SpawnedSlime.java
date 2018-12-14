package slimebound.orbs;

import basemod.animations.AbstractAnimation;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.*;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.SlimeAnimListener;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.FireBurstParticleEffect;
import com.megacrit.cardcrawl.vfx.TorchHeadFireEffect;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import slimebound.SlimeboundMod;
import slimebound.powers.PotencyPower;
import slimebound.vfx.*;


public abstract class SpawnedSlime
        extends AbstractOrb {
    private float vfxTimer = 1.0F;
    private float vfxIntervalMin = 0.2F;
    private float vfxIntervalMax = 0.7F;

    private static final float ORB_WAVY_DIST = 0.04F;
    private static final float PI_4 = 12.566371F;
    public AbstractCard lockedCard;
    protected boolean showChannelValue = true;
    public static final Logger logger = LogManager.getLogger(SlimeboundMod.class.getName());
    public boolean upgraded = false;
    public boolean showPassive = true;
    public boolean activatedThisTurn = false;
    public int UniqueFocus;
    public float animX;
    public float animY;
    public boolean movesToAttack;
    public int upgradedInitialBoost;
    public String originalRelic = "";
    public String[] descriptions;
    public com.badlogic.gdx.graphics.Texture intentImage;
    private SlimeFlareEffect.OrbFlareColor OrbVFXColor;
    private Color deathColor;
    private Color modelColor;
    public static String orbID = "";
    public boolean noEvokeBonus;
    private float scale = 1F;
    private static int W;
    private Texture img;
    private float x;
    private float px;
    public static SkeletonMeshRenderer sr;
    private AbstractCreature.CreatureAnimation animation;
    private float animationTimer;

    private float animationTimerStart;
    private TextureAtlas atlas;
    public Skeleton skeleton;
    public AnimationState state;
    private AnimationStateData stateData;
    private AbstractAnimation animationA;
    public AbstractPlayer p;
    private float y;
    private String atlasString = "images/monsters/theBottom/slimeAltS/skeleton.atlas";
    private String skeletonString = "images/monsters/theBottom/slimeAltS/skeleton.json";
    private String animString = "idle";

    public String customDescription;


    public SpawnedSlime(String ID, int passive, int initialBoost, boolean movesToAttack, Color deathColor, SlimeFlareEffect.OrbFlareColor OrbFlareColor, Texture intentImage, String IMGURL) {
    this(ID,"images/monsters/theBottom/slimeAltS/skeleton.atlas","images/monsters/theBottom/slimeAltS/skeleton.json","idle",1F,Color.WHITE,passive,initialBoost,movesToAttack,deathColor,OrbFlareColor,intentImage,IMGURL);
    }
    public SpawnedSlime(String ID, String atlasString, String skeletonString, String animString, float scale, Color modelColor, int passive, int initialBoost, boolean movesToAttack, Color deathColor, SlimeFlareEffect.OrbFlareColor OrbFlareColor, Texture intentImage, String IMGURL) {

        this.scale=scale;
        this.modelColor=modelColor;
        this.atlas = new TextureAtlas(Gdx.files.internal(atlasString));
        SkeletonJson json = new SkeletonJson(this.atlas);



        json.setScale(Settings.scale / scale);
        SkeletonData skeletonData = json.readSkeletonData(Gdx.files.internal(skeletonString));
        this.skeleton = new Skeleton(skeletonData);
        this.skeleton.setColor(Color.WHITE);
        this.stateData = new AnimationStateData(skeletonData);
        this.state = new AnimationState(this.stateData);
        AnimationState.TrackEntry e = this.state.setAnimation(0, animString, true);
        e.setTime(e.getEndTime() * MathUtils.random());
        this.state.addListener(new SlimeAnimListener());

        this.ID = ID;

        //this.img = ImageMaster.loadImage(IMGURL);


        this.basePassiveAmount = passive;
        this.movesToAttack = movesToAttack;

        this.deathColor = deathColor;

        this.evokeAmount = 1;

        this.passiveAmount = this.basePassiveAmount;
        this.OrbVFXColor = OrbFlareColor;
        this.intentImage = intentImage;
        this.upgradedInitialBoost = initialBoost;


        this.channelAnimTimer = 0.5F;


        this.descriptions = CardCrawlGame.languagePack.getOrbString(this.ID).DESCRIPTION;

        this.name = CardCrawlGame.languagePack.getOrbString(this.ID).NAME;
        SlimeboundMod.mostRecentSlime = this;



        AbstractDungeon.actionManager.addToBottom(new VFXAction(new SlimeSpawnProjectile(AbstractDungeon.player.hb.cX,AbstractDungeon.player.hb.cY,this,1F,deathColor)));

        //AbstractDungeon.actionManager.addToBottom(new VFXAction(new SlimeFlareEffect(this, OrbVFXColor), .1F));
        this.applyFocus();

        updateDescription();

        if (this instanceof HexSlime) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, 1), 1));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DexterityPower(AbstractDungeon.player, 1), 1));

            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new PotencyPower(AbstractDungeon.player, AbstractDungeon.player, 3), 3));

        }

    }


    public void onEndOfTurn() {

        this.activatedThisTurn = false;

    }


    public void onStartOfTurn() {


        activateEffect();

    }

    /*
    public void updateAnimation() {
    }

    public void update() {

    }
    */

    public void applyFocus() {
        super.applyFocus();
        AbstractPower power = AbstractDungeon.player.getPower(PotencyPower.POWER_ID);
        if (power != null) {
            this.passiveAmount = this.basePassiveAmount + power.amount + this.UniqueFocus;

        } else {
            this.passiveAmount = this.basePassiveAmount + this.UniqueFocus;

        }
        updateDescription();
    }

    public void applyUniqueFocus(int StrAmount) {

        logger.info("Torch head getting buffed by " + StrAmount);
        this.UniqueFocus = this.UniqueFocus + StrAmount;
        this.passiveAmount = this.passiveAmount + StrAmount;
        updateDescription();
        //AbstractDungeon.effectsQueue.add(new FireBurstParticleEffect(this.cX, this.cY));
    }



    public void onEvoke() {

        if (!noEvokeBonus){
            if (this instanceof HexSlime) {
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, -1), -1));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DexterityPower(AbstractDungeon.player, -1), -1));

                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new PotencyPower(AbstractDungeon.player, AbstractDungeon.player, -3), -3));

            }

        AbstractDungeon.actionManager.addToBottom(new HealAction(AbstractDungeon.player, AbstractDungeon.player, 3));

    }
        triggerEvokeAnimation();

    }


    public void triggerEvokeAnimation() {

        CardCrawlGame.sound.play("DUNGEON_TRANSITION", 0.1F);

        AbstractDungeon.effectsQueue.add(new SlimeDeathParticleEffect(this.cX, this.cY, this.deathColor));

    }



    public void activateEffect() {


        if (SlimeboundMod.slimeDelay == true) {
            AbstractDungeon.actionManager.addToTop(new WaitAction(1.4F));
            SlimeboundMod.slimeDelay = false;
        }

        activateEffectUnique();

    }

    public void activateEffectUnique() {
    }


    public void playChannelSFX() {

        CardCrawlGame.sound.play("SLIMED_ATK", 0.1F);

    }

    public void useFastAttackAnimation() {
        this.animationTimer = 0.4F;
        this.animationTimerStart=this.animationTimer;
        this.animation = AbstractCreature.CreatureAnimation.ATTACK_FAST;
    }


    public void updateAnimation() {
        super.updateAnimation();
        if (this.animationTimer != 0.0F) {
            switch (this.animation) {
                case ATTACK_FAST:
                    this.updateFastAttackAnimation();
                    break;
            }
        }
    }




    protected void updateFastAttackAnimation() {
        this.animationTimer -= Gdx.graphics.getDeltaTime();
        float targetPos = 50.0F * Settings.scale;


        if (this.animationTimer > (this.animationTimerStart / 2)) {
            this.animX = Interpolation.exp5Out.apply(0.0F, targetPos, ((this.animationTimerStart / 2) - (this.animationTimer - (this.animationTimerStart / 2))) / (this.animationTimerStart / 2));
            //logger.info("pow2Out " + ((this.animationTimerStart / 2) - (this.animationTimer - (this.animationTimerStart / 2))) / (this.animationTimerStart / 2));

        } else if (this.animationTimer < 0.0F) {
            this.animationTimer = 0.0F;
            this.animX = 0.0F;
        } else {
            //logger.info("fade " + this.animationTimer /(this.animationTimerStart / 2));
            this.animX = Interpolation.fade.apply(0.0F, targetPos,(this.animationTimer /(this.animationTimerStart / 2)));
        }

    }

    public void render(SpriteBatch sb) {

        renderText(sb);
        if (this.atlas == null) {
           // logger.info("rendering null");
            sb.setColor(new Color(1F, 1F, 1F, 2F));

            sb.draw(this.img, this.cX - (float) this.img.getWidth() + this.animX * Settings.scale / 2.0F, this.cY + this.animY, (float) this.img.getWidth() * Settings.scale, (float) this.img.getHeight() * Settings.scale, 0, 0, this.img.getWidth(), this.img.getHeight(), false, false);
        } else {

           // logger.info("rendering slime model");
            this.state.update(Gdx.graphics.getDeltaTime());
            this.state.apply(this.skeleton);
            this.skeleton.updateWorldTransform();
            this.skeleton.setPosition(this.cX + this.animX, this.cY + this.animY - 20);
           //logger.info("x = " + this.cX + " y = " + (this.cY + AbstractDungeon.sceneOffsetY));

            this.skeleton.setColor(new Color(modelColor.r,modelColor.b,modelColor.g,2F));
             this.skeleton.setFlip(true,false);
            sb.end();
            CardCrawlGame.psb.begin();
            AbstractMonster.sr.draw(CardCrawlGame.psb, this.skeleton);
            CardCrawlGame.psb.end();
            sb.begin();
            sb.setBlendFunction(770, 771);


        }
        //this.hb.render(sb);
    }
}




