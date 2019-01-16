package slimebound.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.colorless.Shiv;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import slimebound.actions.RandomStudyCardAction;

public class StudyCardRelic extends CustomRelic {
    public static final String ID = "StudyCardRelic";
    public static final String IMG_PATH = "relics/tinybowlerhat.png";
    public static final String OUTLINE_IMG_PATH = "relics/tinybowlerhatOutline.png";
    private static final int HP_PER_CARD = 1;

    public StudyCardRelic() {
        super(ID, new Texture(slimebound.SlimeboundMod.getResourcePath(IMG_PATH)),new Texture(slimebound.SlimeboundMod.getResourcePath(OUTLINE_IMG_PATH)), // you could create the texture in this class if you wanted too
                RelicTier.BOSS, LandingSound.MAGICAL); // this relic is uncommon and sounds magic when you click it
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public void atBattleStartPreDraw() {
        this.flash();
        AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
       AbstractDungeon.actionManager.addToBottom(new RandomStudyCardAction(false));
    }



    @Override
    public AbstractRelic makeCopy() { // always override this method to return a new instance of your relic
        return new StudyCardRelic();
    }

}