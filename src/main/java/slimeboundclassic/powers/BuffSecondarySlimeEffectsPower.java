package slimeboundclassic.powers;


import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import slimeboundclassic.SlimeboundMod;
import slimeboundclassic.orbs.*;


public class BuffSecondarySlimeEffectsPower extends AbstractPower {
    public static final String POWER_ID = "SlimeboundClassic:BuffSecondarySlimeEffectsPower";
    public static final String NAME = "Potency";
    public static PowerType POWER_TYPE = PowerType.BUFF;
    public static final String IMG = "powers/BuffSlimingSlimes.png";
    public static final Logger logger = LogManager.getLogger(SlimeboundMod.class.getName());

    public static String[] DESCRIPTIONS;
    private AbstractCreature source;


    public BuffSecondarySlimeEffectsPower(AbstractCreature owner, AbstractCreature source, int amount) {

        this.name = NAME;

        this.ID = POWER_ID;


        this.owner = owner;

        this.source = source;


        this.img = new com.badlogic.gdx.graphics.Texture(SlimeboundMod.getResourcePath(IMG));

        this.type = POWER_TYPE;

        this.amount = amount;
        this.DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(this.ID).DESCRIPTIONS;

        this.name = CardCrawlGame.languagePack.getPowerStrings(this.ID).NAME;

        updateDescription();

    }


    public void updateDescription() {


        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];


    }

    public void onInitialApplication() {


        for (AbstractOrb o : AbstractDungeon.player.orbs) {
            if (o instanceof PoisonSlime || o instanceof SlimingSlime || o instanceof ShieldSlime || o instanceof BronzeSlime) {
                SpawnedSlime s = (SpawnedSlime) o;
                s.applySecondaryBonus(this.amount);

            }
        }
    }


    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);

        for (AbstractOrb o : AbstractDungeon.player.orbs) {
            if (o instanceof PoisonSlime || o instanceof SlimingSlime || o instanceof ShieldSlime || o instanceof BronzeSlime) {
                SpawnedSlime s = (SpawnedSlime) o;
                s.applySecondaryBonus(stackAmount);
            }
        }

    }

    public void reducePower(int stackAmount) {
        super.reducePower(stackAmount);

        for (AbstractOrb o : AbstractDungeon.player.orbs) {
            if (o instanceof PoisonSlime || o instanceof SlimingSlime || o instanceof ShieldSlime || o instanceof BronzeSlime) {
                SpawnedSlime s = (SpawnedSlime) o;
                s.applySecondaryBonus(-1 * stackAmount);
            }
        }
    }

    @Override
    public void onRemove() {
        super.onRemove();
        for (AbstractOrb o : AbstractDungeon.player.orbs) {
            if (o instanceof PoisonSlime || o instanceof SlimingSlime || o instanceof ShieldSlime || o instanceof BronzeSlime) {
                SpawnedSlime s = (SpawnedSlime) o;
                s.applySecondaryBonus(-1 * this.amount);

            }
        }
    }
}




