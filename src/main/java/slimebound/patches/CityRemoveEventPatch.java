package slimebound.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.TheBeyond;
import com.megacrit.cardcrawl.dungeons.TheCity;
import com.megacrit.cardcrawl.monsters.exordium.SlimeBoss;
import slimebound.SlimeboundMod;
import slimebound.characters.SlimeboundCharacter;
import slimebound.events.Hunted;
import slimebound.relics.StudyCardRelic;

@SpirePatch(clz=AbstractDungeon.class,method="initializeCardPools")
public class CityRemoveEventPatch {

    public static void Prefix(AbstractDungeon dungeon_instance) {
        if (dungeon_instance instanceof TheCity || dungeon_instance instanceof TheBeyond)
        if (AbstractDungeon.player instanceof SlimeboundCharacter) {
            SlimeboundCharacter sc = (SlimeboundCharacter)AbstractDungeon.player;
                if (!sc.foughtSlimeBoss){
                    if (!sc.hasRelic(StudyCardRelic.ID))
                        return;
            }

        }

        dungeon_instance.eventList.remove(Hunted.ID);
        SlimeboundMod.logger.info("Removed Hunted event from pool.");

    }
}