/*    */ package slimebound.cards;
/*    */

import basemod.abstracts.CustomCard;
import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ThornsPower;
import slimebound.SlimeboundMod;
import slimebound.patches.AbstractCardEnum;
import slimebound.powers.LoseThornsPower;
import slimebound.powers.PotencyPower;

/*    */
/*    */ public class QuickSpikes extends CustomCard
/*    */ {
/*    */   public static final String ID = "QuickSpikes";
    /*    */   public static final String NAME;
    /*    */   public static final String DESCRIPTION;
    public static String UPGRADED_DESCRIPTION;
/*    */   public static final String IMG_PATH = "cards/quickspikes.png";

/* 17 */   private static final CardType TYPE = CardType.SKILL;
/* 18 */   private static final CardRarity RARITY = CardRarity.COMMON;
/* 19 */   private static final CardTarget TARGET = CardTarget.SELF;
    /*    */                private static final CardStrings cardStrings;
/*    */
/*    */   private static final int COST = 1;
/*    */   private static final int BLOCK = 5;
/*    */   private static final int UPGRADE_BONUS = 3;
/*    */
/*    */   public QuickSpikes()
/*    */   {
/* 27 */     super(ID, NAME, SlimeboundMod.getResourcePath(IMG_PATH), COST, DESCRIPTION, TYPE, AbstractCardEnum.SLIMEBOUND, RARITY, TARGET);

/*    */
/* 29 */     this.baseBlock = 7;
            this.magicNumber = this.baseMagicNumber = 3;
/*    */   }
/*    */
/*    */   public void use(AbstractPlayer p, AbstractMonster m)
/*    */   {
/* 35 */     AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.GainBlockAction(p, p, this.block));
             AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new ThornsPower(p, this.magicNumber), this.magicNumber));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new LoseThornsPower(p, p, this.magicNumber), this.magicNumber));
/*    */   }
/*    */   
/*    */   public AbstractCard makeCopy()
/*    */   {
/* 40 */     return new QuickSpikes();
/*    */   }
/*    */   
/*    */   public void upgrade()
/*    */   {
/* 45 */     if (!this.upgraded)
/*    */     {
/* 47 */       upgradeName();
/* 48 */       upgradeBlock(3);
                upgradeMagicNumber(2);
/*    */     }
/*    */   }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
        UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    }
/*    */ }


/* Location:              C:\Program Files (x86)\Steam\steamapps\common\SlayTheSpire\mods\SlimeboundMod.jar!\slimboundmod\cards\Defend_Slimebound.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */