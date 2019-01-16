package slimebound.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import slimebound.actions.AddPreparedAction;
import slimebound.actions.RandomStudyCardAction;

public class PreparedRelic extends CustomRelic {
    public static final String ID = "PreparedRelic";
    public static final String IMG_PATH = "relics/slimedteaSet.png";
    public static final String OUTLINE_IMG_PATH = "relics/slimedteaSetOutline.png";
    private static final int HP_PER_CARD = 1;
    private boolean firstTurn = true;

    public PreparedRelic() {
        super(ID, new Texture(slimebound.SlimeboundMod.getResourcePath(IMG_PATH)),new Texture(slimebound.SlimeboundMod.getResourcePath(OUTLINE_IMG_PATH)), // you could create the texture in this class if you wanted too
                RelicTier.UNCOMMON, LandingSound.MAGICAL); // this relic is uncommon and sounds magic when you click it
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public void atBattleStartPreDraw() {

    }

    public void atTurnStart() {
        if (this.firstTurn) {
            if (this.counter == -2) {
                this.pulse = false;
                this.counter = -1;
                this.flash();
                AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                AbstractDungeon.actionManager.addToBottom(new AddPreparedAction(false)); }

            this.firstTurn = false;
        }

    }

    public void atPreBattle() {
        this.firstTurn = true;
    }

    public void setCounter(int counter) {
        super.setCounter(counter);
        if (counter == -2) {
            this.pulse = true;
        }

    }


    public void onRest() {
        this.flash();
        this.counter = -2;
        this.pulse = true;
    }


    @Override
    public AbstractRelic makeCopy() { // always override this method to return a new instance of your relic
        return new PreparedRelic();
    }

}