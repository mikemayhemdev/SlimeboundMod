/*    */ package slimebound.powers;
/*    */
/*    */

/*    */

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import slimebound.SlimeboundMod;
import slimebound.actions.TendrilFlailAction;

/*    */
/*    */
        /*    */
        /*    */

/*    */
/*    */ public class SelfDamageSlimedPower extends AbstractPower
/*    */ {
    /*    */   public static final String POWER_ID = "SelfDamageSlimedPower";
    /*    */   public static final String NAME = "Potency";
                public static PowerType POWER_TYPE = PowerType.BUFF;
    /*    */   public static final String IMG = "powers/Malleable.png";
    public static final Logger logger = LogManager.getLogger(SlimeboundMod.class.getName()); // lets us log output

    /* 14 */   public static String[] DESCRIPTIONS;
    /*    */   private AbstractCreature source;
    private boolean active =  true;

    /*    */
    /*    */
    /*    */
    public SelfDamageSlimedPower(AbstractCreature owner, AbstractCreature source, int amount)
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
        this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
        /*    */
        /*    */
    }
    public int onAttacked(DamageInfo info, int damageAmount)
        /*    */   {
        /* 25 */     if ((AbstractDungeon.getCurrRoom().phase == com.megacrit.cardcrawl.rooms.AbstractRoom.RoomPhase.COMBAT) &&
                /* 26 */       (damageAmount > 0) && active) {
            /* 27 */       flash();
            AbstractDungeon.actionManager.addToTop(new TendrilFlailAction(this.owner,
                    /* 49 */           AbstractDungeon.getMonsters().getRandomMonster(true), 1,this.amount));
            /*    */                  /*    */     }

        /* 36 */     return damageAmount;
        /*    */   }

    public void atStartOfTurn(){
            active = true;
    }


    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) active = false;
    }
}
/*    */


/* Location:              C:\Program Files (x86)\Steam\steamapps\common\SlayTheSpire\mods\SlimeboundMod.jar!\slimboundmod\powers\SearingPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */