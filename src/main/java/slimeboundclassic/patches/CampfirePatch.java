package slimeboundclassic.patches;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.rooms.CampfireUI;
import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;

import com.evacipated.cardcrawl.modthespire.lib.*;

import java.util.*;

import basemod.*;

import slimeboundclassic.relics.ScrapOozeRelic;
import slimeboundclassic.ui.ScrapBonfireOption;

@SpirePatch(clz = CampfireUI.class, method = "initializeButtons")
public class CampfirePatch {
    public static void Prefix(CampfireUI obj) {

        final ArrayList<AbstractCampfireOption> campfireButtons = (ArrayList<AbstractCampfireOption>)ReflectionHacks.getPrivate((Object)obj, (Class)CampfireUI.class, "buttons");

        if (AbstractDungeon.player.hasRelic(ScrapOozeRelic.ID)) {
            campfireButtons.add(new ScrapBonfireOption(!AbstractDungeon.player.masterDeck.getPurgeableCards().isEmpty()));

            /*
            if (AbstractDungeon.player.hasRelic(PeacePipe.ID)){
                AbstractDungeon.player.relics.remove(AbstractDungeon.player.getRelic(PeacePipe.ID));
            }
            */

        }
    }
}


