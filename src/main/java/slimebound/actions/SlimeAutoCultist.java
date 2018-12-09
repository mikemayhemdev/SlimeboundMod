package slimebound.actions;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import slimebound.orbs.CultistSlime;
import slimebound.vfx.SlimeIntentEffect;
import slimebound.vfx.SlimeIntentMovementEffect;


public class SlimeAutoCultist extends AbstractGameAction {

    private static final float DURATION = 0.01F;
    private AbstractCreature owner;
    private static final float POST_ATTACK_WAIT_DUR = 0.2F;
    private int damage;
    private int debuffamount;
    private AbstractPower p;
    private AttackEffect AE;
    private CultistSlime slime;

    public SlimeAutoCultist(AbstractCreature player, Integer damage, AttackEffect AE, CultistSlime o) {

        this.owner = player;
        this.actionType = ActionType.POWER;
        this.attackEffect = AttackEffect.POISON;
        this.duration = 0.01F;
        this.debuffamount=amount;
        this.slime=o;
        this.AE=AE;
        this.damage=damage;

    }

    public void update() {


        if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
            AbstractDungeon.actionManager.clearPostCombatActions();
            this.isDone = true;
            return;
        }
        float speedTime = 0.2F / (float)AbstractDungeon.player.orbs.size();

        if (Settings.FAST_MODE) {

            speedTime = 0.10F;

        }

        AbstractDungeon.actionManager.addToTop(new SlimeAutoCultistBuff(2,slime));
        AbstractDungeon.actionManager.addToTop(new DamageRandomEnemyAction(new DamageInfo(AbstractDungeon.player, this.damage, DamageInfo.DamageType.THORNS),AE));

        AbstractDungeon.actionManager.addToTop(new VFXAction(new SlimeIntentEffect(slime.intentImage, slime, speedTime), speedTime));
        if (slime.movesToAttack) {
            AbstractDungeon.actionManager.addToTop(new VFXAction(new SlimeIntentMovementEffect(slime, speedTime), speedTime));
        }




        this.isDone = true;
    }
}

