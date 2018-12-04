 package slimebound.actions;

 import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
 import com.megacrit.cardcrawl.actions.GameActionManager;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.RegenPower;


 public class DissolveAction extends com.megacrit.cardcrawl.actions.AbstractGameAction
 {
   private DamageInfo info;
   private float startingDuration;
            private int BlockGain;
            private int RegenGain;

   public DissolveAction(AbstractCreature target, int BlockGain, int RegenGain)
   {
     this.info = info;
     this.actionType = com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType.WAIT;
     this.startingDuration = com.megacrit.cardcrawl.core.Settings.ACTION_DUR_FAST;
     this.duration = this.startingDuration;
            this.BlockGain = BlockGain;
            this.RegenGain = RegenGain;
            }

   public void update()
   {
     if (this.duration == this.startingDuration) {
       int count = AbstractDungeon.player.hand.size();

       for (int i = 0; i < count; i++) {
         com.megacrit.cardcrawl.dungeons.AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, this.BlockGain));
            AbstractDungeon.actionManager.addToBottom(new HealAction(AbstractDungeon.player, AbstractDungeon.player, this.RegenGain));
       }
       for (int i = 0; i < count; i++) {
         AbstractDungeon.actionManager.addToTop(new com.megacrit.cardcrawl.actions.common.ExhaustAction(AbstractDungeon.player, AbstractDungeon.player, 1, true, true));
       }
     }


     tickDuration();
   }
 }

