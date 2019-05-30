/* (c) 2019 by Panayotis Katsaloulis
 *
 * CrossMobile is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 2.
 *
 * CrossMobile is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with CrossMobile; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package org.crossmobile.utils;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

public class ParamSet {

    private final Set<Param> other = new HashSet<>();
    private final Set<Param> build = new HashSet<>();
    private final Set<Param> runtime = new HashSet<>();
    private final Set<Param> local = new HashSet<>();

    {
        for (ParamsCommon p : ParamsCommon.values())
            register(p.tag());
    }

    public void register(Param p) {
        switch (p.type) {
            case SYSTEMRUNTIME:
                runtime.add(p);
                break;
            case BUILD:
                build.add(p);
                break;
            case RUNTIME:
                build.add(p);
                runtime.add(p);
                break;
            case LOCAL:
                local.add(p);
                break;
            default:
            case SYSTEM:
                other.add(p);
                break;
        }
    }

    public void unregister(Param p) {
        other.remove(p);
        build.remove(p);
        runtime.remove(p);
        local.remove(p);
    }

    public Set<Param> local() {
        return local;
    }

    public Set<Param> build() {
        return build;
    }

    public Set<Param> runtime() {
        return runtime;
    }

    public Properties getDefaults() {
        Properties defaults = new Properties();
        for (Param tag : other)
            defaults.put(tag.name, tag.deflt);
        for (Param tag : build)
            defaults.put(tag.name, tag.deflt);
        for (Param tag : runtime)
            defaults.put(tag.name, tag.deflt);
        for (Param tag : local)
            defaults.put(tag.name, tag.deflt);
        return defaults;
    }
}