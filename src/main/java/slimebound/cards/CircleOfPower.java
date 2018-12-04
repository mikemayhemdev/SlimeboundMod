 package slimebound.cards;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.NextTurnBlockPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.ThornsPower;
import slimebound.SlimeboundMod;


 public class CircleOfPower extends CustomCard
 {
   public static final String ID = "CircleOfPower";
       public static final String NAME;
       public static final String DESCRIPTION;
    public static String UPGRADED_DESCRIPTION;
   public static final String IMG_PATH = "cards/circleofpower.png";

   private static final CardType TYPE = CardType.SKILL;
   private static final CardRarity RARITY = CardRarity.SPECIAL;
   private static final CardTarget TARGET = CardTarget.SELF;
                    private static final CardStrings cardStrings;

   private static final int COST = 1;
   private static final int BLOCK = 5;
   private static final int UPGRADE_BONUS = 3;

   public CircleOfPower()
   {
     super(ID, NAME, SlimeboundMod.getResourcePath(IMG_PATH), COST, DESCRIPTION, TYPE, CardColor.COLORLESS, RARITY, TARGET);



            this.magicNumber = this.baseMagicNumber = 9;

    this.exhaust = true;
    this.isEthereal = true;
   }

   public void use(AbstractPlayer p, AbstractMonster m)
   {
    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, 1), 1 ));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new ArtifactPower(p, 1), 1));

                 AbstractCard c = AbstractDungeon.returnTrulyRandomCardInCombat(AbstractCard.CardType.POWER).makeCopy();
         c.modifyCostForTurn(this.magicNumber *-1);
         AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction(c, true));


}

   public AbstractCard makeCopy()
   {
     return new CircleOfPower();
   }

   public void upgrade()
   {
     if (!this.upgraded)
     {
       upgradeName();
                upgradeMagicNumber(1);
     }
   }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
        UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    }
 }


