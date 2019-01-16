/*    */ package slimebound.actions;
/*    */ 
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.CardGroup;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.screens.select.HandCardSelectScreen;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class DelusionAction extends com.megacrit.cardcrawl.actions.AbstractGameAction
/*    */ {
/*    */   private AbstractPlayer p;
/* 13 */   private static String[] TEXT = { "Choose a card to echo." };
/* 14 */   private ArrayList<AbstractCard> etherealCards = new ArrayList();
/*    */   
/*    */   public DelusionAction(AbstractPlayer p, int amount) {
/* 17 */     this.p = p;
/* 18 */     this.amount = amount;
/* 19 */     this.actionType = ActionType.CARD_MANIPULATION;
/* 20 */     this.duration = com.megacrit.cardcrawl.core.Settings.ACTION_DUR_FAST;
/*    */   }
/*    */   
/*    */   public void update() {
/* 24 */     if (this.duration == com.megacrit.cardcrawl.core.Settings.ACTION_DUR_FAST) {
/* 25 */       for (AbstractCard c : this.p.hand.group) {
/* 26 */         if (c.isEthereal) {
/* 27 */           this.etherealCards.add(c);
/*    */         }
/*    */       }
/* 30 */       if (this.p.hand.size() == this.etherealCards.size()) {
/* 31 */         this.isDone = true;
/* 32 */         return;
/*    */       }
/* 34 */       if (this.p.hand.size() - this.etherealCards.size() == 1) {
/* 35 */         for (AbstractCard c : this.p.hand.group) {
/* 36 */           if (!c.isEthereal) {
/* 37 */             AbstractDungeon.actionManager.addToBottom(new MakeEchoAction(c, this.amount));
/*    */             
/* 39 */             this.isDone = true;
/* 40 */             return;
/*    */           }
/*    */         }
/*    */       }
/* 44 */       this.p.hand.group.removeAll(this.etherealCards);
/* 45 */       if (this.p.hand.group.size() > 1)
/*    */       {
/* 47 */         AbstractDungeon.handCardSelectScreen.open(TEXT[0], 1, false, false, false, true);
/* 48 */         tickDuration();
/* 49 */         return;
/*    */       }
/* 51 */       if (this.p.hand.group.size() == 1)
/*    */       {
/* 53 */         returnCards();
/* 54 */         AbstractDungeon.actionManager.addToBottom(new MakeEchoAction(this.p.hand
/* 55 */           .getTopCard(), this.amount));
/* 56 */         this.isDone = true;
/*    */       }
/*    */     }
/* 59 */     if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved)
/*    */     {
/* 61 */       returnCards();
/* 62 */       for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
/* 63 */         AbstractDungeon.actionManager.addToBottom(new MakeEchoAction(c, this.amount));
/*    */         
/* 65 */         this.p.hand.addToTop(c);
/*    */       }
/* 67 */       AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
/* 68 */       AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
/* 69 */       this.isDone = true;
/*    */     }
/* 71 */     tickDuration();
/*    */   }
/*    */   
/*    */   private void returnCards() {
/* 75 */     for (AbstractCard c : this.etherealCards) {
/* 76 */       this.p.hand.addToTop(c);
/*    */     }
/* 78 */     this.p.hand.refreshHandLayout();
/*    */   }
/*    */ }


/* Location:              C:\Program Files (x86)\Steam\steamapps\common\SlayTheSpire\mods\SlimeboundMod.jar!\slimboundmod\actions\DelusionAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */