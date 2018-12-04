 package slimebound.powers;





import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.FireBurstParticleEffect;
import com.megacrit.cardcrawl.vfx.combat.WeakParticleEffect;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import slimebound.SlimeboundMod;
import slimebound.orbs.SpawnedSlime;
import slimebound.vfx.SlimeDripsEffectPurple;







 public class SlimedPower extends AbstractPower
         {
       public static final String POWER_ID = "SlimedPower";
       public static final String NAME = "UsefulSlime";
    public static PowerType POWER_TYPE = PowerType.DEBUFF;
       public static final String IMG = "powers/SlimedS.png";
                public boolean doubleUp = false;
    public static final Logger logger = LogManager.getLogger(SlimeboundMod.class.getName());
       public static String[] DESCRIPTIONS;
       private AbstractCreature source;
                public boolean triggered = false;




    public SlimedPower(AbstractCreature owner, AbstractCreature source, int amount)
     {

        this.name = NAME;

        this.ID = POWER_ID;
        this.amount = amount;

        this.owner = owner;

        this.source = source;


        this.img = new com.badlogic.gdx.graphics.Texture(slimebound.SlimeboundMod.getResourcePath(IMG));

        this.type = POWER_TYPE;

        this.DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(this.ID).DESCRIPTIONS;

        this.name = CardCrawlGame.languagePack.getPowerStrings(this.ID).NAME;

        updateDescription();


    }



    public void updateDescription()
     {

        this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2]);



    }

    public void onInitialApplication() {

        AbstractDungeon.actionManager.addToBottom(new VFXAction(new SlimeDripsEffectPurple(this.owner.hb.cX,this.owner.hb.cY,4),0.05F));

    }



    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        AbstractDungeon.actionManager.addToBottom(new VFXAction(new SlimeDripsEffectPurple(this.owner.hb.cX,this.owner.hb.cY,4),0.05F));


    }


    public void atEndOfRound()
     {






        if (this.amount <= 1) {

            AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction(this.owner, this.owner, "SlimedPower"));

        } else {

            AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.ReducePowerAction(this.owner, this.owner, "SlimedPower", 1));

        }

    }


    public float atDamageFinalReceive(float damage, DamageInfo.DamageType damageType)
     {

        if (damageType == DamageInfo.DamageType.NORMAL) {



                return damage + this.amount;

        }

        return damage;

    }



    public int onAttacked(DamageInfo info, int damageAmount) {
        if (!this.triggered) {
            if (info.type == DamageInfo.DamageType.NORMAL) {
                this.triggered = true;
                AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.HealAction(this.source, this.source, this.amount));
                AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction(this.owner, this.owner, "SlimedPower"));
            }


        }
        return super.onAttacked(info, damageAmount);
    }


}





