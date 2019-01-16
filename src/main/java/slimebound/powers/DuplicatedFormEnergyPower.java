/*    */ package slimebound.powers;
/*    */
/*    */

/*    */

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import slimebound.SlimeboundMod;

/*    */
/*    */
        /*    */
        /*    */

/*    */
/*    */ public class DuplicatedFormEnergyPower extends AbstractPower
/*    */ {
    /*    */   public static final String POWER_ID = "DuplicatedFormEnergyPower";
    /*    */   public static final String NAME = "Potency";
                public static PowerType POWER_TYPE = PowerType.BUFF;
    /*    */   public static final String IMG = "powers/DuplicatedEnergyS.png";
    public static final Logger logger = LogManager.getLogger(SlimeboundMod.class.getName()); // lets us log output

    /* 14 */   public static String[] DESCRIPTIONS;
    /*    */   private AbstractCreature source;
    /* 18 */   private int cardsDoubledThisTurn = 0;

    /*    */
    /*    */
    /*    */
    public DuplicatedFormEnergyPower(AbstractCreature owner, AbstractCreature source, int amount)
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

    public void updateDescription()
        /*    */ {
        /* 36 */
        /* 37 */
        if (this.amount <= 1) {
            /* 41 */       this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
            /*    */     } else {
            /* 43 */       this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[3];
            /*    */     }
        /*    */
        /*    */
    }

    public void atStartOfTurn() {
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, this.amount));
        AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(this.amount));
    }

}
/*    */


/* Location:              C:\Program Files (x86)\Steam\steamapps\common\SlayTheSpire\mods\SlimeboundMod.jar!\slimboundmod\powers\SearingPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */