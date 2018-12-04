 package slimebound.cards;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.vfx.combat.CardPoofEffect;
import slimebound.SlimeboundMod;
import slimebound.actions.OverexertionAction;
import slimebound.actions.SlimeSpawnAction;
import slimebound.patches.AbstractCardEnum;


 public class Overexertion extends CustomCard
 {
   public static final String ID = "Overexertion";
       public static final String NAME;
       public static final String DESCRIPTION;
    public static String UPGRADED_DESCRIPTION;
       public static final String[] EXTENDED_DESCRIPTION;
   public static final String IMG_PATH = "cards/wastenot.png";

   private static final CardType TYPE = CardType.SKILL;
   private static final CardRarity RARITY = CardRarity.UNCOMMON;
   private static final CardTarget TARGET = CardTarget.SELF;
                    private static final CardStrings cardStrings;

   private static final int COST = 2;
   private static final int BLOCK = 5;
   private static final int UPGRADE_BONUS = 3;

   public Overexertion()
   {
     super(ID, NAME, SlimeboundMod.getResourcePath(IMG_PATH), COST, DESCRIPTION, TYPE, AbstractCardEnum.SLIMEBOUND, RARITY, TARGET);


     this.exhaust = true;
this.magicNumber = this.baseMagicNumber = 1;
   }



    public boolean canUse(AbstractPlayer p, AbstractMonster m)
           {

              if (p.exhaustPile.size() > 0){
                        return true;} else {
                this.cantUseMessage = EXTENDED_DESCRIPTION[0];
                     return false;}

           }

   public void use(AbstractPlayer p, AbstractMonster m)
   {


        AbstractDungeon.actionManager.addToBottom(new OverexertionAction(false));
    }



   public AbstractCard makeCopy()
   {
     return new Overexertion();
   }

   public void upgrade()
   {
     if (!this.upgraded)
     {
       upgradeName();
upgradeBaseCost(1);
     }
   }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
        UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
        EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
    }
 }


