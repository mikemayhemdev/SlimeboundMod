package slimebound.actions;

import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import slimebound.orbs.SpawnedSlime;

public class CoordinateAction extends com.megacrit.cardcrawl.actions.AbstractGameAction {
    private boolean freeToPlayOnce = false;
    private int damage;
    private AbstractPlayer p;
    private AbstractMonster m;
    private DamageInfo.DamageType damageTypeForTurn;
    private int energyOnUse = -1;


    public CoordinateAction(AbstractPlayer p, AbstractMonster m, int damage, DamageInfo.DamageType damageTypeForTurn, boolean freeToPlayOnce, int energyOnUse) {
        this.p = p;
        this.m = m;
        this.damage = damage;
        this.freeToPlayOnce = freeToPlayOnce;
        this.duration = com.megacrit.cardcrawl.core.Settings.ACTION_DUR_XFAST;
        this.actionType = ActionType.DAMAGE;
        this.damageTypeForTurn = damageTypeForTurn;
        this.energyOnUse = energyOnUse;
    }

    public void update() {
        int effect = EnergyPanel.totalCount;
        if (this.energyOnUse != -1) {
            effect = this.energyOnUse;
        }

        if (this.p.hasRelic("Chemical X")) {
            effect += 2;
            this.p.getRelic("Chemical X").flash();
        }

        if (effect > 0) {
            for (int i = 0; i < effect; i++) {
                AbstractDungeon.actionManager.addToTop(new com.megacrit.cardcrawl.actions.common.DamageAction(this.m, new DamageInfo(this.p, this.damage, this.damageTypeForTurn), com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect.BLUNT_LIGHT));

            }

            AbstractOrb oldestOrb = null;
            for (AbstractOrb o : p.orbs) {
                if (o instanceof SpawnedSlime) {
                    oldestOrb = o;
                    break;
                }

            }
            if (oldestOrb != null) {
                for (int i = 0; i < effect; i++) {
                    com.megacrit.cardcrawl.dungeons.AbstractDungeon.actionManager.addToBottom(new TrigggerSpecificSlimeAttackAction(oldestOrb));


                }
                com.megacrit.cardcrawl.dungeons.AbstractDungeon.actionManager.addToBottom(new WaitAction(0.4F * effect));


                com.megacrit.cardcrawl.dungeons.AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.defect.EvokeOrbAction(1));
            }


            if (!this.freeToPlayOnce) {
                this.p.energy.use(EnergyPanel.totalCount);
            }
        }
        this.isDone = true;
    }
}
