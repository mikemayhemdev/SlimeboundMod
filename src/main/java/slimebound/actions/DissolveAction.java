/*    */ package slimebound.actions;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
/*    */ import com.megacrit.cardcrawl.actions.GameActionManager;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.RegenPower;

/*    */
/*    */ public class DissolveAction extends com.megacrit.cardcrawl.actions.AbstractGameAction
/*    */ {
/*    */   private DamageInfo info;
/*    */   private float startingDuration;
            private int BlockGain;
            private int RegenGain;
/*    */   
/*    */   public DissolveAction(AbstractCreature target, int BlockGain, int RegenGain)
/*    */   {
/* 16 */     this.info = info;
/* 18 */     this.actionType = com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType.WAIT;
/* 20 */     this.startingDuration = com.megacrit.cardcrawl.core.Settings.ACTION_DUR_FAST;
/* 21 */     this.duration = this.startingDuration;
            this.BlockGain = BlockGain;
            this.RegenGain = RegenGain;
            }
/*    */   
/*    */   public void update()
/*    */   {
/* 26 */     if (this.duration == this.startingDuration) {
/* 27 */       int count = AbstractDungeon.player.hand.size();
/*    */       
/* 29 */       for (int i = 0; i < count; i++) {
/* 30 */         com.megacrit.cardcrawl.dungeons.AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, this.BlockGain));
            AbstractDungeon.actionManager.addToBottom(new HealAction(AbstractDungeon.player, AbstractDungeon.player, this.RegenGain));
/*    */       }
/* 32 */       for (int i = 0; i < count; i++) {
/* 33 */         AbstractDungeon.actionManager.addToTop(new com.megacrit.cardcrawl.actions.common.ExhaustAction(AbstractDungeon.player, AbstractDungeon.player, 1, true, true));
/*    */       }
/*    */     }
/*    */     
/*    */ 
/* 38 */     tickDuration();
/*    */   }
/*    */ }

