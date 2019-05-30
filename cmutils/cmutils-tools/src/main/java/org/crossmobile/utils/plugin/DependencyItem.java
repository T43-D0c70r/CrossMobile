/* (c) 2019 by Panayotis Katsaloulis
 *
 * CrossMobile is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, getVersion 2.
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
package org.crossmobile.utils.plugin;

import org.crossmobile.utils.PluginMetaData;

import java.io.File;
import java.util.*;

import static org.crossmobile.utils.CollectionUtils.asList;

public class DependencyItem implements Comparable<DependencyItem> {

    public static final String BlackListGroup = "org.crossmobile";
    public static final Set<String> BlackListArtifacts = new TreeSet<>(Arrays.asList("cmforeign"));

    private final String groupID;
    private final String artifactID;
    private final String version;
    private final String type;
    private final File file;

    private final Collection<DependencyItem> runtime = new TreeSet<>();
    private final Collection<DependencyItem> compile = new TreeSet<>();
    private final Collection<DependencyItem> blacklisted = new TreeSet<>();

    public DependencyItem() {
        this.groupID = null;
        this.artifactID = null;
        this.version = null;
        this.type = null;
        this.file = null;
    }

    public DependencyItem(String groupID, String artifact, String version, String type, File file) {
        this.groupID = groupID == null ? "" : groupID;
        this.artifactID = artifact;
        this.version = version == null ? "" : version;
        this.type = type == null ? "" : type;
        this.file = file;
    }

    private DependencyItem add(String groupId, String artifactId, String version, String type, Collection<DependencyItem> where, File file) {
        DependencyItem item = new DependencyItem(groupId, artifactId, version, type, file);
        if (!(groupId.equals(BlackListGroup) && BlackListArtifacts.contains(artifactId))) {
            where.add(item);
            return item;
        } else {
            blacklisted.add(item);
            return null;
        }
    }

    public DependencyItem addCompiletime(String groupId, String artifactId, String version, String type, File file) {
        return add(groupId, artifactId, version, type, compile, file);
    }

    public DependencyItem addRuntime(String groupId, String artifactId, String version, String type, File file) {
        return add(groupId, artifactId, version, type, runtime, file);
    }

    private Collection<DependencyItem> getAllCompile(boolean recursively) {
        if (recursively) {
            Collection<DependencyItem> result = new TreeSet<>();
            for (DependencyItem item : compile) {
                result.add(item);
                result.addAll(item.getAllCompile(recursively));
            }
            return result;
        } else
            return new TreeSet<>(compile);
    }

    private Collection<DependencyItem> getAllRuntime(boolean recursively) {
        if (recursively) {
            Collection<DependencyItem> result = new TreeSet<>();
            for (DependencyItem item : runtime) {
                result.add(item);
                result.addAll(item.getAllRuntime(recursively));
            }
            return result;
        } else
            return new TreeSet<>(runtime);
    }

    private void getBlacklisted(Collection<DependencyItem> result) {
        result.addAll(blacklisted);
        for (DependencyItem item : compile)
            item.getBlacklisted(result);
        for (DependencyItem item : runtime)
            item.getBlacklisted(result);
    }

    public Iterable<DependencyItem> getBlacklisted() {
        Collection<DependencyItem> result = new TreeSet<>();
        getBlacklisted(result);
        return result;
    }

    public Iterable<PluginMetaData> getPluginMetaData() {
        return asList(getCompiletimeDependencies(true), d -> new PluginMetaData(d.getFile()));
    }

    public Iterable<DependencyItem> getCompiletimeDependencies(boolean recursively) {
        return getAllCompile(recursively);
    }

    public Iterable<DependencyItem> getRuntimeDependencies(boolean recursively) {
        return getAllRuntime(recursively);
    }

    public Iterable<DependencyItem> getCompileOnlyDependencies(boolean recursively) {
        Collection<DependencyItem> result = getAllCompile(recursively);
        result.removeAll(getAllRuntime(recursively));
        return result;
    }

    public Iterable<DependencyItem> getCompileAndRuntimeDependencies(boolean recursively) {
        Collection<DependencyItem> result = getAllCompile(recursively);
        result.retainAll(getAllRuntime(recursively));
        return result;
    }

    public Iterable<DependencyItem> getRuntimeOnlyDependencies(boolean recursively) {
        Collection<DependencyItem> result = getAllRuntime(recursively);
        result.removeAll(getAllCompile(recursively));
        return result;
    }

    public String getGroupID() {
        return groupID;
    }

    public String getArtifactID() {
        return artifactID;
    }

    public String getVersion() {
        return version;
    }

    public String getType() {
        return type;
    }

    public File getFile() {
        return file;
    }

    @Override
    public boolean equals(Object obj) {
        return this.getClass().equals(obj.getClass())
                && ((DependencyItem) obj).artifactID.equals(artifactID)
                && ((DependencyItem) obj).groupID.equals(groupID)
                && ((DependencyItem) obj).version.equals(version)
                && ((DependencyItem) obj).type.equals(type);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.artifactID);
        hash = 13 * hash + Objects.hashCode(this.groupID);
        hash = 13 * hash + Objects.hashCode(this.version);
        hash = 13 * hash + Objects.hashCode(this.type);
        return hash;
    }

    @Override
    public String toString() {
        return groupID + ":" + artifactID + ":" + version + ":" + type;
    }

    @Override
    public int compareTo(DependencyItem o) {
        int cmp = artifactID.compareTo(o.artifactID);
        if (cmp != 0)
            return cmp;
        cmp = groupID.compareTo(o.groupID);
        if (cmp != 0)
            return cmp;
        cmp = version.compareTo(o.version);
        if (cmp != 0)
            return cmp;
        return type.compareTo(o.type);
    }

}