/*    */ package slimebound.actions;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
/*    */ import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.CardGroup;
/*    */ import com.megacrit.cardcrawl.cards.CardGroup.CardGroupType;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

/*    */
/*    */ public class SlimepotheosisAction extends com.megacrit.cardcrawl.actions.AbstractGameAction
/*    */ {
    public boolean upgraded;

    /*    */   public SlimepotheosisAction(boolean upgraded)
/*    */   {
/* 14 */     this.duration = Settings.ACTION_DUR_MED;
/* 15 */     this.actionType = AbstractGameAction.ActionType.WAIT;
this.upgraded=upgraded;
/*    */   }
/*    */   
/*    */   public void update()
/*    */   {
/* 20 */     if (this.duration == Settings.ACTION_DUR_MED) {
/* 21 */       AbstractPlayer p = com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;
/*    */       
/* 23 */       int healthloss = upgradeAllCardsInGroup(p.hand, upgraded);


        if (healthloss > 0) AbstractDungeon.actionManager.addToBottom(new LoseHPAction(AbstractDungeon.player, AbstractDungeon.player, healthloss));

        /* 28 */       this.isDone = true;
/*    */     }
/*    */   }
/*    */   
/*    */   private int upgradeAllCardsInGroup(CardGroup cardGroup, boolean upgradedV) {
            int cards = 0;
/* 33 */     for (AbstractCard c : cardGroup.group) {
/* 34 */       if (c.canUpgrade()) {
/* 35 */         if (cardGroup.type == CardGroup.CardGroupType.HAND) {
/* 36 */           c.superFlash();
/*    */         }
/* 38 */         c.upgrade();
/* 39 */         c.applyPowers();
                cards++;
                if(!upgradedV) cards++;
/*    */       }
/*    */     }
            return cards;
/*    */   }
/*    */ }
