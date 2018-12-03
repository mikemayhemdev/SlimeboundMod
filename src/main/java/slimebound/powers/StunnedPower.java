/*    */ package slimebound.powers;
/*    */
/*    */

/*    */

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ThornsPower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import slimebound.SlimeboundMod;
import com.megacrit.cardcrawl.vfx.cardManip.ExhaustCardEffect;
import java.util.Iterator;

/*    */
/*    */
        /*    */
        /*    */

/*    */
/*    */ public class StunnedPower extends AbstractPower
/*    */ {
    /*    */   public static final String POWER_ID = "StunnedPower";
    /*    */   public static final String NAME = "Potency";
    public static PowerType POWER_TYPE = PowerType.DEBUFF;
    /*    */   public static final String IMG = "powers/SleepSmall.png";
    public static final Logger logger = LogManager.getLogger(SlimeboundMod.class.getName()); // lets us log output

    /* 14 */   public static String[] DESCRIPTIONS;
    /*    */   private AbstractCreature source;
    public int storedHandSize;

    /*    */
    /*    */
    /*    */
    public StunnedPower(AbstractCreature owner, AbstractCreature source, int amount)
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

    public void atEndOfRound() {
        if (this.amount == 0) {
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "StunnedPower"));
        } else {
            AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, "StunnedPower", 1));
        }

    }

    public void atStartOfTurn() {
        super.atStartOfTurn();
        this.storedHandSize = AbstractDungeon.player.gameHandSize;
        AbstractDungeon.player.gameHandSize = 0;
        AbstractDungeon.actionManager.cardQueue.clear();
        Iterator var1 = AbstractDungeon.player.limbo.group.iterator();

        while(var1.hasNext()) {
            AbstractCard c = (AbstractCard)var1.next();
            AbstractDungeon.effectList.add(new ExhaustCardEffect(c));
        }

        AbstractDungeon.player.limbo.group.clear();
        AbstractDungeon.player.releaseCard();
        AbstractDungeon.overlayMenu.endTurnButton.disable(true);
    }

    public void atStartOfTurnPostDraw() {
        super.atStartOfTurnPostDraw();
        AbstractDungeon.player.gameHandSize = this.storedHandSize;
    }
    /*    */
    /*    */
    public void updateDescription()
    /*    */ {
        /* 36 */
        /* 37 */
        if (this.amount <= 1) {
            /* 41 */       this.description = DESCRIPTIONS[0];
            /*    */     } else {
            /* 43 */       this.description = DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
            /*    */     }
        /*    */
        /*    */
    }


    /*    */



}
/*    */


/* Location:              C:\Program Files (x86)\Steam\steamapps\common\SlayTheSpire\mods\SlimeboundMod.jar!\slimboundmod\powers\SearingPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */