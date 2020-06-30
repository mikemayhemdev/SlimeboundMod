package slimeboundclassic.cards;



import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.IntenseZoomEffect;
import slimeboundclassic.SlimeboundMod;
import slimeboundclassic.patches.AbstractCardEnum;
import slimeboundclassic.powers.*;

import java.util.ArrayList;


public class StudyTheSpire extends AbstractSlimeboundCard {
    public static final String ID = "SlimeboundClassic:StudyTheSpire";

    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static String UPGRADED_DESCRIPTION;
    public static final String IMG_PATH = "cards/studyspire.png";
    private static final CardType TYPE = CardType.POWER;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;

    private static final int COST = 1;

    private static int upgradedamount = 1;

    public StudyTheSpire() {
        super(ID, NAME, SlimeboundMod.getResourcePath(IMG_PATH), COST, DESCRIPTION, TYPE, AbstractCardEnum.SLIMEBOUND, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = 3;


    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new BorderFlashEffect(Color.GREEN, true), 0.05F, true));
        AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new IntenseZoomEffect(p.hb.cX, p.hb.cY, false), 0.05F));


        ArrayList<AbstractPower> powers = new ArrayList();

        
            powers.add(new StudyAutomatonPower(p, p, this.magicNumber));
            powers.add(new StudyAwakenedPower(p, p, this.magicNumber));
            powers.add(new StudyShapesPower(p, p, this.magicNumber));
            powers.add(new StudyChampPower(p, p, this.magicNumber));
            powers.add(new StudyCollectorPower(p, p, this.magicNumber));
            powers.add(new StudyGuardianPower(p, p, this.magicNumber));
            powers.add(new StudyHexaghostPower(p, p, this.magicNumber));
            powers.add(new StudyTimeEaterPower(p, p, this.magicNumber));



        AbstractPower o = powers.get(AbstractDungeon.cardRng.random(powers.size() - 1));


        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, o, this.magicNumber));



    }

    public AbstractCard makeCopy() {
        return new StudyTheSpire();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
           // upgradeBaseCost(1);
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
