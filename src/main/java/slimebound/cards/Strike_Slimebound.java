/*    */ package slimebound.cards;
/*    */
import basemod.abstracts.CustomCard;
import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
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

/*    */
/*    */ public class Strike_Slimebound extends CustomCard
/*    */ {
/*    */   public static final String ID = "Strike_Slimebound";
/*    */   public static final String NAME = "Strike";
/*    */   public static final String DESCRIPTION = "Deal !D! damage.";
/*    */   public static final String IMG_PATH = "cards/attackSlime.png";
/* 19 */   private static final AbstractCard.CardType TYPE = AbstractCard.CardType.ATTACK;
/* 20 */   private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.BASIC;
/* 21 */   private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.ENEMY;

/*    */   private static final int COST = 1;
/*    */   private static final int POWER = 6;
/*    */   private static final int UPGRADE_BONUS = 3;

/*    */   public Strike_Slimebound()
/*    */   {
/* 29 */     super(ID, NAME, slimebound.SlimeboundMod.getResourcePath(IMG_PATH), COST, DESCRIPTION, TYPE, AbstractCardEnum.SLIMEBOUND, RARITY, TARGET);
/*    */     
/* 31 */     this.baseDamage = 6;
/* 32 */     this.tags.add(BaseModCardTags.BASIC_STRIKE);
/* 33 */     this.tags.add(AbstractCard.CardTags.STRIKE);
/*    */   }
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m)
/*    */   {
/* 38 */     com.megacrit.cardcrawl.dungeons.AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(m, new com.megacrit.cardcrawl.cards.DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
/*    */   }
/*    */   
/*    */ 
/*    */   public AbstractCard makeCopy()
/*    */   {
/* 44 */     return new Strike_Slimebound();
/*    */   }
/*    */   
/*    */   public void upgrade()
/*    */   {
/* 49 */     if (!this.upgraded)
/*    */     {
/* 51 */       upgradeName();
/* 52 */       upgradeDamage(3);
/*    */     }
/*    */   }


/*    */ }


/* Location:              C:\Program Files (x86)\Steam\steamapps\common\SlayTheSpire\mods\SlimeboundMod.jar!\slimboundmod\cards\Strike_Slimebound.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */