package slimebound.powers;

import com.evacipated.cardcrawl.mod.stslib.actions.defect.EvokeSpecificOrbAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.ShieldParticleEffect;
import slimebound.orbs.SpawnedSlime;


public class FirmFortitudePower extends AbstractPower {
    public static final String POWER_ID = "Slimebound:FirmFortitudePower";
    public static final String NAME = "Slime Sacrifice";
    public static PowerType POWER_TYPE = PowerType.BUFF;
    public static final String IMG = "powers/FirmFortitude.png";
    private boolean isActive = true;

    public static String[] DESCRIPTIONS;
    private AbstractCreature source;

    public FirmFortitudePower(AbstractCreature owner, int bufferAmt) {
        this.name = NAME;

        this.ID = POWER_ID;
        this.amount = bufferAmt;

        this.owner = owner;


        this.img = new com.badlogic.gdx.graphics.Texture(slimebound.SlimeboundMod.getResourcePath(IMG));

        this.type = POWER_TYPE;

        this.DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(this.ID).DESCRIPTIONS;

        this.name = CardCrawlGame.languagePack.getPowerStrings(this.ID).NAME;
        updateDescription();

    }



    public void atEndOfTurn(boolean isPlayer) {
        this.isActive = false;
    }



    public void atStartOfTurn() {
        this.isActive = true;
    }

    public int onLoseHp(int damageAmount) {
        if (damageAmount > 0 && this.isActive) {
            flash();

            AbstractDungeon.actionManager.addToTop(new ReducePowerAction(this.owner, this.owner, this.ID, 1));
            return 0;
        }

       return damageAmount;
    }



    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
    }


    public void updateDescription() {
        if (this.amount <= 1) {
            this.description = DESCRIPTIONS[0];
        } else {
            this.description = (DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2]);
        }
    }
}


