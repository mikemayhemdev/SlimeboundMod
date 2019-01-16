//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package slimebound.events;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.curses.Doubt;
import com.megacrit.cardcrawl.cards.curses.Normality;
import com.megacrit.cardcrawl.cards.curses.Shame;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractEvent;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.events.beyond.MindBloom;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.MonsterHelper;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.AbstractRelic.RelicTier;
import com.megacrit.cardcrawl.vfx.RainingGoldEffect;
import com.megacrit.cardcrawl.vfx.UpgradeShineEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;
import slimebound.SlimeboundMod;
import slimebound.relics.StudyCardRelic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Hunted extends MindBloom {
    public static final String ID = "Slimebound:Hunted";
    private static final EventStrings eventStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;
    public static final String[] OPTIONS;
    private static final String DIALOG_1;
    private static final String DIALOG_2;
    private static final String DIALOG_3;
    private Hunted.CurScreen screen;

    public Hunted() {

        this.imageEventText.clearAllDialogs();
        this.title = NAME;
        this.body = DIALOG_1;
        this.imageEventText.loadImage("SlimeboundImages/events/hunted.jpg");
        this.screen = Hunted.CurScreen.INTRO;
        this.imageEventText.setDialogOption(OPTIONS[0]);

        this.imageEventText.setDialogOption(OPTIONS[1], CardLibrary.getCopy("Shame"));
        SlimeboundMod.huntedTriggered = true;
         CardCrawlGame.sound.play("VO_SLIMEBOSS_1A");

    }

    @Override
    protected void buttonEffect(int buttonPressed) {
        switch(this.screen) {
            case INTRO:
                switch(buttonPressed) {
                    case 0:
                        //this.imageEventText.updateBodyText(DIALOG_2);
                        this.screen = Hunted.CurScreen.FIGHT;
                        //logMetric("MindBloom", "Fight");
                        CardCrawlGame.music.playTempBgmInstantly("BOSS_BOTTOM", true);


                        AbstractDungeon.getCurrRoom().monsters = MonsterHelper.getEncounter("Slime Boss");
                        AbstractDungeon.getCurrRoom().rewards.clear();
                        AbstractDungeon.getCurrRoom().addGoldToRewards(100);
                        AbstractDungeon.getCurrRoom().addRelicToRewards(new StudyCardRelic());
                        this.enterCombatFromImage();
                        //AbstractDungeon.lastCombatMetricKey = "Mind Bloom Boss Battle";
                        break;
                    case 1:
                        this.screen = Hunted.CurScreen.LEAVE;
                        AbstractCard card = new Shame();
                        AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(card, (float)(Settings.WIDTH / 2), (float)(Settings.HEIGHT / 2)));
                        this.imageEventText.clearRemainingOptions();
                        SlimeboundMod.huntedTriggered = false;
                        this.openMap();
                        break;
                    default:
                        SlimeboundMod.huntedTriggered = false;
                        this.imageEventText.clearRemainingOptions();
                        this.openMap();

                }

                this.imageEventText.clearRemainingOptions();
                break;
            case LEAVE:
                this.openMap();
                break;
            default:
                this.openMap();
        }

    }

    static {
        eventStrings = CardCrawlGame.languagePack.getEventString("Slimebound:Hunted");
        NAME = eventStrings.NAME;
        DESCRIPTIONS = eventStrings.DESCRIPTIONS;
        OPTIONS = eventStrings.OPTIONS;
        DIALOG_1 = DESCRIPTIONS[0];
        DIALOG_2 = DESCRIPTIONS[1];
        DIALOG_3 = DESCRIPTIONS[2];
    }

    private static enum CurScreen {
        INTRO,
        FIGHT,
        LEAVE;

        private CurScreen() {
        }
    }
}
