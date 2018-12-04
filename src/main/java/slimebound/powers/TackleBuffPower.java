 package slimebound.powers;





import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.DamageHooks;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.colorless.Shiv;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import slimebound.SlimeboundMod;
import slimebound.cards.*;







 public class TackleBuffPower extends AbstractPower
 {
       public static final String POWER_ID = "TackleBuffPower";
       public static final String NAME = "Potency";
    public static PowerType POWER_TYPE = PowerType.BUFF;
       public static final String IMG = "powers/bleed.png";
    public static final Logger logger = LogManager.getLogger(SlimeboundMod.class.getName());

       public static String[] DESCRIPTIONS;
       private AbstractCreature source;





    public TackleBuffPower(AbstractCreature owner, AbstractCreature source, int amount)
         {

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

    public void stackPower(int stackAmount)
         {

        this.fontScale = 8.0F;

        this.amount += stackAmount;

        updateTacklesInHand();

    }



    public void updateDescription()
     {


        this.description = (DESCRIPTIONS[0] + this.amount * 2 + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2]);


    }

    private void updateTacklesInHand() {

        for (AbstractCard c : com.megacrit.cardcrawl.dungeons.AbstractDungeon.player.hand.group) {

            if (c.cardID == "Tackle") {

                Tackle t = (Tackle) c;
                if (t.upgraded) {
                    t.baseDamage = t.originalDamage + t.upgradeDamage + this.amount * 2;

                    t.baseBlock = t.originalBlock + t.upgradeSelfDamage + this.amount;

                } else {
                    t.baseDamage = t.originalDamage + this.amount * 2;

                    t.baseBlock = t.originalBlock + this.amount;
                }

            } else if (c.cardID == "PoisonTackle") {

                PoisonTackle t = (PoisonTackle) c;
                if (t.upgraded) {
                    t.baseDamage = t.originalDamage + t.upgradeDamage + this.amount * 2;

                    t.baseBlock = t.originalBlock + t.upgradeSelfDamage + this.amount;

                } else {
                    t.baseDamage = t.originalDamage + this.amount * 2;

                    t.baseBlock = t.originalBlock + this.amount;
                }

            } else if (c.cardID == "QuickTackle") {

                QuickTackle t = (QuickTackle) c;
                if (t.upgraded) {
                    t.baseDamage = t.originalDamage + t.upgradeDamage + this.amount * 2;

                    t.baseBlock = t.originalBlock + t.upgradeSelfDamage + this.amount;

                } else {
                    t.baseDamage = t.originalDamage + this.amount * 2;

                    t.baseBlock = t.originalBlock + this.amount;
                }
            } else if (c.cardID == "FlameTackle") {

                FlameTackle t = (FlameTackle) c;
                if (t.upgraded) {
                    t.baseDamage = t.originalDamage + t.upgradeDamage + this.amount * 2;

                    t.baseBlock = t.originalBlock + t.upgradeSelfDamage + this.amount;

                } else {
                    t.baseDamage = t.originalDamage + this.amount * 2;

                    t.baseBlock = t.originalBlock + this.amount;
                }
            }
        }

    }


    public void onDrawOrDiscard()
     {

        updateTacklesInHand();

    }
}



