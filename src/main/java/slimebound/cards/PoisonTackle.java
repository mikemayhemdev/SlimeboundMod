 package slimebound.cards;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import slimebound.SlimeboundMod;
import slimebound.patches.AbstractCardEnum;
import slimebound.powers.TackleBuffPower;



 public class PoisonTackle extends CustomCard
 {
       public static final String ID = "PoisonTackle";
       public static final String NAME;
       public static final String DESCRIPTION;
    public static String UPGRADED_DESCRIPTION;
       public static final String IMG_PATH = "cards/poisontackle.png";
       private static final CardType TYPE = CardType.ATTACK;
       private static final CardRarity RARITY = CardRarity.COMMON;
       private static final CardTarget TARGET = CardTarget.ENEMY;

    private static final CardStrings cardStrings;
       private static final int COST = 2;
       private static int baseSelfDamage;
       public static int originalDamage;
       public static int originalBlock;
       public static int upgradeDamage;
       public static int upgradeSelfDamage;


    public PoisonTackle()
     {

        super(ID, NAME, SlimeboundMod.getResourcePath(IMG_PATH), COST, DESCRIPTION, TYPE, AbstractCardEnum.SLIMEBOUND, RARITY, TARGET);


        this.baseDamage = this.originalDamage = 9;
        this.baseBlock = this.originalBlock = 4;
        this.upgradeDamage = 2;

        this.magicNumber = this.baseMagicNumber = 6;


    }



    public void use(AbstractPlayer p, AbstractMonster m)
     {




        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new com.megacrit.cardcrawl.cards.DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        AbstractDungeon.actionManager.addToBottom(new DamageAction(p, new com.megacrit.cardcrawl.cards.DamageInfo(p, this.baseBlock, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SMASH));

        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new PoisonPower(m, p, this.magicNumber), this.magicNumber, true, AbstractGameAction.AttackEffect.POISON));


        }








    public AbstractCard makeCopy()
     {

        return new PoisonTackle();

    }



    public void upgrade()
     {

        if (!this.upgraded)
             {

            upgradeName();

            upgradeDamage(upgradeDamage);

            upgradeMagicNumber(2);

        }

    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
        UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    }
}


