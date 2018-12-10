package slimebound.actions;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.SmallLaserEffect;
import slimebound.orbs.SpawnedSlime;
import slimebound.vfx.SlimeIntentEffect;
import slimebound.vfx.SlimeIntentMovementEffect;


public class SlimeAutoBronze extends AbstractGameAction {

    private static final float DURATION = 0.01F;
    private AbstractCreature owner;
    private static final float POST_ATTACK_WAIT_DUR = 0.2F;
    private int damage;
    private int debuffamount;
    private AbstractPower p;
    private SpawnedSlime slime;


    public SlimeAutoBronze(AbstractCreature player, Integer damage, SpawnedSlime o) {

        this.owner = player;
        this.actionType = ActionType.POWER;
        this.attackEffect = AttackEffect.POISON;
        this.duration = 0.01F;
        this.debuffamount=amount;
        this.damage=damage;
        this.slime=o;

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

            AbstractDungeon.actionManager.addToTop(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, this.damage));


            AbstractDungeon.actionManager.addToTop(new DamageAction(mo,
                    new DamageInfo(AbstractDungeon.player, this.damage, DamageInfo.DamageType.THORNS),
                    AttackEffect.NONE));

            AbstractDungeon.actionManager.addToTop(new VFXAction(new SlimeIntentEffect(slime.intentImage, slime, speedTime), speedTime));

            AbstractDungeon.actionManager.addToTop(new VFXAction(new SmallLaserEffect(slime.cX, slime.cY, mo.hb.cX, mo.hb.cY)));
            AbstractDungeon.actionManager.addToTop(new SFXAction("ATTACK_MAGIC_BEAM_SHORT", 0.5F));


        }

        this.isDone = true;
    }
}

