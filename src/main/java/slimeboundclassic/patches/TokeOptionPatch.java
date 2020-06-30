package slimeboundclassic.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.ui.campfire.TokeOption;
import slimeboundclassic.SlimeboundMod;

@SpirePatch(clz= TokeOption.class,method="useOption")
public class TokeOptionPatch {


    public static void Prefix() {


        SlimeboundMod.scrapping = false;

    }
}

