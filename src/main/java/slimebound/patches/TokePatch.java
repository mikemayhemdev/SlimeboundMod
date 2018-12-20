package slimebound.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.vfx.campfire.CampfireTokeEffect;
import slimebound.SlimeboundMod;
import slimebound.characters.SlimeboundCharacter;
import slimebound.relics.ScrapOozeRelic;

@SpirePatch(clz= CampfireTokeEffect.class,method="update")
public class TokePatch {


    public static void Prefix() {


        //TODO technically there's an exploit? here if Toke is available.  Need to turn off the bool when cancel is hit.
        if (SlimeboundMod.scrapping && !AbstractDungeon.isScreenUp && !AbstractDungeon.gridSelectScreen.selectedCards.isEmpty() && AbstractDungeon.gridSelectScreen.forPurge) {
            SlimeboundMod.scrapping = false;
            ScrapOozeRelic scrapOoze = ((ScrapOozeRelic) AbstractDungeon.player.getRelic(ScrapOozeRelic.ID));
            scrapOoze.incrementScrapNum();

        }

    }
}

