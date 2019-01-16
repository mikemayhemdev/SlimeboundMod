/*    */ package slimebound.cards;
/*    */

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.IntangiblePower;
import com.megacrit.cardcrawl.powers.RegenPower;
import slimebound.SlimeboundMod;
import slimebound.patches.AbstractCardEnum;

/*    */
/*    */ public class RapidHeal extends CustomCard
/*    */ {
/*    */   public static final String ID = "RapidHeal";
    /*    */   public static final String NAME;
    /*    */   public static final String DESCRIPTION;
    public static String UPGRADED_DESCRIPTION;
    /* 18 */   public static final String[] EXTENDED_DESCRIPTION;
/*    */   public static final String IMG_PATH = "cards/rapidheal.png";

/* 17 */   private static final CardType TYPE = CardType.SKILL;
/* 18 */   private static final CardRarity RARITY = CardRarity.COMMON;
/* 19 */   private static final CardTarget TARGET = CardTarget.SELF;
    /*    */                private static final CardStrings cardStrings;
/*    */
/*    */   private static final int COST = 0;
/*    */   private static final int BLOCK = 5;
/*    */   private static final int UPGRADE_BONUS = 3;
/*    */
/*    */   public RapidHeal()
/*    */   {
/* 27 */     super(ID, NAME, SlimeboundMod.getResourcePath(IMG_PATH), COST, DESCRIPTION, TYPE, AbstractCardEnum.SLIMEBOUND, RARITY, TARGET);

/*    */
/* 29 */     this.exhaust = true;
this.magicNumber = this.baseMagicNumber = 3;
/*    */   }


/*


/*    */   public void use(AbstractPlayer p, AbstractMonster m)
/*    */   {

    /*
    if (p.hasPower("Regeneration")) {
        int regenAmount = p.getPower("Regeneration").amount;
        int heal =  regenAmount * (1 + regenAmount)/2;
        heal = heal/2;

        AbstractDungeon.actionManager.addToBottom(new HealAction(p,p,heal));
        AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction(p,p, "Regeneration"));
*/

   // AbstractDungeon.actionManager.addToBottom(new HealAction(p,p,heal));
    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p,new RegenPower(p,this.magicNumber),this.magicNumber));
    if(upgraded){
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p,1));
    }

    }
/*    */   
/*    */   public AbstractCard makeCopy()
/*    */   {
/* 40 */     return new RapidHeal();
/*    */   }
/*    */   
/*    */   public void upgrade()
/*    */   {
/* 45 */     if (!this.upgraded)
/*    */     {
/* 47 */       upgradeName();
upgradeMagicNumber(1);
        this.rawDescription = UPGRADED_DESCRIPTION;
        this.initializeDescription();
/*    */     }
/*    */   }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
        UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
        EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
    }
/*    */ }


/* Location:              C:\Program Files (x86)\Steam\steamapps\common\SlayTheSpire\mods\SlimeboundMod.jar!\slimboundmod\cards\Defend_Slimebound.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */