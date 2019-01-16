/*    */ package slimebound.actions;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
/*    */ import com.megacrit.cardcrawl.actions.GameActionManager;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.helpers.Hitbox;
/*    */ import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*    */ import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
/*    */ 
/*    */ public class ConditionalLoseHPAction extends AbstractGameAction
/*    */ {
/*    */   private static final float DURATION = 0.33F;
/*    */   private AbstractCreature check;
/*    */   
/*    */   public ConditionalLoseHPAction(AbstractCreature target, AbstractCreature source, int amount, AbstractCreature check)
/*    */   {
/* 19 */     this(target, source, amount, check, AttackEffect.NONE);
/*    */   }
/*    */   
/*    */   public ConditionalLoseHPAction(AbstractCreature target, AbstractCreature source, int amount, AbstractCreature check, AttackEffect effect)
/*    */   {
/* 24 */     setValues(target, source, amount);
/* 25 */     this.check = check;
/* 26 */     this.actionType = ActionType.DAMAGE;
/* 27 */     this.attackEffect = effect;
/* 28 */     this.duration = 0.33F;
/*    */   }
/*    */   
/*    */   public void update()
/*    */   {
/* 33 */     if ((this.duration == 0.33F) && (this.target.currentHealth > 0) && (this.check.currentHealth > 0))
/*    */     {
/* 35 */       this.target.damageFlash = true;
/* 36 */       this.target.damageFlashFrames = 4;
/* 37 */       AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, this.attackEffect));
/*    */     }
/* 39 */     tickDuration();
/* 40 */     if (this.isDone)
/*    */     {
/* 42 */       if (this.check.currentHealth > 0) {
/* 43 */         this.target.damage(new com.megacrit.cardcrawl.cards.DamageInfo(this.source, this.amount, com.megacrit.cardcrawl.cards.DamageInfo.DamageType.HP_LOSS));
/*    */       }
/* 45 */       if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
/* 46 */         AbstractDungeon.actionManager.clearPostCombatActions();
/*    */       }
/* 48 */       AbstractDungeon.actionManager.addToTop(new com.megacrit.cardcrawl.actions.utility.WaitAction(0.1F));
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Program Files (x86)\Steam\steamapps\common\SlayTheSpire\mods\SlimeboundMod.jar!\slimboundmod\actions\ConditionalLoseHPAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */