//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package slimebound.events;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractEvent;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.vfx.RainingGoldEffect;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import slimebound.relics.AggressiveSlimeRelic;

public class WorldOfGoopSlimebound extends AbstractImageEvent {
    public static final String ID = "World of Goop";
    private static final EventStrings eventStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;
    public static final String[] OPTIONS;
    private static final String DIALOG_1;
    private static final String GOLD_DIALOG;
    private static final String LEAVE_DIALOG;
    private WorldOfGoopSlimebound.CurScreen screen;
    private int damage;
    private int gold;
    private int goldLoss;

    public WorldOfGoopSlimebound() {
        super(NAME, DIALOG_1, "images/events/goopPuddle.jpg");
        this.screen = WorldOfGoopSlimebound.CurScreen.INTRO;
        this.damage = 11;
        this.gold = 75;
        if (AbstractDungeon.ascensionLevel >= 15) {
            this.goldLoss = AbstractDungeon.miscRng.random(35, 75);
        } else {
            this.goldLoss = AbstractDungeon.miscRng.random(20, 50);
        }

        if (this.goldLoss > AbstractDungeon.player.gold) {
            this.goldLoss = AbstractDungeon.player.gold;
        }

        this.imageEventText.updateDialogOption(0, "[Gather Gold] #gGain #g75 #gGold.");
        this.imageEventText.updateDialogOption(1, "[Recruit] Gain #gGreed #gOoze.");
    }

    public void onEnterRoom() {
        if (Settings.AMBIANCE_ON) {
            CardCrawlGame.sound.play("EVENT_SPIRITS");
        }

    }

    protected void buttonEffect(int buttonPressed) {
        switch(this.screen) {
            case INTRO:
                switch(buttonPressed) {
                    case 0:
                        this.imageEventText.updateBodyText(GOLD_DIALOG);
                        this.imageEventText.clearAllDialogs();
                        AbstractDungeon.effectList.add(new RainingGoldEffect(this.gold));
                        AbstractDungeon.player.gainGold(this.gold);
                        imageEventText.updateBodyText("You exit the invigorating slime bath, fishing out the #ygold, leaving the creature with a sad look upon its amorphous face.");
                        this.screen = WorldOfGoopSlimebound.CurScreen.RESULT;
                        return;
                    case 1:
                        imageEventText.updateBodyText("You succeed in negotiating a deal with the creature to provide it with more shiny metal if it will aid you in your quest. NL You and your new-found #gfriend exit the slime bath together.");
                        this.imageEventText.clearAllDialogs();
                        this.imageEventText.setDialogOption("Leave");
                        AbstractDungeon.getCurrRoom().spawnRelicAndObtain(Settings.WIDTH / 2.0f, Settings.HEIGHT / 2.0f, RelicLibrary.getRelic(AggressiveSlimeRelic.ID).makeCopy());

                        this.screen = WorldOfGoopSlimebound.CurScreen.RESULT;
                        return;
                    default:

                        return;
                }
            default:
                this.openMap();
        }
    }

    static {
        eventStrings = CardCrawlGame.languagePack.getEventString("World of Goop");
        NAME = eventStrings.NAME;
        DESCRIPTIONS = eventStrings.DESCRIPTIONS;
        OPTIONS = eventStrings.OPTIONS;
        DIALOG_1 = DESCRIPTIONS[0];
        GOLD_DIALOG = DESCRIPTIONS[1];
        LEAVE_DIALOG = DESCRIPTIONS[2];
    }

    private static enum CurScreen {
        INTRO,
        RESULT;

        private CurScreen() {
        }
    }
}
