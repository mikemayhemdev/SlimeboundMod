package slimebound.powers;


import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import slimebound.SlimeboundMod;
import slimebound.actions.RandomLickCardAction;
import slimebound.actions.TrigggerSpecificSlimeAttackAction;
import slimebound.cards.AbstractSlimeboundCard;
import slimebound.orbs.SlimingSlime;


public class GluttonyPower extends AbstractPower {
    public static final String POWER_ID = "Slimebound:GluttonyPower";
    public static final String NAME = "Potency";
    public static PowerType POWER_TYPE = PowerType.BUFF;
    public static final String IMG = "powers/GluttonyS.png";
    public static final Logger logger = LogManager.getLogger(SlimeboundMod.class.getName());

    public static String[] DESCRIPTIONS;
    private AbstractCreature source;
    private int timesTriggeredThisTurn;


    public GluttonyPower(AbstractCreature owner, AbstractCreature source, int amount) {

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

    public void updateSlimedEffects(){


        for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
        if (c.hasTag(SlimeboundMod.LICK)) {
            ((AbstractSlimeboundCard) c).upgradeLickSlimed(0);
        }
    }for (AbstractCard c : AbstractDungeon.player.exhaustPile.group) {
            if (c.hasTag(SlimeboundMod.LICK)) {
            ((AbstractSlimeboundCard) c).upgradeLickSlimed(0);
        }
    }for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
            if (c.hasTag(SlimeboundMod.LICK)) {
            ((AbstractSlimeboundCard) c).upgradeLickSlimed(0);
        }
    }for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.hasTag(SlimeboundMod.LICK)) {
            ((AbstractSlimeboundCard) c).upgradeLickSlimed(0);
        }
    }

}

    public void onInitialApplication() {
        updateSlimedEffects();

    }

    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        updateSlimedEffects();
    }

    public void updateDescription() {



            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];



    }

    /*
    public void onAfterCardPlayed(AbstractCard usedCard) {
        super.onAfterCardPlayed(usedCard);
        if (usedCard.hasTag(SlimeboundMod.LICK)){
            if(timesTriggeredThisTurn == 0){
                if (AbstractDungeon.player.orbs.get(0) != null);{
                    flash();
                    for (int i = 0; i < this.amount; i++) {
                        AbstractDungeon.actionManager.addToBottom(new RandomLickCardAction(false));
                    }
                    timesTriggeredThisTurn++;
                }
            }
        }
    }


    public void atStartOfTurn() {

        timesTriggeredThisTurn=0;


    }
*/

}



