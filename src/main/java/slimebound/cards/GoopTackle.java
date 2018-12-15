package slimebound.cards;



import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.exordium.GoopPuddle;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import slimebound.SlimeboundMod;
import slimebound.patches.AbstractCardEnum;
import slimebound.powers.TackleBuffPower;
import slimebound.powers.TackleDebuffPower;

import java.util.Random;


public class GoopTackle extends AbstractSlimeboundCard {
    public static final String ID = "Slimebound:GoopTackle";
    public static final String NAME;
    public static final String DESCRIPTION;
    public static String UPGRADED_DESCRIPTION;
    public static final String IMG_PATH = "cards/corrosivetackle.png";
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


    public GoopTackle() {

        super(ID, NAME, SlimeboundMod.getResourcePath(IMG_PATH), COST, DESCRIPTION, TYPE, AbstractCardEnum.SLIMEBOUND, RARITY, TARGET);


        this.baseDamage = this.originalDamage = 8;
        this.selfDamage = 3;
        this.upgradeDamage = 2;


    }

    public float calculateModifiedCardDamage(AbstractPlayer player, AbstractMonster mo, float tmp) {
        int bonus = 0;
        if (player.hasPower(TackleBuffPower.POWER_ID)){
            bonus = player.getPower(TackleBuffPower.POWER_ID).amount;
        }
        if (mo != null) {
            if (mo.hasPower(TackleDebuffPower.POWER_ID)) {
                bonus = bonus + mo.getPower(TackleDebuffPower.POWER_ID).amount;
            }
        }
        return tmp + bonus;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p,p,TackleBuffPower.POWER_ID));


        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new com.megacrit.cardcrawl.cards.DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        AbstractDungeon.actionManager.addToBottom(new DamageAction(p, new com.megacrit.cardcrawl.cards.DamageInfo(p, this.selfDamage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SMASH));


        AbstractCard c = null;
        Random random = new Random();
        Integer chosenRand = random.nextInt(7) + 1;

        switch (chosenRand) {
            case 1:
                c = CardLibrary.getCard(GoopTackle.ID).makeCopy();
                break;
            case 2:
                c = CardLibrary.getCard(FlameTackle.ID).makeCopy();
                break;
            case 3:
                c = CardLibrary.getCard(ComboTackle.ID).makeCopy();
                break;
            case 4:
                c = CardLibrary.getCard(HungryTackle.ID).makeCopy();
                break;
            case 5:
                c = CardLibrary.getCard(VenomTackle.ID).makeCopy();
                break;
            case 6:
                c = CardLibrary.getCard(SlimeSmash.ID).makeCopy();
                break;
            case 7:
                c = CardLibrary.getCard(FinishingTackle.ID).makeCopy();
                break;
        }


        if (upgraded) {
            c.upgrade();
        }
        c.setCostForTurn(0);

        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c));



    }


    public AbstractCard makeCopy() {

        return new GoopTackle();

    }


    public void upgrade() {

        if (!this.upgraded) {

            upgradeName();

            upgradeDamage(upgradeDamage);

            this.rawDescription = UPGRADED_DESCRIPTION;
            this.initializeDescription();

        }

    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
        UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    }
}


