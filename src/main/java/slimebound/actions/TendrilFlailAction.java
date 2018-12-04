package slimebound.actions;


import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import slimebound.powers.SlimedPower;


public class TendrilFlailAction extends com.megacrit.cardcrawl.actions.AbstractGameAction {

    private static final float DURATION = 0.01F;
    private AbstractCreature owner;
    private static final float POST_ATTACK_WAIT_DUR = 0.2F;
    private int numTimes;
    private int slimed;

    public TendrilFlailAction(AbstractCreature player, AbstractCreature target, int numTimes, int slimed) {

        this.owner = player;
        this.target = target;
        this.actionType = ActionType.POWER;
        this.attackEffect = AttackEffect.POISON;
        this.duration = 0.01F;
        this.slimed = slimed;
        this.numTimes = numTimes;
    }

    public void update() {
        if (this.target == null) {
            this.isDone = true;
            return;
        }

        if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
            AbstractDungeon.actionManager.clearPostCombatActions();
            this.isDone = true;
            return;
        }

        if (this.target.currentHealth > 0) {


            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.target, this.owner, new SlimedPower(this.target, this.owner, slimed), slimed, true, AttackEffect.POISON));

            if ((this.numTimes > 1) && (!AbstractDungeon.getMonsters().areMonstersBasicallyDead())) {
                this.numTimes -= 1;
                AbstractDungeon.actionManager.addToTop(new TendrilFlailAction(this.owner,
                        AbstractDungeon.getMonsters().getRandomMonster(true), this.numTimes, this.slimed));
            }

            AbstractDungeon.actionManager.addToTop(new com.megacrit.cardcrawl.actions.utility.WaitAction(0.2F));
        }

        this.isDone = true;
    }
}

