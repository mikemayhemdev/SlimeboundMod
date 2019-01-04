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
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.helpers.SlimeAnimListener;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import slimebound.SlimeboundMod;
import slimebound.actions.SlimeSpawnAction;
import slimebound.characters.SlimeboundCharacter;
import slimebound.powers.*;
import slimebound.vfx.FakeFlashAtkImgEffect;
import slimebound.vfx.SlimeDeathParticleEffect;
import slimebound.vfx.SlimeFlareEffect;
import slimebound.vfx.SlimeSpawnProjectile;


public abstract class SpawnedSlime
        extends AbstractOrb {

    public float NUM_X_OFFSET = 1.0F * Settings.scale;
    public float NUM_Y_OFFSET = -35.0F * Settings.scale;
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
    public int slimeBonus;
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
    public float scale = 1F;
    private static int W;
    private Texture img;
    public float x;
    public float y;
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
    public Color projectileColor;
    private float delayTime;
    private boolean hasSplashed;
    private boolean madePostDuplicated;
    private boolean renderBehind;
    private String atlasString = "images/monsters/theBottom/slimeAltS/skeleton.atlas";
    private String skeletonString = "images/monsters/theBottom/slimeAltS/skeleton.json";
    private String animString = "idle";

    public String customDescription;
    private float yOffset;
    public int debuffBonusAmount;
    public int debuffAmount;
    public Color extraFontColor = null;
    public boolean topSpawnVFX = false;



    public SpawnedSlime(String ID, int yOffset, Color projectileColor, String atlasString, String skeletonString, String animString, float scale, Color modelColor, int passive, int initialBoost, boolean movesToAttack, Color deathColor, SlimeFlareEffect.OrbFlareColor OrbFlareColor, Texture intentImage, String IMGURL) {

        this.scale = scale;
        this.modelColor = modelColor;
        this.atlas = new TextureAtlas(Gdx.files.internal(atlasString));
        //this.renderBehind=true;
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
        this.delayTime = 0.27F;
        this.yOffset = yOffset * Settings.scale;

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

        this.projectileColor = projectileColor;
        this.descriptions = CardCrawlGame.languagePack.getOrbString(this.ID).DESCRIPTION;

        this.name = CardCrawlGame.languagePack.getOrbString(this.ID).NAME;
        SlimeboundMod.mostRecentSlime = this;
        if (AbstractDungeon.player.hasPower(DuplicatedFormPower.POWER_ID)) this.madePostDuplicated = true;



        //AbstractDungeon.actionManager.addToBottom(new VFXAction(new SlimeFlareEffect(this, OrbVFXColor), .1F));
        this.applyFocus();

        updateDescription();


    }

public void spawnVFX(){
    if (AbstractDungeon.player.maxOrbs > 0) {

        if (this.topSpawnVFX) {
            AbstractDungeon.actionManager.addToTop(new VFXAction(new SlimeSpawnProjectile(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, this, 1.4F, projectileColor)));

        } else {
            AbstractDungeon.actionManager.addToBottom(new VFXAction(new SlimeSpawnProjectile(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, this, 1.4F, projectileColor)));
        }
    }
}
 @Override
    public void setSlot(int slotNum, int maxOrbs) {
        if (AbstractDungeon.player instanceof SlimeboundCharacter) {
            this.tX = ((SlimeboundCharacter) AbstractDungeon.player).orbPositionsX[slotNum];
            this.tY = ((SlimeboundCharacter) AbstractDungeon.player).orbPositionsY[slotNum];

        }



        this.hb.move(this.tX, this.tY);
    }


    public void onEndOfTurn() {

        this.activatedThisTurn = false;

    }


    public void onStartOfTurn() {


        activateEffect();

    }



    /*
    public void update() {

    }
    */

    public void applyFocus() {
        super.applyFocus();
        AbstractPower power = AbstractDungeon.player.getPower(PotencyPower.POWER_ID);
        int bonus = 0;
        if (this instanceof AttackSlime && AbstractDungeon.player.hasPower(BuffAttackSlimesPower.POWER_ID))
            bonus = AbstractDungeon.player.getPower(BuffAttackSlimesPower.POWER_ID).amount;
        if (this instanceof ShieldSlime && AbstractDungeon.player.hasPower(BuffShieldSlimesPower.POWER_ID))
            this.debuffBonusAmount = AbstractDungeon.player.getPower(BuffShieldSlimesPower.POWER_ID).amount;
        if (this instanceof PoisonSlime && AbstractDungeon.player.hasPower(BuffPoisonSlimesPower.POWER_ID))
            this.debuffBonusAmount = AbstractDungeon.player.getPower(BuffPoisonSlimesPower.POWER_ID).amount;
        if (this instanceof SlimingSlime && AbstractDungeon.player.hasPower(BuffSlimingSlimesPower.POWER_ID))
            this.debuffBonusAmount = AbstractDungeon.player.getPower(BuffSlimingSlimesPower.POWER_ID).amount;


        if (power != null) {

            this.passiveAmount = this.basePassiveAmount + power.amount + this.UniqueFocus + bonus;

        } else {
            this.passiveAmount = this.basePassiveAmount + this.UniqueFocus + bonus;

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

        if (!noEvokeBonus) {
            if (this instanceof HexSlime) {
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, -1), -1));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DexterityPower(AbstractDungeon.player, -1), -1));

                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new PotencyPower(AbstractDungeon.player, AbstractDungeon.player, -2), -2));

            }
            if (this instanceof ScrapOozeSlime){
                AbstractDungeon.actionManager.addToBottom(new SlimeSpawnAction(new slimebound.orbs.ScrapOozeSlime(), false, false));

            } else if (this instanceof GreedOozeSlime){
                AbstractDungeon.actionManager.addToBottom(new SlimeSpawnAction(new slimebound.orbs.GreedOozeSlime(), false, false));

            } else {
                AbstractDungeon.actionManager.addToBottom(new HealAction(AbstractDungeon.player, AbstractDungeon.player, 3));
            }
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
        this.animationTimerStart = this.animationTimer;
        this.animation = AbstractCreature.CreatureAnimation.ATTACK_FAST;
    }

    @Override
    public void updateAnimation() {

        if (this.animationTimer != 0.0F) {
            switch (this.animation) {
                case ATTACK_FAST:
                    this.updateFastAttackAnimation();
                    break;
            }
        }

            this.cX = MathHelper.orbLerpSnap(this.cX, this.tX);
            this.cY = MathHelper.orbLerpSnap(this.cY, this.tY);


        if (this.channelAnimTimer != 0.0F) {
            this.channelAnimTimer -= Gdx.graphics.getDeltaTime();
            if (this.channelAnimTimer < 0.0F) {
                this.channelAnimTimer = 0.0F;
            }
        }

        this.c.a = Interpolation.pow2In.apply(1.0F, 0.01F, this.channelAnimTimer / 0.5F);
        this.scale = Interpolation.swingIn.apply(Settings.scale, 0.01F, this.channelAnimTimer / 0.5F);
    }


    public void postSpawnEffects() {
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
            this.animX = Interpolation.fade.apply(0.0F, targetPos, (this.animationTimer / (this.animationTimerStart / 2)));
        }

    }

    public void render(SpriteBatch sb) {


        if (this.delayTime > 0F) {
            delayTime = delayTime - Gdx.graphics.getDeltaTime();
        } else if (this.atlas == null) {
            // logger.info("rendering null");
            sb.setColor(new Color(1F, 1F, 1F, 2F));

            sb.draw(this.img, this.cX - (float) this.img.getWidth() + this.animX * Settings.scale / 2.0F, this.cY + this.animY, (float) this.img.getWidth() * Settings.scale, (float) this.img.getHeight() * Settings.scale, 0, 0, this.img.getWidth(), this.img.getHeight(), false, false);
        } else {
            if (!hasSplashed) {
                AbstractDungeon.effectsQueue.add(new FakeFlashAtkImgEffect(this.cX, this.cY, projectileColor, 0.75F, true, 0.3F));
                this.hasSplashed = true;
                postSpawnEffects();
            } else {

                // logger.info("rendering slime model");
                this.state.update(Gdx.graphics.getDeltaTime());
                this.state.apply(this.skeleton);
                this.skeleton.updateWorldTransform();
                this.x = this.cX + this.animX;
                this.y = this.cY + this.animY + this.yOffset;
                this.skeleton.setPosition(this.x, this.y);
                //logger.info("x = " + this.cX + " y = " + (this.cY + AbstractDungeon.sceneOffsetY));

                this.skeleton.setColor(modelColor);
                this.skeleton.setFlip(true, false);
                sb.end();
                CardCrawlGame.psb.begin();
                AbstractMonster.sr.draw(CardCrawlGame.psb, this.skeleton);
                CardCrawlGame.psb.end();
                sb.begin();
                sb.setBlendFunction(770, 771);

            }
            renderText(sb);

        }
        //this.hb.render(sb);
    }


    public void renderText(SpriteBatch sb) {
        if (this.extraFontColor != null) {


            float fontOffset = 26 * Settings.scale;
            if (this.passiveAmount > 9) fontOffset = fontOffset + (6 * Settings.scale);
            FontHelper.renderFontCentered(sb, FontHelper.cardEnergyFont_L, this.passiveAmount + "/", this.cX + this.NUM_X_OFFSET, this.cY + this.NUM_Y_OFFSET, this.c, this.fontScale);



            FontHelper.renderFontCentered(sb, FontHelper.cardEnergyFont_L, Integer.toString(this.debuffAmount + this.debuffBonusAmount + this.slimeBonus), this.cX + this.NUM_X_OFFSET + fontOffset, this.cY + this.NUM_Y_OFFSET + 1F, this.extraFontColor, this.fontScale);

        } else {

            FontHelper.renderFontCentered(sb, FontHelper.cardEnergyFont_L, Integer.toString(this.passiveAmount), this.cX + this.NUM_X_OFFSET, this.cY + this.NUM_Y_OFFSET, this.c, this.fontScale);
        }
    }
}




