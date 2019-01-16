/*    */ package slimebound.actions;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
/*    */ import com.megacrit.cardcrawl.actions.GameActionManager;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.EnergyManager;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
/*    */ 
/*    */ public class DistilledAgonyAction extends AbstractGameAction
/*    */ {
/* 15 */   private boolean freeToPlayOnce = false;
/*    */   private int damage;
/*    */   private int pain;
/*    */   private AbstractPlayer p;
/*    */   private AbstractMonster m;
/*    */   private DamageType damageTypeForTurn;
/* 21 */   private int energyOnUse = -1;
/*    */   
/*    */   public DistilledAgonyAction(AbstractPlayer p, AbstractMonster m, int damage, int pain, DamageType damageTypeForTurn, boolean freeToPlayOnce, int energyOnUse)
/*    */   {
/* 25 */     this.p = p;
/* 26 */     this.m = m;
/* 27 */     this.damage = damage;
/* 28 */     this.pain = pain;
/* 29 */     this.freeToPlayOnce = freeToPlayOnce;
/* 30 */     this.duration = com.megacrit.cardcrawl.core.Settings.ACTION_DUR_XFAST;
/* 31 */     this.actionType = ActionType.SPECIAL;
/* 32 */     this.damageTypeForTurn = damageTypeForTurn;
/* 33 */     this.energyOnUse = energyOnUse;
/*    */   }
/*    */   
/*    */   public void update()
/*    */   {
/* 38 */     int effect = EnergyPanel.totalCount;
/* 39 */     if (this.energyOnUse != -1) {
/* 40 */       effect = this.energyOnUse;
/*    */     }
/* 42 */     if (this.p.hasRelic("Chemical X"))
/*    */     {
/* 44 */       effect += 2;
/* 45 */       this.p.getRelic("Chemical X").flash();
/*    */     }
/* 47 */     if (effect > 0)
/*    */     {
/* 49 */       for (int i = 0; i < effect; i++) {
/* 50 */         AbstractDungeon.actionManager.addToBottom(new ConditionalLoseHPAction(this.p, this.p, this.pain, this.m));
/* 51 */         AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(this.m, new DamageInfo(this.p, this.damage, this.damageTypeForTurn), AttackEffect.BLUNT_LIGHT));
/*    */       }
/* 53 */       if (!this.freeToPlayOnce) {
/* 54 */         this.p.energy.use(EnergyPanel.totalCount);
/*    */       }
/*    */     }
/* 57 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              C:\Program Files (x86)\Steam\steamapps\common\SlayTheSpire\mods\SlimeboundMod.jar!\slimboundmod\actions\DistilledAgonyAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */