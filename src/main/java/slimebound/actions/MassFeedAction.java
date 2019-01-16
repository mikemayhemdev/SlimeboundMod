/*    */ package slimebound.actions;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
/*    */ import com.megacrit.cardcrawl.actions.GameActionManager;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.helpers.Hitbox;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.monsters.MonsterGroup;
/*    */ import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*    */ import com.megacrit.cardcrawl.unlock.UnlockTracker;
/*    */ import com.megacrit.cardcrawl.vfx.combat.BiteEffect;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
/*    */ 
/*    */ public class MassFeedAction extends com.megacrit.cardcrawl.actions.AbstractGameAction
/*    */ {
/*    */   private int increaseHpAmount;
/*    */   private DamageInfo info;
/*    */   private static final float DURATION = 0.1F;
/*    */   
/*    */   public MassFeedAction(AbstractCreature target, DamageInfo info, int maxHPAmount)
/*    */   {
/* 23 */     this.info = info;
/* 24 */     setValues(target, info);
/* 25 */     this.increaseHpAmount = maxHPAmount;
/* 26 */     this.actionType = AbstractGameAction.ActionType.DAMAGE;
/* 27 */     this.duration = 0.1F;
/*    */   }
/*    */   
/*    */   public void update()
/*    */   {
/* 32 */     if ((this.duration == 0.1F) && 
/* 33 */       (this.target != null)) {
/* 34 */       AbstractDungeon.effectList.add(new BiteEffect(this.target.hb.cX, this.target.hb.cY));
/*    */       
/* 36 */       this.target.damage(this.info);
/*    */       
/* 38 */       if (((((AbstractMonster)this.target).isDying) || (this.target.currentHealth <= 0)) && (!this.target.halfDead) && 
/* 39 */         (!this.target.hasPower("Minion"))) {
/* 40 */         AbstractDungeon.player.increaseMaxHp(this.increaseHpAmount, false);

/*    */       }
/*    */       
/* 48 */       if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
/* 49 */         AbstractDungeon.actionManager.clearPostCombatActions();
/*    */       }
/*    */     }
/*    */     
/*    */ 
/* 54 */     tickDuration();
/*    */   }
/*    */ }

