package slimebound.powers;


import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.SmokePuffEffect;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import slimebound.SlimeboundMod;
import slimebound.characters.SlimeboundCharacter;
import slimebound.orbs.SpawnedSlime;
import slimebound.vfx.DoubleSlimeParticle;


public class DuplicatedFormNoHealPower extends AbstractPower {
    public static final String POWER_ID = "DuplicatedFormNoHealPower";
    public static final String NAME = "Potency";
    public static PowerType POWER_TYPE = PowerType.DEBUFF;
    public static final String IMG = "powers/HalvedS.png";
    public static final Logger logger = LogManager.getLogger(SlimeboundMod.class.getName());

    public static String[] DESCRIPTIONS;
    private AbstractCreature source;
    private int cardsDoubledThisTurn = 0;


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


    public void updateDescription() {


        this.description = (DESCRIPTIONS[0]);


    }


    public int onHeal(int healAmount) {
        if ((AbstractDungeon.currMapNode != null) && (AbstractDungeon.getCurrRoom().phase == com.megacrit.cardcrawl.rooms.AbstractRoom.RoomPhase.COMBAT)) {
            flash();
            int currentHealth = this.owner.currentHealth;
            int maxHealth = this.owner.maxHealth;
            double currentPct = currentHealth * 1.001 / maxHealth * 1.001;
            logger.info("Current health: " + currentHealth);
            logger.info("Max health: " + maxHealth);
            logger.info("Current percentage: " + currentPct);
            if (currentPct >= 0.5) {
                return MathUtils.round(healAmount * 0F);
            } else if (currentHealth + healAmount > maxHealth / 2) {
                return (maxHealth / 2) - currentHealth;
            } else {
                return healAmount;
            }
        }
        return healAmount;
    }

    public void onInitialApplication() {
        AbstractPlayer p = AbstractDungeon.player;

        if (p.currentHealth > (p.maxHealth/2)){
            AbstractDungeon.actionManager.addToBottom(new LoseHPAction(AbstractDungeon.player,AbstractDungeon.player,p.currentHealth-(p.maxHealth/2)));


        }
    }

}



