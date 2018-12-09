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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import slimebound.SlimeboundMod;
import slimebound.orbs.SpawnedSlime;
import slimebound.vfx.SlimeIntentEffect;
import slimebound.vfx.SlimeIntentMovementEffect;


public class SlimeAutoAttacking extends AbstractGameAction {

    private static final float DURATION = 0.01F;
    private AbstractCreature owner;
    private static final float POST_ATTACK_WAIT_DUR = 0.2F;
    private int damage;
    private int debuffamount;
    private AbstractPower p;
    private AttackEffect AE;
    public static final Logger logger = LogManager.getLogger(SlimeboundMod.class.getName());
    private SpawnedSlime slime;


    public SlimeAutoAttacking(AbstractCreature player, Integer damage, AttackEffect AE, SpawnedSlime slime) {

        this.owner = player;
        this.actionType = ActionType.POWER;
        this.attackEffect = AttackEffect.POISON;
        this.duration = 0.01F;
        this.debuffamount=amount;
        this.AE=AE;
        this.damage=damage;
        this.slime=slime;

    }

    public void update() {

            logger.info("Starting auto attack");
        if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
            AbstractDungeon.actionManager.clearPostCombatActions();
            this.isDone = true;
            return;
        }
        logger.info("Finding target");
        float speedTime = 0.2F / (float)AbstractDungeon.player.orbs.size();

        if (Settings.FAST_MODE) {

            speedTime = 0.10F;

        }
        AbstractCreature mo = AbstractDungeon.getMonsters().getRandomMonster(true);
        if (mo != null) {

            AbstractDungeon.actionManager.addToTop(new DamageAction(mo,
                    new DamageInfo(AbstractDungeon.player, this.damage, DamageInfo.DamageType.THORNS),
                    AE));

            AbstractDungeon.actionManager.addToTop(new VFXAction(new SlimeIntentEffect(slime.intentImage, slime, speedTime), speedTime));
            if (slime.movesToAttack) {
                AbstractDungeon.actionManager.addToTop(new VFXAction(new SlimeIntentMovementEffect(slime, speedTime), speedTime));
            }
            //logger.info("Targetng " + mo.name);

        }


        this.isDone = true;
    }
}

