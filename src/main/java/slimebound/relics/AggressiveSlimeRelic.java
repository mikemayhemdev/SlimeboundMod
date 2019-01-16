package slimebound.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import slimebound.actions.SlimeSpawnAction;
import slimebound.powers.PotencyPower;

public class AggressiveSlimeRelic extends CustomRelic {
    public static final String ID = "AggressiveSlimeRelic";
    public static final String IMG_PATH = "relics/minion.png";
    public static final String OUTLINE_IMG_PATH = "relics/minionOutline.png";
    private static final int HP_PER_CARD = 1;

    public AggressiveSlimeRelic() {
        super(ID, new Texture(slimebound.SlimeboundMod.getResourcePath(IMG_PATH)),new Texture(slimebound.SlimeboundMod.getResourcePath(OUTLINE_IMG_PATH)), // you could create the texture in this class if you wanted too
                RelicTier.COMMON, LandingSound.MAGICAL); // this relic is uncommon and sounds magic when you click it
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public void atBattleStartPreDraw() {
        this.flash();
        AbstractDungeon.actionManager.addToBottom(new SlimeSpawnAction(new slimebound.orbs.AttackSlime(),false,false));
        /* 35 */
    }



    @Override
    public AbstractRelic makeCopy() { // always override this method to return a new instance of your relic
        return new AggressiveSlimeRelic();
    }

}