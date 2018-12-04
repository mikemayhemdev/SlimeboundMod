 package slimebound.powers;



 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import slimebound.SlimeboundMod;





 public class SearingPower extends AbstractPower
 {

           public static final String POWER_ID = "SearingPower";
           public static final String NAME = "Potency";
        public static PowerType POWER_TYPE = PowerType.DEBUFF;
           public static final String IMG = "powers/BurningS.png";
        public static final Logger logger = LogManager.getLogger(SlimeboundMod.class.getName());

           public static String[] DESCRIPTIONS;
           private AbstractCreature source;




    public SearingPower(AbstractCreature owner, AbstractCreature source, int amount)
         {

            this.name = NAME;

            this.ID = POWER_ID;


            this.owner = owner;

            this.source = source;


            this.img = new com.badlogic.gdx.graphics.Texture(slimebound.SlimeboundMod.getResourcePath(IMG));

            this.type = POWER_TYPE;

            this.amount = amount;
            this.DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(this.ID).DESCRIPTIONS;

            this.name = CardCrawlGame.languagePack.getPowerStrings(this.ID).NAME;

            updateDescription();
   }

   public void updateDescription()
   {
       this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
   }

   public void atStartOfTurn()
   {
     if ((AbstractDungeon.getCurrRoom().phase == com.megacrit.cardcrawl.rooms.AbstractRoom.RoomPhase.COMBAT) &&
       (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()))
     {
       flashWithoutSound();
       AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.LoseHPAction(this.owner, this.source, this.amount, com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect.FIRE));
     }
   }
 }


