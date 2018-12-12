package slimebound.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.vfx.MegaSpeechBubble;
import com.megacrit.cardcrawl.vfx.SpeechBubble;
import slimebound.SlimeboundMod;
import slimebound.characters.SlimeboundCharacter;
import slimebound.orbs.HexSlime;
import slimebound.orbs.SpawnedSlime;
import slimebound.vfx.SlimeDripsEffect;


public class SlimeSpawnAction extends AbstractGameAction {
    private AbstractOrb orbType;
    private boolean SelfDamage = true;
    private boolean upgraded = false;
    private int currentAmount;
    private int upgradedamount;
    private int count;


    public SlimeSpawnAction(AbstractOrb newOrbType, boolean upgraded, boolean SelfDamage, int count) {

        this.duration = Settings.ACTION_DUR_FAST;

        this.orbType = newOrbType;

        this.upgraded = upgraded;
        this.SelfDamage = SelfDamage;
        this.currentAmount = 3;
        SpawnedSlime s = (SpawnedSlime) newOrbType;
        this.upgradedamount = s.upgradedInitialBoost;
        this.count = count;


    }

    public SlimeSpawnAction(AbstractOrb newOrbType, boolean upgraded, boolean SelfDamage) {

        this.duration = Settings.ACTION_DUR_FAST;

        this.orbType = newOrbType;

        this.upgraded = upgraded;
        this.SelfDamage = SelfDamage;
        this.currentAmount = 3;
        SpawnedSlime s = (SpawnedSlime) newOrbType;
        this.upgradedamount = s.upgradedInitialBoost;
        this.count = 1;


    }


    public void update() {

        for (int i = 0; i < count; i++) {

            if (SelfDamage) {

                if (currentAmount >= AbstractDungeon.player.currentHealth && !AbstractDungeon.player.hasPower("Buffer") && !AbstractDungeon.player.hasPower("FirmFortitudePower")) {
                    AbstractDungeon.effectList.add(new SpeechBubble(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, 1.0F, "Need... health...", true));
                    this.isDone = true;
                    return;
                }
                if (currentAmount > 0)
                    AbstractDungeon.actionManager.addToBottom(new LoseHPAction(AbstractDungeon.player, AbstractDungeon.player, this.currentAmount));
            }
            AbstractDungeon.effectsQueue.add(new SlimeDripsEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, 0));


            AbstractDungeon.player.channelOrb(this.orbType);


            if (this.upgraded) {
                AbstractDungeon.actionManager.addToTop(new SlimeBuffUpgraded(this.upgradedamount, SlimeboundMod.mostRecentSlime));
            }
            tickDuration();


        }


        this.isDone = true;

    }
}



