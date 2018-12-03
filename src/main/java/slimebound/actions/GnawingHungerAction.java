/*    */ package slimebound.actions;
/*    */ 
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.CardGroup;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.helpers.GetAllInBattleInstances;
/*    */ import java.util.UUID;
/*    */ 
/*    */ public class GnawingHungerAction extends com.megacrit.cardcrawl.actions.AbstractGameAction
/*    */ {
/*    */   private int miscIncrease;
/*    */   private int moreDamage;
/*    */   private UUID uuid;
/*    */   
/*    */   public GnawingHungerAction(UUID targetUUID, int miscIncrease, int moreDamage)
/*    */   {
/* 18 */     this.miscIncrease = miscIncrease;
/*    */     
/* 20 */     this.moreDamage = moreDamage;
/*    */     
/* 22 */     this.uuid = targetUUID;
/*    */   }
/*    */   
/*    */   public void update()
/*    */   {
/* 27 */     for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
/* 28 */       if (c.uuid.equals(this.uuid))
/*    */       {
/* 30 */         c.misc += this.miscIncrease;
/* 31 */         c.applyPowers();
/* 32 */         c.baseDamage = (c.misc * 2 + this.moreDamage);
/* 33 */         c.baseMagicNumber = c.misc;
/*    */       }
/*    */     }
/* 36 */     for (AbstractCard c : GetAllInBattleInstances.get(this.uuid))
/*    */     {
/* 38 */       c.misc += this.miscIncrease;
/* 39 */       c.applyPowers();
/* 40 */       c.baseDamage = (c.misc * 2 + this.moreDamage);
/* 41 */       c.baseMagicNumber = c.misc;
/*    */     }
/* 43 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              C:\Program Files (x86)\Steam\steamapps\common\SlayTheSpire\mods\SlimeboundMod.jar!\slimboundmod\actions\GnawingHungerAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */