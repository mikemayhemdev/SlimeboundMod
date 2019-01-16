package slimebound.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import slimebound.actions.SlimeSpawnAction;
import slimebound.powers.PotencyPower;

public class MaxSlimesRelic extends CustomRelic {
    public static final String ID = "MaxSlimesRelic";
    public static final String IMG_PATH = "relics/slimeplushie.png";
    public static final String OUTLINE_IMG_PATH = "relics/slimeplushieOutline.png";
    private static final int HP_PER_CARD = 1;

    public MaxSlimesRelic() {
        super(ID, new Texture(slimebound.SlimeboundMod.getResourcePath(IMG_PATH)),new Texture(slimebound.SlimeboundMod.getResourcePath(OUTLINE_IMG_PATH)), // you could create the texture in this class if you wanted too
                RelicTier.UNCOMMON, LandingSound.MAGICAL); // this relic is uncommon and sounds magic when you click it
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public void atBattleStartPreDraw() {
        this.flash();
        com.megacrit.cardcrawl.dungeons.AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.defect.IncreaseMaxOrbAction(1));
        AbstractDungeon.actionManager.addToBottom(new SlimeSpawnAction(new slimebound.orbs.DebuffSlime(),false,false));
    }



    @Override
    public AbstractRelic makeCopy() { // always override this method to return a new instance of your relic
        return new MaxSlimesRelic();
    }

}