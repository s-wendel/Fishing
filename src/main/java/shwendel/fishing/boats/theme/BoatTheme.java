package shwendel.fishing.boats.theme;

import com.sk89q.worldedit.extent.clipboard.Clipboard;
import shwendel.fishing.boats.BoatSize;
import shwendel.fishing.boats.BoatState;

import java.util.Map;

public class BoatTheme {

    public enum ID {

        DEFAULT,
        ;

    }

    private Map<BoatSize, Map<BoatState, Clipboard>> options;

    public BoatTheme(Map<BoatSize, Map<BoatState, Clipboard>> options) {
        this.options = options;
    }

    public Clipboard getClipboard(BoatSize size, BoatState state) {
        return options.get(size).get(state);
    }

}
