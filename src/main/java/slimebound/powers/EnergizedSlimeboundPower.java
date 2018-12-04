/*    */ package slimebound.powers;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.GameActionManager;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import slimebound.SlimeboundMod;

/*    */
/*    */ public class EnergizedSlimeboundPower extends AbstractPower
/*    */ {
/*    */      /*    */   public static final String POWER_ID = "EnergizedSlimeboundPower";
    /*    */   public static final String NAME = "Potency";
    public static PowerType POWER_TYPE = PowerType.BUFF;
    /*    */   public static final String IMG = "powers/EnergyNextTurnS.png";
    public static final Logger logger = LogManager.getLogger(SlimeboundMod.class.getName()); // lets us log output

    /* 14 */   public static String[] DESCRIPTIONS;
    /*    */   private AbstractCreature source;

    /*    */
    /*    */
    /*    */
    public EnergizedSlimeboundPower(AbstractCreature owner, AbstractCreature source, int amount)
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
/* 24 */
/*    */   }
/*    */   
/*    */   public void stackPower(int stackAmount)
/*    */   {
/* 29 */     super.stackPower(stackAmount);
/* 30 */     if (this.amount >= 999) {
/* 31 */       this.amount = 999;
/*    */     }
/*    */   }
/*    */   
/*    */   public void updateDescription()
/*    */   {
/* 37 */     if (this.amount == 1) {
/* 38 */       this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
/*    */     } else {
/* 40 */       this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2]);
/*    */     }
/*    */   }
/*    */   
/*    */   public void onEnergyRecharge()
/*    */   {
/* 46 */     flash();
/* 47 */     AbstractDungeon.player.gainEnergy(this.amount);
/* 48 */     AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction(this.owner, this.owner, "EnergizedSlimeboundPower"));
/*    */   }
/*    */ }


/* Location:              C:\Users\Computer\IdeaProjects\lib\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\EnergizedSlimeboundPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */