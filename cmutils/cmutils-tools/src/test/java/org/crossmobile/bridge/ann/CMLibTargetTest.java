/*
 * (c) 2021 by Panayotis Katsaloulis
 *
 * SPDX-License-Identifier: LGPL-3.0-only
 */

package org.crossmobile.bridge.ann;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.fail;

public class CMLibTargetTest {

    private int targetToHash(CMLibTarget target) {
        int res = 0;
        if (target.android)
            res += 1;
        if (target.compile)
            res += 2;
        if (target.swing)
            res += 4;
        if (target.avian)
            res += 8;
        if (target.iosjava)
            res += 16;
        if (target.iosnative)
            res += 32;
        if (target.uwpjava)
            res += 64;
        if (target.uwpnative)
            res += 128;
        return res;
    }

    @Test
    public void checkDoubleOccurrences() {
        Map<Integer, CMLibTarget> available = new HashMap<>();
        for (CMLibTarget target : CMLibTarget.values()) {
            if (target == CMLibTarget.UNKNOWN)    // don't check this target
                continue;
            int hash = targetToHash(target);
            CMLibTarget found = available.get(hash);
            if (found != null)  // don't use assertNotNull because then we'd reference a null object in the default (correct) case
                fail("Found two targets with the same signature: " + target.name() + " and " + found.name());
            available.put(hash, target);
        }
    }
}