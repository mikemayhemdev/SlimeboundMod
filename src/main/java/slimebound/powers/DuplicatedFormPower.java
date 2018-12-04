 package slimebound.powers;





import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import slimebound.SlimeboundMod;







 public class DuplicatedFormPower extends AbstractPower
 {
       public static final String POWER_ID = "DuplicatedFormPower";
       public static final String NAME = "Potency";
                public static PowerType POWER_TYPE = PowerType.BUFF;
       public static final String IMG = "powers/DuplicatedEchoS.png";
    public static final Logger logger = LogManager.getLogger(SlimeboundMod.class.getName());

       public static String[] DESCRIPTIONS;
       private AbstractCreature source;
       private int cardsDoubledThisTurn = 0;




    public DuplicatedFormPower(AbstractCreature owner, AbstractCreature source, int amount)
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

    public void atStartOfTurn() {
        this.cardsDoubledThisTurn = 0;
    }



    public void updateDescription()
     {


        if (this.amount == 1) {
                   this.description = DESCRIPTIONS[0];
                 } else {
                   this.description = (DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2]);
                 }


    }


    public void onUseCard(AbstractCard card, UseCardAction action)
           {
             if ((!card.purgeOnUse) && (this.amount > 0) && (AbstractDungeon.actionManager.cardsPlayedThisTurn.size() - this.cardsDoubledThisTurn <= this.amount))
                 {
                   this.cardsDoubledThisTurn += 1;
                   flash();
                   AbstractMonster m = null;

                   if (action.target != null) {
                         m = (AbstractMonster)action.target;
                       }

                   AbstractCard tmp = card.makeSameInstanceOf();
                   AbstractDungeon.player.limbo.addToBottom(tmp);
                   tmp.current_x = card.current_x;
                   tmp.current_y = card.current_y;
                   tmp.target_x = (Settings.WIDTH / 2.0F - 300.0F * Settings.scale);
                   tmp.target_y = (Settings.HEIGHT / 2.0F);
                   tmp.freeToPlayOnce = true;

                   if (m != null) {
                         tmp.calculateCardDamage(m);
                       }

                   tmp.purgeOnUse = true;
                   AbstractDungeon.actionManager.cardQueue.add(new com.megacrit.cardcrawl.cards.CardQueueItem(tmp, m, card.energyOnUse));
                 }
           }
}



