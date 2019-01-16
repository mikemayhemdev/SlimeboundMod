/*    */ package slimebound.powers;
/*    */
/*    */

/*    */

import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.DamageHooks;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.colorless.Shiv;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import slimebound.SlimeboundMod;
import slimebound.cards.*;

/*    */
/*    */
        /*    */
        /*    */

/*    */
/*    */ public class TackleBuffPower extends AbstractPower
/*    */ {
    /*    */   public static final String POWER_ID = "TackleBuffPower";
    /*    */   public static final String NAME = "Potency";
    public static PowerType POWER_TYPE = PowerType.BUFF;
    /*    */   public static final String IMG = "powers/bleed.png";
    public static final Logger logger = LogManager.getLogger(SlimeboundMod.class.getName()); // lets us log output

    /* 14 */   public static String[] DESCRIPTIONS;
    /*    */   private AbstractCreature source;

    /*    */
    /*    */
    /*    */

    public TackleBuffPower(AbstractCreature owner, AbstractCreature source, int amount)
        /*    */ {
        /* 23 */
        this.name = NAME;
        /* 24 */
        this.ID = POWER_ID;

        /* 25 */
        this.owner = owner;
        /* 26 */
        this.source = source;
        /*    */
        /* 28 */
        this.img = new com.badlogic.gdx.graphics.Texture(SlimeboundMod.getResourcePath(IMG));
        /* 29 */
        this.type = POWER_TYPE;
        /* 30 */
        this.amount = amount;
        DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(this.ID).DESCRIPTIONS;
        /*  84 */
        this.name = CardCrawlGame.languagePack.getPowerStrings(this.ID).NAME;
        /* 31 */
        updateDescription();
        /*    */
    }

    public void stackPower(int stackAmount)
        /*    */ {
        /* 33 */
        this.fontScale = 8.0F;
        /* 34 */
        this.amount += stackAmount;
        /* 35 */
        updateTacklesInHand();
        /*    */
    }

    /*    */
    /*    */
    public void updateDescription()
    /*    */ {
        /* 36 */
        /* 37 */
        this.description = (DESCRIPTIONS[0] + this.amount * 2 + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2]);
        /*    */
        /*    */
    }

    private void updateTacklesInHand() {
        /* 39 */
        for (AbstractCard c : com.megacrit.cardcrawl.dungeons.AbstractDungeon.player.hand.group) {
            /* 53 */
            if (c.cardID == "Tackle") {
                /* 55 */
                Tackle t = (Tackle) c;
                if (t.upgraded) {
                    t.baseDamage = Tackle.originalDamage + Tackle.upgradeDamage + this.amount * 2;

                    t.baseBlock = Tackle.originalBlock + Tackle.upgradeSelfDamage + this.amount;

                } else {
                    t.baseDamage = Tackle.originalDamage + this.amount * 2;
                    /*    */
                    t.baseBlock = Tackle.originalBlock + this.amount;
                }
                /*    */
            } else if (c.cardID == "PoisonTackle") {
                /* 55 */
                PoisonTackle t = (PoisonTackle) c;
                if (t.upgraded) {
                    t.baseDamage = PoisonTackle.originalDamage + PoisonTackle.upgradeDamage + this.amount * 2;

                    t.baseBlock = PoisonTackle.originalBlock + PoisonTackle.upgradeSelfDamage + this.amount;

                } else {
                    t.baseDamage = PoisonTackle.originalDamage + this.amount * 2;
                    /*    */
                    t.baseBlock = PoisonTackle.originalBlock + this.amount;
                }
                /*    */
            } else if (c.cardID == "QuickTackle") {
                /* 55 */
                QuickTackle t = (QuickTackle) c;
                if (t.upgraded) {
                    t.baseDamage = QuickTackle.originalDamage + QuickTackle.upgradeDamage + this.amount * 2;

                    t.baseBlock = QuickTackle.originalBlock + QuickTackle.upgradeSelfDamage + this.amount;

                } else {
                    t.baseDamage = QuickTackle.originalDamage + this.amount * 2;
                    /*    */
                    t.baseBlock = QuickTackle.originalBlock + this.amount;
                }
            } else if (c.cardID == "FlameTackle") {
                /* 55 */
                FlameTackle t = (FlameTackle) c;
                if (t.upgraded) {
                    t.baseDamage = FlameTackle.originalDamage + FlameTackle.upgradeDamage + this.amount * 2;

                    t.baseBlock = FlameTackle.originalBlock + FlameTackle.upgradeSelfDamage + this.amount;

                } else {
                    t.baseDamage = FlameTackle.originalDamage + this.amount * 2;
                    /*    */
                    t.baseBlock = FlameTackle.originalBlock + this.amount;
                }
            }
        }
            /*    */
    }

    /*    */
    public void onDrawOrDiscard()
    /*    */ {
        /* 52 */
        updateTacklesInHand();
        /*    */
    }
}
/*    */


/* Location:              C:\Program Files (x86)\Steam\steamapps\common\SlayTheSpire\mods\SlimeboundMod.jar!\slimboundmod\powers\SearingPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */