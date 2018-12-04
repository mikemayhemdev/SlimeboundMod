 package slimebound.cards;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveAllBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.defect.DiscardPileToHandAction;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.actions.utility.DrawPileToHandAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import slimebound.SlimeboundMod;
import slimebound.patches.AbstractCardEnum;



 public class TendrilStrike extends CustomCard
 {
       public static final String ID = "TendrilStrike";
       public static final String NAME;
       public static final String DESCRIPTION;
    public static String UPGRADED_DESCRIPTION;
       public static final String IMG_PATH = "cards/tendrilflail.png";
       private static final CardType TYPE = CardType.ATTACK;
       private static final CardRarity RARITY = CardRarity.UNCOMMON;
       private static final CardTarget TARGET = CardTarget.ENEMY;

    private static final CardStrings cardStrings;
       private static final int COST = 0;
       private static final int POWER = 6;
                private boolean returnThis;
       private static final int UPGRADE_BONUS = 3;


    public TendrilStrike()
     {

        super(ID, NAME, SlimeboundMod.getResourcePath(IMG_PATH), COST, DESCRIPTION, TYPE, AbstractCardEnum.SLIMEBOUND, RARITY, TARGET);


        this.baseDamage = 5;
        this.returnThis = false;
             this.tags.add(AbstractCard.CardTags.STRIKE);


    }



    public void use(AbstractPlayer p, AbstractMonster m)
     {

        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new com.megacrit.cardcrawl.cards.DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));

        if (m.hasPower("Weakened") && m.hasPower("Poison")) {
            this.returnThis = true;

            if (m.getPower("Weakened").amount > 1) {
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new WeakPower(p, -1, false), -1, true, AbstractGameAction.AttackEffect.NONE));
            } else {
                AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction(m, p, "Weakened"));

            }

            if (m.getPower("Poison").amount > 1) {
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new PoisonPower(m, p, -1), -1, true, AbstractGameAction.AttackEffect.NONE));
            } else {
                AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction(m, p, "Poison"));

            }

        }

    }


    public void onMoveToDiscard() {
        if (returnThis){
            AbstractDungeon.actionManager.addToBottom(new DiscardToHandAction(this));
                    returnThis = false;

        }
    }




    public AbstractCard makeCopy()
     {

        return new TendrilStrike();

    }



    public void upgrade()
     {

        if (!this.upgraded)
             {

            upgradeName();

            upgradeDamage(2);

        }

    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
        UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    }
}


