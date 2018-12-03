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
import com.megacrit.cardcrawl.vfx.FireBurstParticleEffect;
import com.megacrit.cardcrawl.vfx.combat.WeakParticleEffect;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import slimebound.SlimeboundMod;



        /*    */
        /*    */

/*    */
/*    */ public class SlimedPower extends AbstractPower
        /*    */ {
    /*    */   public static final String POWER_ID = "SlimedPower";
    /*    */   public static final String NAME = "UsefulSlime";
    public static PowerType POWER_TYPE = PowerType.DEBUFF;
    /*    */   public static final String IMG = "powers/SlimedS.png";
                public boolean doubleUp = false;
    public static final Logger logger = LogManager.getLogger(SlimeboundMod.class.getName()); // lets us log output
    /* 14 */   public static String[] DESCRIPTIONS;
    /*    */   private AbstractCreature source;
                public boolean triggered = false;

    /*    */
    /*    */
    /*    */
    public SlimedPower(AbstractCreature owner, AbstractCreature source, int amount)
    /*    */ {
        /* 23 */
        this.name = NAME;
        /* 24 */
        this.ID = POWER_ID;
        this.amount = amount;
        /* 25 */
        this.owner = owner;
        /* 26 */
        this.source = source;
        /*    */
        /* 28 */
        this.img = new com.badlogic.gdx.graphics.Texture(slimebound.SlimeboundMod.getResourcePath(IMG));
        /* 29 */
        this.type = POWER_TYPE;
        /* 30 */
        this.DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(this.ID).DESCRIPTIONS;
        /*  84 */
        this.name = CardCrawlGame.languagePack.getPowerStrings(this.ID).NAME;
        /* 31 */
        updateDescription();
        /*    */
        /*    */
    }

    /*    */
    /*    */
    public void updateDescription()
    /*    */ {
        /* 36 */
        this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2]);
        /*    */
        /*    */
        /*    */
    }

    /*    */
    /*    */
    public void atEndOfRound()
    /*    */ {
        /* 38 */
        /* 42 */    // if (AbstractDungeon.player.hasRelic("WalkingCane")) {
        /* 43 */      // AbstractDungeon.player.getRelic("WalkingCane").flash();
        /* 44 */     //  return;
        /*    */    // }
        /* 46 */
        if (this.amount <= 1) {
            /* 47 */
            AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction(this.owner, this.owner, "SlimedPower"));
            /*    */
        } else {
            /* 49 */
            AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.ReducePowerAction(this.owner, this.owner, "SlimedPower", 1));
            /*    */
        }
        /*    */
    }


    public float atDamageFinalReceive(float damage, DamageInfo.DamageType damageType)
    /*    */ {
        /* 55 */
        if (damageType == DamageInfo.DamageType.NORMAL) {



                return damage + this.amount;

        }
        /* 58 */
        return damage;
        /*    */
    }



    public int onAttacked(DamageInfo info, int damageAmount) {
        if (!this.triggered) {
            if (info.type == DamageInfo.DamageType.NORMAL) {
                this.triggered = true;
                AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.HealAction(this.source, this.source, this.amount));
                AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction(this.owner, this.owner, "SlimedPower"));
            }


        }
        return super.onAttacked(info, damageAmount);
    }


}

    /*    */
/*    */


/* Location:              C:\Program Files (x86)\Steam\steamapps\common\SlayTheSpire\mods\SlimeboundMod.jar!\slimboundmod\powers\SearingPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */