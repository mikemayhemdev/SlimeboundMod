package slimebound.actions;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import slimebound.orbs.SpawnedSlime;
import slimebound.powers.SlimedPower;
import slimebound.vfx.SlimeIntentEffect;
import slimebound.vfx.SlimeIntentMovementEffect;


public class SlimeAutoWeakening extends AbstractGameAction {

    private static final float DURATION = 0.01F;
    private AbstractCreature owner;
    private static final float POST_ATTACK_WAIT_DUR = 0.2F;
    private int damage;
    private int debuffamount;
    private SpawnedSlime slime;


    public SlimeAutoWeakening(AbstractCreature player, Integer damage, SpawnedSlime s) {

        this.owner = player;
        this.actionType = ActionType.POWER;
        this.attackEffect = AttackEffect.POISON;
        this.duration = 0.01F;
        this.debuffamount=amount;
        this.damage=damage;
        this.slime=s;

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

        AbstractCreature mo = AbstractDungeon.getMonsters().getRandomMonster(true);

        if (mo != null) {

            AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(mo, AbstractDungeon.player, new WeakPower(mo, 1, false), 1, true, AbstractGameAction.AttackEffect.NONE));
            AbstractDungeon.actionManager.addToTop(new DamageAction(mo,
                    new DamageInfo(AbstractDungeon.player, this.damage, DamageInfo.DamageType.THORNS),
                    AbstractGameAction.AttackEffect.BLUNT_LIGHT));
            AbstractDungeon.actionManager.addToTop(new VFXAction(new SlimeIntentEffect(slime.intentImage, slime, speedTime), speedTime));
            if (slime.movesToAttack) {
                slime.useFastAttackAnimation();            }
        }



        this.isDone = true;
    }
}

