package net.yslibrary.android.headercontrolviewpager.event;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

/**
 * Created by yshrsmz on 15/01/31.
 */
public class BusProvider {

    private static final Bus BUS = new Bus(ThreadEnforcer.ANY);

    private BusProvider() {
        // No instances.
    }

    public static Bus get() {
        return BUS;
    }
}
