package slimebound.cards;



import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import slimebound.SlimeboundMod;
import slimebound.actions.*;
import slimebound.patches.AbstractCardEnum;

import java.util.Random;


public class QuickStudy extends AbstractSlimeboundCard {
    public static final String ID = "Slimebound:QuickStudy";
    public static final String NAME;
    private static final CardStrings cardStrings;
    public static final String DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;
    public static String UPGRADED_DESCRIPTION;
    public static final String IMG_PATH = "cards/QuickStudy.png";

    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;

    private static final int COST = 0;
    private static final int BLOCK = 5;
    private static final int UPGRADE_BONUS = 3;


    public QuickStudy() {

        super(ID, NAME, SlimeboundMod.getResourcePath(IMG_PATH), COST, DESCRIPTION, TYPE, AbstractCardEnum.SLIMEBOUND, RARITY, TARGET);


        this.exhaust = true;
        this.magicNumber = this.baseMagicNumber = 4;


    }



    public void use(AbstractPlayer p, AbstractMonster m) {

        Random random = new Random();
        Integer chosenRand = random.nextInt(8) + 1;
        AbstractDungeon.actionManager.addToBottom(new LoseHPAction(AbstractDungeon.player, AbstractDungeon.player, this.magicNumber));


        switch (chosenRand) {
            case 1:
                AbstractDungeon.actionManager.addToBottom(new RandomHexaghostCardAction(false));
                break;
            case 2:
                AbstractDungeon.actionManager.addToBottom(new RandomChampCardAction(false));
                break;
            case 3:
                AbstractDungeon.actionManager.addToBottom(new RandomCollectorCardAction(false));
                break;
            case 4:
                AbstractDungeon.actionManager.addToBottom(new RandomAutomatonCardAction(false));
                break;
            case 5:
                AbstractDungeon.actionManager.addToBottom(new RandomAwakanedCardAction(false));
                break;
            case 6:
                AbstractDungeon.actionManager.addToBottom(new RandomShapesCardAction(false));
                break;
            case 7:
                AbstractDungeon.actionManager.addToBottom(new RandomGuardianCardAction(false));
                break;
            case 8:
                AbstractDungeon.actionManager.addToBottom(new RandomTimeEaterCardAction(false));
                break;

        }
    }


    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
        UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
        EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
    }

    public AbstractCard makeCopy() {
        return new QuickStudy();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(-2);


        }
    }
}

