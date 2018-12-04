 package slimebound.cards;


import basemod.abstracts.CustomCard;
import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import slimebound.SlimeboundMod;
import slimebound.actions.SlimeSpawnAction;
import slimebound.patches.AbstractCardEnum;


 public class TorchHeadSlime extends CustomCard
         {
       public static final String ID = "TorchHeadSlime";
       public static final String NAME;
       public static final String DESCRIPTION;
    public static String UPGRADED_DESCRIPTION;
       public static final String IMG_PATH = "cards/splittorchhead.png";

       private static final CardType TYPE = CardType.SKILL;
       private static final CardRarity RARITY = CardRarity.SPECIAL;
       private static final CardTarget TARGET = CardTarget.SELF;

       private static final int COST = 0;
       private static final int BLOCK = 5;
       private static final int UPGRADE_BONUS = 3;
             private static final CardStrings cardStrings;

       public TorchHeadSlime()
       {
             super(ID, NAME, SlimeboundMod.getResourcePath(IMG_PATH), COST, DESCRIPTION, TYPE, CardColor.COLORLESS, RARITY, TARGET);



        this.exhaust=true;
        this.isEthereal=true;

           }

        static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
        UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    }
       public void use(AbstractPlayer p, AbstractMonster m)
       {
                     AbstractDungeon.actionManager.addToBottom(new SlimeSpawnAction(new slimebound.orbs.TorchHeadSlime(),false,true));
                    if (this.upgraded) { AbstractDungeon.actionManager.addToBottom(new SlimeSpawnAction(new slimebound.orbs.TorchHeadSlime(),false,true));}
             }

       public AbstractCard makeCopy()
       {
             return new TorchHeadSlime();
           }

       public void upgrade()
       {
             if (!this.upgraded)
                 {
                   upgradeName();
            this.rawDescription = UPGRADED_DESCRIPTION;
            this.initializeDescription();


                 }
           }
     }

