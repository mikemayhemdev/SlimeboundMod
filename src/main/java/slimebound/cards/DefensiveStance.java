 package slimebound.cards;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.MetallicizePower;
import slimebound.SlimeboundMod;
import slimebound.powers.NextTurnGainStrengthPower;


 public class DefensiveStance extends CustomCard
 {
   public static final String ID = "DefensiveStance";
       public static final String NAME;
       public static final String DESCRIPTION;
    public static String UPGRADED_DESCRIPTION;
   public static final String IMG_PATH = "cards/defensivestance.png";

   private static final CardType TYPE = CardType.POWER;
   private static final CardRarity RARITY = CardRarity.SPECIAL;
   private static final CardTarget TARGET = CardTarget.SELF;
                    private static final CardStrings cardStrings;

   private static final int COST = 1;
   private static final int BLOCK = 5;
   private static final int UPGRADE_BONUS = 3;

   public DefensiveStance()
   {
     super(ID, NAME, SlimeboundMod.getResourcePath(IMG_PATH), COST, DESCRIPTION, TYPE, CardColor.COLORLESS, RARITY, TARGET);


     this.baseBlock = 8;
            this.magicNumber = this.baseMagicNumber = 3;

    this.isEthereal = true;
   }

   public void use(AbstractPlayer p, AbstractMonster m)
   {
     AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.GainBlockAction(p, p, this.block));
             AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new MetallicizePower(p, this.magicNumber), this.magicNumber));
   }

   public AbstractCard makeCopy()
   {
     return new DefensiveStance();
   }

   public void upgrade()
   {
     if (!this.upgraded)
     {
       upgradeName();
       upgradeBlock(3);
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


