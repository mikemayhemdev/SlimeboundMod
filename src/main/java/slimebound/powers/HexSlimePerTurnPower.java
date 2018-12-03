/*    */ package slimebound.powers;
/*    */
/*    */

/*    */

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import slimebound.SlimeboundMod;
import slimebound.actions.CheckForSixHexAction;
import slimebound.actions.RandomTimeEaterCardAction;
import slimebound.actions.SlimeSpawnAction;

/*    */
/*    */
        /*    */
        /*    */

/*    */
/*    */ public class HexSlimePerTurnPower extends AbstractPower
/*    */ {
    /*    */   public static final String POWER_ID = "HexSlimePerTurnPower";
    /*    */   public static final String NAME = "Potency";
    public static PowerType POWER_TYPE = PowerType.BUFF;
    /*    */   public static final String IMG = "powers/nostalgia.png";
    public static final Logger logger = LogManager.getLogger(SlimeboundMod.class.getName()); // lets us log output

    /* 14 */   public static String[] DESCRIPTIONS;
    /*    */   private AbstractCreature source;

    /*    */
    /*    */
    /*    */
    public HexSlimePerTurnPower(AbstractCreature owner, AbstractCreature source, int amount)
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
        this.DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(this.ID).DESCRIPTIONS;
        /*  84 */
        this.name = CardCrawlGame.languagePack.getPowerStrings(this.ID).NAME;
        /* 31 */
        updateDescription();
        /*    */
    }

    /*    */
    /*    */
    public void updateDescription()
    /*    */ {
        /* 36 */
        /* 37 */
        if (this.amount == 1) {
            /* 33 */       this.description = DESCRIPTIONS[0];
            /*    */     } else {
            /* 35 */       this.description = (DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2]);
            /*    */     }
        /*    */
        /*    */
    }


    public void atStartOfTurn()
        /*    */ {
        /* 33 */
        flash();
        /* 34 */
        AbstractDungeon.actionManager.addToBottom(new SlimeSpawnAction(new slimebound.orbs.HexSlime(),false,false));
        AbstractDungeon.actionManager.addToBottom(new CheckForSixHexAction(AbstractDungeon.player));

        if (this.amount <= 1) {
            /* 47 */
            AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction(this.owner, this.owner, "HexSlimePerTurnPower"));
            /*    */
        } else {
            /* 49 */
            AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.ReducePowerAction(this.owner, this.owner, "HexSlimePerTurnPower", 1));
            /*    */
        }
    }
    /*    */


}
/*    */


/* Location:              C:\Program Files (x86)\Steam\steamapps\common\SlayTheSpire\mods\SlimeboundMod.jar!\slimboundmod\powers\SearingPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */