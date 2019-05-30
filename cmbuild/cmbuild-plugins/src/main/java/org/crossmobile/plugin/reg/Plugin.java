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
package org.crossmobile.plugin.reg;

import org.crossmobile.bridge.CrossMobilePlugin;
import org.crossmobile.bridge.CrossMobilePlugin.Disabled;
import org.crossmobile.bridge.ann.CMLibDepends;
import org.crossmobile.bridge.ann.CMPod;
import org.crossmobile.utils.PluginPod;
import org.crossmobile.utils.reqgraph.Requirement;

import java.util.*;

import static org.crossmobile.utils.TextUtils.replaceOldString;

public class Plugin {

    private String displayName = "";
    private String description = "";
    private String url = "";
    private String initializer = "";
    private final String name;
    private final boolean isExternalDependency;
    private final StringBuilder androidapp = new StringBuilder();
    private final Collection<String> alreadyAddedAndroidApp = new HashSet<>();
    private final Collection<String> libs = new TreeSet<>();
    private final Collection<String> perms = new TreeSet<>();
    private final Map<String, PluginParam> params = new LinkedHashMap<>();
    private final Collection<PluginDependency> depends = new TreeSet<>();
    private final Collection<PluginPod> pods = new TreeSet<>();
    private final Set<String> imports = new HashSet<>();

    public Plugin(String name, boolean isExternalDependency) {
        this.name = name;
        this.isExternalDependency = isExternalDependency;
    }

    public String getName() {
        return name;
    }

    public void setDisplayName(String displayName, String info) {
        this.displayName = replaceOldString(this.displayName, displayName, info);
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDescription(String description, String info) {
        this.description = replaceOldString(this.description, description, info);
    }

    public String getDescription() {
        return description;
    }

    public void setUrl(String url, String info) {
        this.url = replaceOldString(this.url, url, info);
    }

    public String getUrl() {
        return url;
    }

    public void setInitializer(Class<? extends CrossMobilePlugin> initializer, String info) {
        String initName = initializer == null || initializer == Disabled.class ? "" : initializer.getName();
        this.initializer = replaceOldString(this.initializer, initName, info);
    }

    public String getInitializer() {
        return initializer;
    }

    public void addAndroidApp(String extraManifest) {
        if (!alreadyAddedAndroidApp.contains(extraManifest)) {
            alreadyAddedAndroidApp.add(extraManifest);
            if (androidapp.length() > 0)
                androidapp.append('\n');
            androidapp.append(extraManifest);
        }
    }

    public String getAndroidApp() {
        return androidapp.toString();
    }

    public void addParam(String property, PluginParam paramData) {
        params.put(property, paramData);
    }

    public Iterable<String> getParams() {
        return params.keySet();
    }

    public PluginParam getParam(String param) {
        return params.get(param);
    }

    public void addLib(String lib) {
        if (lib != null && !lib.trim().isEmpty())
            libs.add(lib);
    }

    public Iterable<String> getLibs() {
        return libs;
    }

    public Collection<String> getAndroidExtraDependenciess() {
        Collection<String> deps = new HashSet<>();
        for (PluginDependency dep : depends)
            if (dep.target().android && !dep.isCMPlugin())
                deps.add(dep.groupId() + ":" + dep.pluginName() + ':' + dep.version() + ":" + dep.type());
        return deps;
    }

    void addDependency(CMLibDepends dep, Class host, boolean requireTarget) {
        if (dep != null) depends.add(new PluginDependency(dep, host, requireTarget));
    }

    void addPod(CMPod pod) {
        if (pod != null)
            pods.add(new PluginPod(pod));
    }

    public Iterable<PluginDependency> getDependencies() {
        return depends;
    }


    public boolean hasPods() {
        return !pods.isEmpty();
    }

    public Iterable<PluginPod> getPods() {
        return pods;
    }

    public Requirement<Plugin> getRootRequirement() {
        Requirement<Plugin> req = new Requirement<>(this);
        Plugin child;
        for (PluginDependency dep : depends)
            if ((child = PluginRegistry.getPluginData(dep.pluginName())) != null)
                req.setRequires(child.getRootRequirement());
            else if (dep.isCMPlugin())
                throw new RuntimeException("Unable to locate plugin " + dep.pluginName());
        return req;
    }

    public void addPermission(String permission) {
        if (!permission.trim().isEmpty())
            perms.add(permission.trim());
    }

    public Iterable<String> getPermissions() {
        return perms;
    }

    @Override
    public String toString() {
        return name;
    }

    public void addImports(String... includes) {
        imports.addAll(Arrays.asList(includes));
    }

    public String[] getImports() {
        return imports.toArray(new String[]{});
    }

    public boolean isExternalDependency() {
        return isExternalDependency;
    }
}