 package slimebound.cards;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
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



 public class TendrilSlash extends CustomCard
 {
       public static final String ID = "TendrilSlash";
       public static final String NAME;
       public static final String DESCRIPTION;
    public static String UPGRADED_DESCRIPTION;
       public static final String IMG_PATH = "cards/tendrilstrike.png";
       private static final CardType TYPE = CardType.ATTACK;
       private static final CardRarity RARITY = CardRarity.UNCOMMON;
       private static final CardTarget TARGET = CardTarget.ENEMY;

    private static final CardStrings cardStrings;
       private static final int COST = 2;
       private static final int POWER = 6;
       private static final int UPGRADE_BONUS = 3;
    public static final Logger logger = LogManager.getLogger(SlimeboundMod.class.getName());


    public TendrilSlash()
     {

        super(ID, NAME, SlimeboundMod.getResourcePath(IMG_PATH), COST, DESCRIPTION, TYPE, AbstractCardEnum.SLIMEBOUND, RARITY, TARGET);


        this.baseDamage = 12;


    }


    public float calculateModifiedCardDamage(AbstractPlayer player, AbstractMonster mo, float tmp)
           {
                         int bonus = 0;
                         if (mo!=null){
                        if (mo.hasPower("SlimedPower")) {

                        bonus = mo.getPower("SlimedPower").amount;
                     }

                        if (bonus > 0) {
                   this.isDamageModified = true;
                 }}
             return tmp + bonus;
           }




    public void use(AbstractPlayer p, AbstractMonster m)
     {

        if (m.hasPower("SlimedPower")) {


            AbstractDungeon.actionManager.addToTop(new WaitAction(.2f));
            AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.HealAction(p,p, m.getPower("SlimedPower").amount));

        }

                AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new com.megacrit.cardcrawl.cards.DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));


    }






    public AbstractCard makeCopy()
     {

        return new TendrilSlash();

    }



    public void upgrade()
     {

        if (!this.upgraded)
             {

            upgradeName();

            upgradeDamage(4);

        }

    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
        UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    }
}


