

package slimeboundclassic.dailymods;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.daily.mods.AbstractDailyMod;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.RunModStrings;


public class AllSplit extends AbstractDailyMod {
    public static final String ID = "SlimeboundClassic:AllSplit";
    private static final RunModStrings modStrings;
    public static final String NAME;
    public static final String DESC;

    public AllSplit() {
        super("SlimeboundClassic:AllSplit", NAME, DESC, null, false);
        this.img = ImageMaster.loadImage("SlimeboundClassicImages/relics/heartofgoo.png");
    }

    static {
        modStrings = CardCrawlGame.languagePack.getRunModString("SlimeboundClassic:AllSplit");
        NAME = modStrings.NAME;
        DESC = modStrings.DESCRIPTION;
    }
}
