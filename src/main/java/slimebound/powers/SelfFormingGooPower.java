/*    */ package slimebound.powers;
/*    */
/*    */

/*    */

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import slimebound.SlimeboundMod;

/*    */
/*    */
        /*    */
        /*    */

/*    */
/*    */ public class SelfFormingGooPower extends AbstractPower
/*    */ {
    /*    */   public static final String POWER_ID = "SelfFormingGooPower";
    /*    */   public static final String NAME = "Potency";
                public static PowerType POWER_TYPE = PowerType.BUFF;
    /*    */   public static final String IMG = "powers/Malleable.png";
    public static final Logger logger = LogManager.getLogger(SlimeboundMod.class.getName()); // lets us log output

    /* 14 */   public static String[] DESCRIPTIONS;
    /*    */   private AbstractCreature source;

    /*    */
    /*    */
    /*    */
    public SelfFormingGooPower(AbstractCreature owner, AbstractCreature source, int amount)
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

    /*    */
    /*    */
    public void updateDescription()
    /*    */ {
        /* 36 */
        /* 37 */
        this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
        /*    */
        /*    */
    }
    public int onAttacked(DamageInfo info, int damageAmount)
        /*    */   {
        /* 25 */     if ((AbstractDungeon.getCurrRoom().phase == com.megacrit.cardcrawl.rooms.AbstractRoom.RoomPhase.COMBAT) &&
                /* 26 */       (damageAmount > 0)) {
            /* 27 */       flash();
            /* 28 */       AbstractDungeon.actionManager.addToTop(new com.megacrit.cardcrawl.actions.common.ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new com.megacrit.cardcrawl.powers.NextTurnBlockPower(AbstractDungeon.player, this.amount, this.name), this.amount));
            /*    */     }

        /* 36 */     return damageAmount;
        /*    */   }
}
/*    */


/* Location:              C:\Program Files (x86)\Steam\steamapps\common\SlayTheSpire\mods\SlimeboundMod.jar!\slimboundmod\powers\SearingPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */