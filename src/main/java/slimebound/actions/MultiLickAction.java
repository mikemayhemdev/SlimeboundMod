/*    */ package slimebound.actions;
/*    */ 
/*    */

/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import com.megacrit.cardcrawl.vfx.combat.WhirlwindEffect;
import slimebound.powers.SlimedPower;

/*    */
/*    */

/*    */
/*    */ public class MultiLickAction extends AbstractGameAction
/*    */ {
    /*    */   private DamageInfo info;
    /*    */   private static final float DURATION = 0.01F;
    /*    */   private static final float POST_ATTACK_WAIT_DUR = 0.2F;
    /*    */   private int numTimes;
    private int energyOnUse = -1;
    private AbstractPlayer p;

    /*    */
    /*    */
    public MultiLickAction(AbstractPlayer player, AbstractCreature target, DamageInfo info, int energyOnuse)
    /*    */ {
        /* 17 */
        this.info = info;
        /* 18 */
        this.target = target;
        /* 19 */
        this.actionType = ActionType.POWER;
        /* 20 */
        this.attackEffect = AttackEffect.POISON;
        /* 21 */
        this.duration = 0.01F;
        /* 22 */
        this.numTimes = numTimes;
        this.p = player;
        this.energyOnUse = energyOnUse;
        /*    */
    }

    /*    */
    /*    */
    public void update()
    /*    */ {

        int effect = EnergyPanel.totalCount;
        if (this.energyOnUse != -1) {
            effect = this.energyOnUse;
        }

        if (this.p.hasRelic("Chemical X")) {
            effect += 2;
            this.p.getRelic("Chemical X").flash();
        }

        if (effect > 0) {
            for (int i = 0; i < effect; ++i) {

                    if (this.target.currentHealth > 0) {
                        /* 39 */
                        AbstractDungeon.actionManager.addToBottom(new DamageAction(target, this.info, AbstractGameAction.AttackEffect.BLUNT_HEAVY));


                }

                }
                for (int i2 = 0; i2 < effect; ++i2) {

                        AbstractDungeon.actionManager.addToBottom(new RandomLickCardAction(false));







                }
            this.p.energy.use(EnergyPanel.totalCount);

                /* 27 */
            }
        this.isDone = true;
        }
    }


