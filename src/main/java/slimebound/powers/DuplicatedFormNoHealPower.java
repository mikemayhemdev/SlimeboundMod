package slimebound.powers;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.HealthBarRenderPower;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.SmokePuffEffect;
import com.megacrit.cardcrawl.vfx.TextAboveCreatureEffect;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import slimebound.SlimeboundMod;
import slimebound.characters.SlimeboundCharacter;
import slimebound.orbs.SpawnedSlime;
import slimebound.vfx.DoubleSlimeParticle;


public class DuplicatedFormNoHealPower extends AbstractPower {
    public static final String POWER_ID = "Slimebound:DuplicatedFormNoHealPower";
    public static final String NAME = "Potency";
    public static PowerType POWER_TYPE = PowerType.BUFF;
    public static final String IMG = "powers/HalvedS.png";
    public static final Logger logger = LogManager.getLogger(SlimeboundMod.class.getName());

    public static String[] DESCRIPTIONS;
    private AbstractCreature source;
    private int maxHpTempLoss = 0;


    public DuplicatedFormNoHealPower(AbstractCreature owner, AbstractCreature source, int amount) {

        this.name = NAME;

        this.ID = POWER_ID;


        this.owner = owner;

        this.source = source;


        this.img = new com.badlogic.gdx.graphics.Texture(SlimeboundMod.getResourcePath(IMG));

        this.type = POWER_TYPE;

        this.amount = amount;
        this.DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(this.ID).DESCRIPTIONS;

        this.name = CardCrawlGame.languagePack.getPowerStrings(this.ID).NAME;

        updateDescription();

    }

    public int onHeal(int healAmount) {
        if ((AbstractDungeon.currMapNode != null) && (AbstractDungeon.getCurrRoom().phase == com.megacrit.cardcrawl.rooms.AbstractRoom.RoomPhase.COMBAT)) {
            //flash();
            int currentHealth = this.owner.currentHealth;
            int maxHealth = this.owner.maxHealth - this.amount;
            double currentPct = currentHealth * 1.001 / maxHealth * 1.001;
            logger.info("Current health: " + currentHealth);
            logger.info("Max health: " + maxHealth);
            logger.info("Current percentage: " + currentPct);
             if (currentHealth + healAmount > maxHealth) {
                return (maxHealth) - currentHealth;
            } else {
                return healAmount;
            }
        }
        return healAmount;
    }


    public void updateDescription() {


        this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);


    }



    public void onInitialApplication() {
        AbstractPlayer p = AbstractDungeon.player;

        //if (p.currentHealth > (p.maxHealth/2)){
        //  AbstractDungeon.actionManager.addToBottom(new LoseHPAction(AbstractDungeon.player,AbstractDungeon.player,p.currentHealth-(p.maxHealth/2)));

        updateCurrentHealth();

    }

    public void updateCurrentHealth(){
        if (this.owner.currentHealth > this.owner.maxHealth - this.amount){

            this.owner.currentHealth = this.owner.maxHealth - this.amount;

            //int currentAmount = this.owner.currentHealth - (this.owner.maxHealth - this.amount);
            //AbstractDungeon.player.damage(new DamageInfo(AbstractDungeon.player, currentAmount, DamageInfo.DamageType.HP_LOSS));

        }
    }

    public void stackPower(int stackAmount) {
        //SlimeboundMod.logger.info("Stacking Split: " + stackAmount);

            this.fontScale = 8.0F;
            this.amount += stackAmount;
        if (stackAmount < 0){
            this.owner.heal(stackAmount * -1);
        }
             updateCurrentHealth();
        if (this.amount == 0){
            AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction(this.owner, this.owner, DuplicatedFormNoHealPower.POWER_ID));

        }



    }


    public void onRemove() {
        restoreMaxHP();
    }

    public void restoreMaxHP(){


        this.owner.heal(this.maxHpTempLoss);
    }

    public void onVictory(){
        this.maxHpTempLoss = this.amount;
        this.amount = 0;
        restoreMaxHP();
    }
}



