/*    */ package slimebound.actions;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.GameActionManager;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import slimebound.powers.SlimedPower;

/*    */
/*    */ public class DoublePoisonSlimedWeakAction extends com.megacrit.cardcrawl.actions.AbstractGameAction
/*    */ {
/*    */   private float startingDuration;
/*    */   
/*    */   public DoublePoisonSlimedWeakAction(AbstractCreature target, AbstractCreature source)
/*    */   {
/* 14 */     this.target = target;
/* 15 */     this.source = source;
/* 16 */     this.startingDuration = com.megacrit.cardcrawl.core.Settings.ACTION_DUR_FAST;
/* 17 */     this.actionType = com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType.DEBUFF;
/* 18 */     this.attackEffect = com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect.FIRE;
/* 19 */     this.duration = this.startingDuration;
/*    */   }
/*    */   
/*    */   public void update()
/*    */   {
/* 24 */     if ((this.duration == this.startingDuration) && 
/* 25 */       (this.target != null) && (this.target.hasPower("Poison"))) {
/* 26 */       com.megacrit.cardcrawl.dungeons.AbstractDungeon.actionManager.addToTop(new com.megacrit.cardcrawl.actions.common.ApplyPowerAction(this.target, this.source, new PoisonPower(this.target, this.source, 
/*    */       
/*    */ 
/*    */ 
/* 30 */         this.target.getPower("Poison").amount), 
/* 31 */         this.target.getPower("Poison").amount));
/*    */     }
    if ((this.duration == this.startingDuration) &&
            /* 25 */       (this.target != null) && (this.target.hasPower("Weakened"))) {
        /* 26 */       com.megacrit.cardcrawl.dungeons.AbstractDungeon.actionManager.addToTop(new com.megacrit.cardcrawl.actions.common.ApplyPowerAction(this.target, this.source, new WeakPower(this.target,
                /*    */
                /*    */
                /* 30 */         this.target.getPower("Weakened").amount, false),
                /* 31 */         this.target.getPower("Weakened").amount));
        /*    */     }
    if ((this.duration == this.startingDuration) &&
            /* 25 */       (this.target != null) && (this.target.hasPower("SlimedPower"))) {
        /* 26 */       com.megacrit.cardcrawl.dungeons.AbstractDungeon.actionManager.addToTop(new com.megacrit.cardcrawl.actions.common.ApplyPowerAction(this.target, this.source, new SlimedPower(this.target, this.source,
                /*    */
                /*    */
                /*    */
                /* 30 */         this.target.getPower("SlimedPower").amount),
                /* 31 */         this.target.getPower("SlimedPower").amount));
        /*    */     }
/*    */     
/*    */ 
/* 35 */     tickDuration();
/*    */   }
/*    */ }

