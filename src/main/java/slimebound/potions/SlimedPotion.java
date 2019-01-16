/*    */ package slimebound.potions;
/*    */ 
/*    */

/*    */ import basemod.abstracts.CustomPotion;
import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.helpers.GameDictionary;
/*    */ import com.megacrit.cardcrawl.helpers.PowerTip;
/*    */ import com.megacrit.cardcrawl.localization.PotionStrings;
/*    */ import com.megacrit.cardcrawl.powers.PoisonPower;
import slimebound.powers.SlimedPower;
/*    */

/*    */
/*    */ public class SlimedPotion extends CustomPotion
/*    */ {
/*    */   public static final String POTION_ID = "SlimedPotion";
/* 15 */   private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString("SlimedPotion");
/* 16 */   public static final String NAME = potionStrings.NAME;
/* 17 */   public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;
/*    */   
/*    */ 
/*    */ 
/*    */   public SlimedPotion()
/*    */   {
/* 23 */     super(NAME, "SlimedPotion", PotionRarity.COMMON, PotionSize.BOTTLE, PotionColor.POISON);
/* 24 */     this.potency = getPotency();
/* 25 */     this.description = (DESCRIPTIONS[0] + this.potency + DESCRIPTIONS[1]);
/* 26 */     this.isThrown = true;
/* 27 */     this.targetRequired = true;
/* 28 */     this.tips.add(new PowerTip(this.name, this.description));
/* 29 */     this.tips.add(new PowerTip(
/*    */     
/* 31 */       com.megacrit.cardcrawl.helpers.TipHelper.capitalize(GameDictionary.POISON.NAMES[0]), 
/* 32 */       GameDictionary.keywords.get(GameDictionary.POISON.NAMES[0])));
/*    */   }
/*    */   
/*    */   public void use(AbstractCreature target)
/*    */   {
/* 37 */     AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.ApplyPowerAction(target, AbstractDungeon.player, new SlimedPower(target, AbstractDungeon.player, this.potency), this.potency));
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public CustomPotion makeCopy()
/*    */   {
/* 47 */     return new SlimedPotion();
/*    */   }
/*    */   
/*    */   public int getPotency(int ascensionLevel)
/*    */   {
/* 52 */     return 10;
/*    */   }
/*    */ }


/* Location:              C:\Users\Computer\IdeaProjects\lib\desktop-1.0.jar!\com\megacrit\cardcrawl\potions\SlimedPotion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */