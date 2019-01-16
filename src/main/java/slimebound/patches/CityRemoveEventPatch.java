package slimebound.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.Exordium;
import com.megacrit.cardcrawl.dungeons.TheBeyond;
import com.megacrit.cardcrawl.dungeons.TheCity;
import com.megacrit.cardcrawl.events.exordium.GoopPuddle;
import com.megacrit.cardcrawl.events.exordium.ScrapOoze;
import com.megacrit.cardcrawl.monsters.exordium.SlimeBoss;
import slimebound.SlimeboundMod;
import slimebound.characters.SlimeboundCharacter;
import slimebound.events.Hunted;
import slimebound.events.WorldOfGoopSlimebound;
import slimebound.orbs.GreedOozeSlime;
import slimebound.relics.GreedOozeRelic;
import slimebound.relics.ScrapOozeRelic;
import slimebound.relics.StudyCardRelic;

@SpirePatch(clz=AbstractDungeon.class,method="initializeCardPools")
public class CityRemoveEventPatch {

    public static void Prefix(AbstractDungeon dungeon_instance) {
        if (dungeon_instance instanceof Exordium)
            if (AbstractDungeon.player instanceof SlimeboundCharacter) {
                dungeon_instance.eventList.remove(GoopPuddle.ID);
               // dungeon_instance.eventList.add(WorldOfGoopSlimebound.ID);
            } else {
                dungeon_instance.eventList.remove(WorldOfGoopSlimebound.ID);

            }

        if (dungeon_instance instanceof TheCity || dungeon_instance instanceof TheBeyond)
        if (AbstractDungeon.player instanceof SlimeboundCharacter) {
            SlimeboundCharacter sc = (SlimeboundCharacter)AbstractDungeon.player;
                if (!sc.foughtSlimeBoss){
                    if (!sc.hasRelic(StudyCardRelic.ID))
                        return;
            }

        }

        if (AbstractDungeon.player.hasRelic(ScrapOozeRelic.ID)){
            dungeon_instance.eventList.remove(ScrapOoze.ID);
        }
        if (AbstractDungeon.player.hasRelic(GreedOozeRelic.ID)){
            dungeon_instance.eventList.remove(WorldOfGoopSlimebound.ID);
            dungeon_instance.eventList.remove(GoopPuddle.ID);
        }


        dungeon_instance.eventList.remove(Hunted.ID);

        //SlimeboundMod.logger.info("Removed Hunted event from pool.");

    }
}