 package slimebound.cards;


import basemod.abstracts.CustomCard;
import basemod.helpers.BaseModCardTags;
import slimebound.SlimeboundMod;
import slimebound.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardRarity;
import com.megacrit.cardcrawl.cards.AbstractCard.CardTarget;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.ThornsPower;

 public class Defend_Slimebound extends CustomCard
 {
   public static final String ID = "Defend_Slimebound";
   public static final String NAME = "Defend";
   public static final String DESCRIPTION = "Gain !B! Block.";
   public static final String IMG_PATH = "cards/defendSlime.png";

   private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;
   private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.BASIC;
   private static final AbstractCard.CardTarget TARGET = CardTarget.SELF;

   private static final int COST = 1;
   private static final int BLOCK = 5;
   private static final int UPGRADE_BONUS = 3;

   public Defend_Slimebound()
   {
     super(ID, NAME, slimebound.SlimeboundMod.getResourcePath(IMG_PATH), COST, DESCRIPTION, TYPE, AbstractCardEnum.SLIMEBOUND, RARITY, TARGET);


     this.baseBlock = 5;
     this.tags.add(BaseModCardTags.BASIC_DEFEND);
   }

   public void use(AbstractPlayer p, AbstractMonster m)
   {
     com.megacrit.cardcrawl.dungeons.AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.GainBlockAction(p, p, this.block));
   }

   public AbstractCard makeCopy()
   {
     return new Defend_Slimebound();
   }

   public void upgrade()
   {
     if (!this.upgraded)
     {
       upgradeName();
       upgradeBlock(3);
     }
   }
 }


