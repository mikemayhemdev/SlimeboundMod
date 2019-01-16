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
/*    */ public class DuplicatedFormNoHealPower extends AbstractPower
/*    */ {
    /*    */   public static final String POWER_ID = "DuplicatedFormNoHealPower";
    /*    */   public static final String NAME = "Potency";
                public static PowerType POWER_TYPE = PowerType.DEBUFF;
    /*    */   public static final String IMG = "powers/HalvedS.png";
    public static final Logger logger = LogManager.getLogger(SlimeboundMod.class.getName()); // lets us log output

    /* 14 */   public static String[] DESCRIPTIONS;
    /*    */   private AbstractCreature source;
    /* 18 */   private int cardsDoubledThisTurn = 0;

    /*    */
    /*    */
    /*    */
    public DuplicatedFormNoHealPower(AbstractCreature owner, AbstractCreature source, int amount)
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
        this.description = (DESCRIPTIONS[0]);
        /*    */
        /*    */
    }



    public int onHeal(int healAmount)
        /*    */   {
        /* 23 */     if ((AbstractDungeon.currMapNode != null) && (AbstractDungeon.getCurrRoom().phase == com.megacrit.cardcrawl.rooms.AbstractRoom.RoomPhase.COMBAT)) {
            /* 24 */       flash();
                        int currentHealth = this.owner.currentHealth;
                        int maxHealth = this.owner.maxHealth;
                        double currentPct = currentHealth * 1.001 / maxHealth * 1.001;
                        logger.info("Current health: " + currentHealth);
                         logger.info("Max health: " + maxHealth);
                        logger.info("Current percentage: " + currentPct);
                            if(currentPct >= 0.5){
            /* 25 */       return MathUtils.round(healAmount * 0F);
                            }
                            else if(currentHealth + healAmount > maxHealth / 2) {
                            return (maxHealth / 2) - currentHealth;
                            }
                            else {return healAmount;}
            /*    */     }
        /* 27 */     return healAmount;
        /*    */   }

}
/*    */


/* Location:              C:\Program Files (x86)\Steam\steamapps\common\SlayTheSpire\mods\SlimeboundMod.jar!\slimboundmod\powers\SearingPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */