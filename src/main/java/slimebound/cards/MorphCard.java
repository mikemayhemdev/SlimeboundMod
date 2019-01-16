/*    */ package slimebound.cards;
/*    */ 
/*    */

/*    */ import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */
        /*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
import slimebound.SlimeboundMod;
import slimebound.actions.MorphCardAction;
import slimebound.patches.AbstractCardEnum;

/*    */
/*    */ public class MorphCard extends CustomCard
/*    */ {
    /*    */   public static final String ID = "MorphCard";
    /*    */   public static final String NAME;
    /*    */   public static final String DESCRIPTION;
    public static String UPGRADED_DESCRIPTION;
    /* 18 */   public static final String[] EXTENDED_DESCRIPTION;
    /*    */   public static final String IMG_PATH = "cards/overexertion.png";

    /* 17 */   private static final CardType TYPE = CardType.SKILL;
    /* 18 */   private static final CardRarity RARITY = CardRarity.UNCOMMON;
    /* 19 */   private static final CardTarget TARGET = CardTarget.SELF;
    /*    */                private static final CardStrings cardStrings;
    /*    */
    /*    */   private static final int COST = 0;
    /*    */   private static final int BLOCK = 5;
    /*    */   private static final int UPGRADE_BONUS = 3;
    /*    */
/*    */   public MorphCard() {
/* 21 */     super(ID, NAME, SlimeboundMod.getResourcePath(IMG_PATH), COST, DESCRIPTION, TYPE, AbstractCardEnum.SLIMEBOUND, RARITY, TARGET);

    /*    */
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 32 */     this.baseMagicNumber = 1;
/* 33 */     this.magicNumber = this.baseMagicNumber;
/* 34 */     this.exhaust = true;
/*    */   }
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m)
/*    */   {
/* 39 */     com.megacrit.cardcrawl.dungeons.AbstractDungeon.actionManager.addToBottom(new MorphCardAction(p, p, this.magicNumber, false, true,false));
/*    */   }
/*    */   
/*    */   public AbstractCard makeCopy()
/*    */   {
/* 44 */     return new MorphCard();
/*    */   }
/*    */   
/*    */   public void upgrade()
/*    */   {
/* 49 */     if (!this.upgraded) {
/* 50 */       upgradeName();
/* 51 */       upgradeMagicNumber(1);
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


/* Location:              C:\Users\Computer\IdeaProjects\lib\desktop-1.0.jar!\com\megacrit\cardcrawl\cards\colorless\MorphCard.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */