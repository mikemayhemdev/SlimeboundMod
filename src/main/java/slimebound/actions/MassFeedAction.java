 package slimebound.actions;

 import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
 import com.megacrit.cardcrawl.actions.GameActionManager;
 import com.megacrit.cardcrawl.cards.DamageInfo;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.helpers.Hitbox;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.monsters.MonsterGroup;
 import com.megacrit.cardcrawl.rooms.AbstractRoom;
 import com.megacrit.cardcrawl.unlock.UnlockTracker;
 import com.megacrit.cardcrawl.vfx.combat.BiteEffect;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

 public class MassFeedAction extends com.megacrit.cardcrawl.actions.AbstractGameAction
 {
   private int increaseHpAmount;
   private DamageInfo info;
   private static final float DURATION = 0.1F;

   public MassFeedAction(AbstractCreature target, DamageInfo info, int maxHPAmount)
   {
     this.info = info;
     setValues(target, info);
     this.increaseHpAmount = maxHPAmount;
     this.actionType = AbstractGameAction.ActionType.DAMAGE;
     this.duration = 0.1F;
   }

   public void update()
   {
     if ((this.duration == 0.1F) &&
       (this.target != null)) {
       AbstractDungeon.effectList.add(new BiteEffect(this.target.hb.cX, this.target.hb.cY));

       this.target.damage(this.info);

       if (((((AbstractMonster)this.target).isDying) || (this.target.currentHealth <= 0)) && (!this.target.halfDead) &&
         (!this.target.hasPower("Minion"))) {
         AbstractDungeon.player.increaseMaxHp(this.increaseHpAmount, false);

       }

       if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
         AbstractDungeon.actionManager.clearPostCombatActions();
       }
     }


     tickDuration();
   }
 }

