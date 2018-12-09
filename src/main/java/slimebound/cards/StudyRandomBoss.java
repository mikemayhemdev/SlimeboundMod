package slimebound.cards;



import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.IntenseZoomEffect;
import slimebound.SlimeboundMod;
import slimebound.patches.AbstractCardEnum;
import slimebound.powers.*;

import java.util.Random;


public class StudyRandomBoss extends AbstractSlimeboundCard {
    public static final String ID = "StudyRandomBoss";

    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static String UPGRADED_DESCRIPTION;
    public static final String IMG_PATH = "cards/studyspire.png";
    private static final CardType TYPE = CardType.POWER;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;

    private static final int COST = 2;

    private static int upgradedamount = 1;

    public StudyRandomBoss() {
        super(ID, NAME, SlimeboundMod.getResourcePath(IMG_PATH), COST, DESCRIPTION, TYPE, AbstractCardEnum.SLIMEBOUND, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = 4;


    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new BorderFlashEffect(Color.GREEN, true), 0.05F, true));
        AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new IntenseZoomEffect(p.hb.cX, p.hb.cY, false), 0.05F));


        Random random = new Random();
        Integer chosenRand = random.nextInt(8) + 1;

        switch (chosenRand) {
            case 1:
                if (!upgraded) {
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StudyAutomatonPower(p, p, this.magicNumber), this.magicNumber));
                } else {
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StudyAutomatonPowerUpgraded(p, p, this.magicNumber), this.magicNumber));

                }
                break;
            case 2:
                if (!upgraded) {
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StudyAwakenedPower(p, p, this.magicNumber), this.magicNumber));
                }  else {
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StudyAwakenedPowerUpgraded(p, p, this.magicNumber), this.magicNumber));

                }
                break;

            case 3:
                if (!upgraded) {
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StudyChampPower(p, p, this.magicNumber), this.magicNumber));
                } else {
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StudyChampPowerUpgraded(p, p, this.magicNumber), this.magicNumber));

                }
                break;

            case 4:
                if (!upgraded) {
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StudyCollectorPower(p, p, this.magicNumber), this.magicNumber));
                } else {
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StudyCollectorPowerUpgraded(p, p, this.magicNumber), this.magicNumber));

                }
                break;

            case 5:
                if (!upgraded) {
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StudyGuardianPower(p, p, this.magicNumber), this.magicNumber));
                } else {
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StudyGuardianPowerUpgraded(p, p, this.magicNumber), this.magicNumber));

                }
                break;

            case 6:
                if (!upgraded) {
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StudyHexaghostPower(p, p, this.magicNumber), this.magicNumber));
                } else {
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StudyHexaghostPowerUpgraded(p, p, this.magicNumber), this.magicNumber));

                }
                break;

            case 7:
                if (!upgraded) {
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StudyShapesPower(p, p, this.magicNumber), this.magicNumber));
                } else {
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StudyShapesPowerUpgraded(p, p, this.magicNumber), this.magicNumber));

                }
                break;

            case 8:
                if (!upgraded) {
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StudyTimeEaterPower(p, p, this.magicNumber), this.magicNumber));
                } else {
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StudyTimeEaterPowerUpgraded(p, p, this.magicNumber), this.magicNumber));

                }
                break;

        }

    }

    public AbstractCard makeCopy() {
        return new StudyRandomBoss();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
           // upgradeBaseCost(1);
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

