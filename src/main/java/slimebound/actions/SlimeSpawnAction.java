package slimebound.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.vfx.MegaSpeechBubble;
import com.megacrit.cardcrawl.vfx.SpeechBubble;
import slimebound.SlimeboundMod;
import slimebound.characters.SlimeboundCharacter;
import slimebound.orbs.*;
import slimebound.patches.SlimeboundEnum;
import slimebound.powers.FirmFortitudePower;
import slimebound.powers.StudyAutomatonPower;
import slimebound.powers.StudyAutomatonPowerUpgraded;
import slimebound.vfx.SlimeDripsEffect;

import java.util.Random;


public class SlimeSpawnAction extends AbstractGameAction {
    private AbstractOrb orbType;
    private boolean SelfDamage = true;
    private boolean upgraded = false;
    private boolean random = false;
    private int currentAmount;
    private int upgradedamount;
    private int count;


    public SlimeSpawnAction(AbstractOrb newOrbType, boolean upgraded, boolean SelfDamage) {

        this.duration = Settings.ACTION_DUR_FAST;
        this.random=random;

        if (newOrbType!=null) {
            this.orbType=newOrbType;
            SpawnedSlime s = (SpawnedSlime) newOrbType;

            this.upgradedamount = s.upgradedInitialBoost;
        }

        this.upgraded = upgraded;
        this.SelfDamage = SelfDamage;
        this.currentAmount = 3;


        this.count = count;


    }



    public void update() {
        if (AbstractDungeon.player.maxOrbs > 0) {

            int currentHealth = AbstractDungeon.player.currentHealth;
        /*int maxFortitudes = 0;
        if (AbstractDungeon.player.hasPower("FirmFortitudePower")) maxFortitudes = AbstractDungeon.player.getPower("FirmFortitudePower").amount;
        if (AbstractDungeon.player.hasPower("Buffer")) maxFortitudes = maxFortitudes + AbstractDungeon.player.getPower("Buffer").amount;

        int usedFortitudes = 0;*/


            if (SelfDamage) {

                if (currentAmount >= currentHealth && !AbstractDungeon.player.hasPower("Buffer") && !AbstractDungeon.player.hasPower(FirmFortitudePower.POWER_ID)) {
                    AbstractDungeon.effectList.add(new SpeechBubble(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, 1.0F, "Need... health...", true));
                    this.isDone = true;
                    return;
                }
                if (currentAmount > 0) {
                    /*
                    if (AbstractDungeon.player.hasPower("Buffer") || AbstractDungeon.player.hasPower("FirmFortitudePower")) {

                        if (usedFortitudes < maxFortitudes) {
                            usedFortitudes++;
                        }
                    } else {
                        currentHealth = currentHealth - 3;
                    }
                    */
                    if (AbstractDungeon.player.chosenClass == SlimeboundEnum.SLIMEBOUND) {
                        SlimeboundMod.disabledStrikeVFX = true;
                    }
                    AbstractDungeon.player.damage(new DamageInfo(AbstractDungeon.player, this.currentAmount, DamageInfo.DamageType.HP_LOSS));

                    //AbstractDungeon.player.damageFlash = true;
                    //AbstractDungeon.player.damageFlashFrames = 4;
                }


            }
            // AbstractDungeon.effectsQueue.add(new SlimeDripsEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, 0));

            SlimeboundMod.logger.info("Channeling slime orb");
            if (this.random || this.orbType == null) {

                //OLD RANDOM, NOW UNUSED, CLEAN UP LATER

            } else {

                AbstractDungeon.player.channelOrb(this.orbType);
            }


            if (this.upgraded) {
                SlimeboundMod.bumpnextlime = true;

                AbstractDungeon.actionManager.addToTop(new SlimeBuffUpgraded(this.upgradedamount, SlimeboundMod.mostRecentSlime));
            }
            tickDuration();


            this.isDone = true;




        }
        this.isDone = true;
    }

}



