 package slimebound.cards;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import slimebound.SlimeboundMod;
import slimebound.patches.AbstractCardEnum;
import slimebound.powers.SlimedPower;



 public class SuperCorrosiveSpit extends CustomCard
 {
       public static final String ID = "SuperCorrosiveSpit";
       public static final String NAME;
       public static final String DESCRIPTION;
    public static String UPGRADED_DESCRIPTION;
       public static final String IMG_PATH = "cards/douseinslime.png";
       private static final CardType TYPE = CardType.SKILL;
       private static final CardRarity RARITY = CardRarity.RARE;
       private static final CardTarget TARGET = CardTarget.ENEMY;

    private static final CardStrings cardStrings;
       private static final int COST = 3;
       private static final int POWER = 6;
       private static final int UPGRADE_BONUS = 3;
    public static final Logger logger = LogManager.getLogger(SlimeboundMod.class.getName());


    public SuperCorrosiveSpit()
     {

        super(ID, NAME, SlimeboundMod.getResourcePath(IMG_PATH), COST, DESCRIPTION, TYPE, AbstractCardEnum.SLIMEBOUND, RARITY, TARGET);


        this.magicNumber = this.baseMagicNumber = 20;


    }







    public void use(AbstractPlayer p, AbstractMonster m)
     {


          AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new SlimedPower(m, p,this.magicNumber), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));



    }






    public AbstractCard makeCopy()
     {

        return new SuperCorrosiveSpit();

    }



    public void upgrade()
     {

        if (!this.upgraded)
             {

            upgradeName();

            upgradeMagicNumber(6);

        }

    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
        UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    }
}


