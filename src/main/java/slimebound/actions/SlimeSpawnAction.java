package slimebound.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.vfx.MegaSpeechBubble;
import com.megacrit.cardcrawl.vfx.SpeechBubble;
import slimebound.orbs.HexSlime;
import slimebound.vfx.SlimeDripsEffect;


public class SlimeSpawnAction extends AbstractGameAction {
    private AbstractOrb orbType;
    private boolean SelfDamage = true;
    private boolean autoEvoke = false;
    private int currentAmount;


    public SlimeSpawnAction(AbstractOrb newOrbType, boolean selfDamage) {

        this(newOrbType, true, selfDamage);

    }


    public SlimeSpawnAction(AbstractOrb newOrbType, boolean autoEvoke, boolean SelfDamage) {

        this.duration = Settings.ACTION_DUR_FAST;

        this.orbType = newOrbType;

        this.autoEvoke = autoEvoke;
        this.SelfDamage = SelfDamage;
        this.currentAmount = 3;

    }


    public void update() {

        if (SelfDamage) {

            if (currentAmount >= AbstractDungeon.player.currentHealth) {
                AbstractDungeon.effectList.add(new SpeechBubble(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, 1.0F, "Need... health...", true));
                this.isDone = true;
                return;
            }
            if (currentAmount > 0)
                AbstractDungeon.actionManager.addToBottom(new LoseHPAction(AbstractDungeon.player, AbstractDungeon.player, this.currentAmount));
        }
        AbstractDungeon.effectsQueue.add(new SlimeDripsEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, 0));

        AbstractDungeon.player.channelOrb(this.orbType);


        if (this.orbType instanceof HexSlime)
            AbstractDungeon.actionManager.addToTop(new CheckForSixHexAction(AbstractDungeon.player));


        tickDuration();

        this.isDone = true;

    }
}



