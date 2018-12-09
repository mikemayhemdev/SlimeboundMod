package slimebound.actions;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import slimebound.orbs.SpawnedSlime;
import slimebound.powers.SlimedPower;
import slimebound.vfx.SlimeIntentEffect;
import slimebound.vfx.SlimeIntentMovementEffect;


public class SlimeAutoSliming extends com.megacrit.cardcrawl.actions.AbstractGameAction {

    private static final float DURATION = 0.01F;
    private AbstractCreature owner;
    private static final float POST_ATTACK_WAIT_DUR = 0.2F;
    private int numTimes;
    private int debuffamount;
    private SpawnedSlime slime;

    public SlimeAutoSliming(AbstractCreature player,Integer amount,SpawnedSlime s) {

        this.owner = player;
        this.actionType = ActionType.POWER;
        this.attackEffect = AttackEffect.POISON;
        this.duration = 0.01F;
        this.debuffamount=amount;
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

        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(mo, AbstractDungeon.player, new SlimedPower(mo, AbstractDungeon.player, this.debuffamount), this.debuffamount, true, AbstractGameAction.AttackEffect.POISON));


        AbstractDungeon.actionManager.addToTop(new VFXAction(new SlimeIntentEffect(slime.intentImage, slime, speedTime), speedTime));
        if (slime.movesToAttack) {
            AbstractDungeon.actionManager.addToTop(new VFXAction(new SlimeIntentMovementEffect(slime, speedTime), speedTime));
        }




        this.isDone = true;
    }
}

