/*    */ package slimebound.cards;
/*    */

import basemod.abstracts.CustomCard;
import basemod.helpers.BaseModCardTags;
import slimebound.SlimeboundMod;
import slimebound.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardRarity;
import com.megacrit.cardcrawl.cards.AbstractCard.CardTarget;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.ThornsPower;
/*    */ 
/*    */ public class Defend_Slimebound extends CustomCard
/*    */ {
/*    */   public static final String ID = "Defend_Slimebound";
/*    */   public static final String NAME = "Defend";
/*    */   public static final String DESCRIPTION = "Gain !B! Block.";
/*    */   public static final String IMG_PATH = "cards/defendSlime.png";

/* 17 */   private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;
/* 18 */   private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.BASIC;
/* 19 */   private static final AbstractCard.CardTarget TARGET = CardTarget.SELF;
/*    */   
/*    */   private static final int COST = 1;
/*    */   private static final int BLOCK = 5;
/*    */   private static final int UPGRADE_BONUS = 3;
/*    */   
/*    */   public Defend_Slimebound()
/*    */   {
/* 27 */     super(ID, NAME, slimebound.SlimeboundMod.getResourcePath(IMG_PATH), COST, DESCRIPTION, TYPE, AbstractCardEnum.SLIMEBOUND, RARITY, TARGET);

/*    */     
/* 29 */     this.baseBlock = 5;
/* 30 */     this.tags.add(BaseModCardTags.BASIC_DEFEND);
/*    */   }
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m)
/*    */   {
/* 35 */     com.megacrit.cardcrawl.dungeons.AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.GainBlockAction(p, p, this.block));
/*    */   }
/*    */   
/*    */   public AbstractCard makeCopy()
/*    */   {
/* 40 */     return new Defend_Slimebound();
/*    */   }
/*    */   
/*    */   public void upgrade()
/*    */   {
/* 45 */     if (!this.upgraded)
/*    */     {
/* 47 */       upgradeName();
/* 48 */       upgradeBlock(3);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Program Files (x86)\Steam\steamapps\common\SlayTheSpire\mods\SlimeboundMod.jar!\slimboundmod\cards\Defend_Slimebound.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */