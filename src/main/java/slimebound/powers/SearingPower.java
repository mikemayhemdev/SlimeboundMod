/*    */ package slimebound.powers;
/*    */ 
/*    */

/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import slimebound.SlimeboundMod;
/*    */
        /*    */
        /*    */

/*    */
/*    */ public class SearingPower extends AbstractPower
/*    */ {
    /*    */
        /*    */   public static final String POWER_ID = "SearingPower";
        /*    */   public static final String NAME = "Potency";
        public static PowerType POWER_TYPE = PowerType.DEBUFF;
        /*    */   public static final String IMG = "powers/BurningS.png";
        public static final Logger logger = LogManager.getLogger(SlimeboundMod.class.getName()); // lets us log output

        /* 14 */   public static String[] DESCRIPTIONS;
        /*    */   private AbstractCreature source;

        /*    */
        /*    */
        /*    */
    public SearingPower(AbstractCreature owner, AbstractCreature source, int amount)
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
            this.img = new com.badlogic.gdx.graphics.Texture(slimebound.SlimeboundMod.getResourcePath(IMG));
            /* 29 */
            this.type = POWER_TYPE;
            /* 30 */
            this.amount = amount;
            DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(this.ID).DESCRIPTIONS;
            /*  84 */
            this.name = CardCrawlGame.languagePack.getPowerStrings(this.ID).NAME;
            /* 31 */
            updateDescription();
/*    */   }
/*    */   
/*    */   public void updateDescription()
/*    */   {
/* 36 *
/* 37 */       this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
/*    */   }
/*    */   
/*    */   public void atStartOfTurn()
/*    */   {
/* 45 */     if ((AbstractDungeon.getCurrRoom().phase == com.megacrit.cardcrawl.rooms.AbstractRoom.RoomPhase.COMBAT) && 
/* 46 */       (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()))
/*    */     {
/* 48 */       flashWithoutSound();
/* 49 */       AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.LoseHPAction(this.owner, this.source, this.amount, com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect.FIRE));
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Program Files (x86)\Steam\steamapps\common\SlayTheSpire\mods\SlimeboundMod.jar!\slimboundmod\powers\SearingPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */