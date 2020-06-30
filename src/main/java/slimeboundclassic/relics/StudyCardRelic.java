package slimeboundclassic.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import slimeboundclassic.actions.RandomStudyCardAction;
import slimeboundclassic.characters.SlimeboundCharacter;
import slimeboundclassic.vfx.TinyHatParticle;

public class StudyCardRelic extends CustomRelic {
    public static final String ID = "SlimeboundClassic:StudyCardRelic";
    public static final String IMG_PATH = "relics/tinybowlerhat.png";
    public static final String OUTLINE_IMG_PATH = "relics/tinybowlerhatOutline.png";
    private static final int HP_PER_CARD = 1;

    public StudyCardRelic() {
        super(ID, new Texture(slimeboundclassic.SlimeboundMod.getResourcePath(IMG_PATH)), new Texture(slimeboundclassic.SlimeboundMod.getResourcePath(OUTLINE_IMG_PATH)),
                RelicTier.BOSS, LandingSound.MAGICAL);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public void atBattleStartPreDraw() {
        if (AbstractDungeon.player instanceof SlimeboundCharacter)
            AbstractDungeon.actionManager.addToBottom(new VFXAction(new TinyHatParticle(AbstractDungeon.player)));
        this.flash();

        AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        AbstractDungeon.actionManager.addToBottom(new RandomStudyCardAction(false));
    }


    @Override
    public AbstractRelic makeCopy() {
        return new StudyCardRelic();
    }

}