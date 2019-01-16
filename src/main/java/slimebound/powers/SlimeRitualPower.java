/*    */ package slimebound.powers;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.GameActionManager;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.LocalizedStrings;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

/*    */
/*    */ public class SlimeRitualPower extends AbstractPower
        /*    */ {
    /*    */   public static final String POWER_ID = "SlimeRitualPower";
    /*    */   public static final String NAME = "Slime Sacrifice";
    public static PowerType POWER_TYPE = PowerType.BUFF;
    /*    */   public static final String IMG = "powers/ritual.png";

    /* 14 */   public static String[] DESCRIPTIONS;
    /*    */   private AbstractCreature source;
    /*    */
    /*    */   public SlimeRitualPower(AbstractCreature owner, int strAmt) {
        /* 16 */
        this.name = NAME;
        /* 24 */
        this.ID = POWER_ID;
        this.amount = strAmt;
        /* 25 */
        this.owner = owner;
        /* 26 */
        /*    */
        /* 28 */
        this.img = new com.badlogic.gdx.graphics.Texture(slimebound.SlimeboundMod.getResourcePath(IMG));
        /* 29 */
        this.type = POWER_TYPE;
        /* 30 */
        DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(this.ID).DESCRIPTIONS;
        /*  84 */
        this.name = CardCrawlGame.languagePack.getPowerStrings(this.ID).NAME;
        /* 20 */
        updateDescription();
    }
/*    */   
/*    */   public void updateDescription()
/*    */   {
/* 28 */     this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
/*    */   }
/*    */   
/*    */   public void atStartOfTurn()
/*    */   {
/* 33 */
/* 35 */       com.megacrit.cardcrawl.dungeons.AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, this.amount), this.amount));
/*    */
/*    */   }
/*    */ }


/* Location:              C:\Users\Computer\IdeaProjects\lib\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\SlimeRitualPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */