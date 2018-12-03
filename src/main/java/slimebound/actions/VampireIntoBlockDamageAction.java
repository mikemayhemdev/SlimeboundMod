/*    */ package slimebound.actions;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
/*    */ import com.megacrit.cardcrawl.actions.GameActionManager;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.helpers.Hitbox;
/*    */ import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*    */ 
/*    */ public class VampireIntoBlockDamageAction extends com.megacrit.cardcrawl.actions.AbstractGameAction
/*    */ {
/*    */   private DamageInfo info;
/*    */   
/*    */   public VampireIntoBlockDamageAction(AbstractCreature target, DamageInfo info, AbstractGameAction.AttackEffect effect)
/*    */   {
/* 18 */     this.info = info;
/* 19 */     setValues(target, info);
/* 20 */     this.actionType = AbstractGameAction.ActionType.DAMAGE;
/* 21 */     this.attackEffect = effect;
/*    */   }
/*    */   
/*    */   public void update()
/*    */   {
/* 26 */     if (this.duration == 0.5F) {
/* 27 */       AbstractDungeon.effectList.add(new com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, this.attackEffect));
/*    */     }
/*    */     
/* 30 */     tickDuration();
/*    */     
/* 32 */     if (this.isDone) {
/* 33 */       heal(this.info);
/* 34 */       this.target.damage(this.info);
/*    */       
/* 36 */       if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
/* 37 */         AbstractDungeon.actionManager.clearPostCombatActions();
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   private void heal(DamageInfo info)
/*    */   {
/* 48 */     int healAmount = info.output;
/* 49 */     if (healAmount < 0) {
/* 50 */       return;
/*    */     }
/*    */     
/* 53 */     healAmount -= this.target.currentBlock;
/*    */     
/* 55 */     if (healAmount > this.target.currentHealth) {
/* 56 */       healAmount = this.target.currentHealth;
/*    */     }
/*    */     
/* 59 */     if (healAmount > 0)
/*    */     {
/* 61 */       if ((healAmount > 1) && (this.target.hasPower("Buffer"))) {
/* 62 */         return;
/*    */       }
/*    */
/* 65 */       if ((healAmount > 1) && (this.target.hasPower("IntangiblePlayer"))) {
/* 66 */         healAmount = 1;
/*    */       }
/*    */       
/* 69 */       AbstractDungeon.actionManager.addToTop(new com.megacrit.cardcrawl.actions.common.GainBlockAction(this.source, this.source, healAmount));
/* 70 */       AbstractDungeon.actionManager.addToTop(new com.megacrit.cardcrawl.actions.utility.WaitAction(0.1F));
/*    */     }
/*    */   }
/*    */ }

