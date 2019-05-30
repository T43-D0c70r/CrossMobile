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
package org.crossmobile.build.tools;

import org.crossmobile.bridge.system.ExceptionUtils;
import org.crossmobile.utils.CMVersion;
import org.crossmobile.utils.FileUtils;
import org.crossmobile.utils.Log;
import org.crossmobile.utils.ProjectException;

import java.io.File;
import java.util.regex.Pattern;

import static org.crossmobile.build.utils.Config.GRADLE;
import static org.crossmobile.utils.TemplateUtils.copyTemplateIfMissing;

public class GradleManager {

    public static final String OLD_GRADLE_PLUGIN = "crossmobile-gradle-plugin";
    public static final String GRADLE_PLUGIN = "cmbuild-gradle-plugin";

    private static final Pattern pluginversion = Pattern.compile("(classpath\\s*'org\\.crossmobile:" + GRADLE_PLUGIN + ":[a-zA-Z0-9.-]*')");

    public static void createAndUpdate(File basedir) {
        createAndUpdateBuild(basedir);
        createAndUpdateProperties(basedir);

    }

    @SuppressWarnings("UseSpecificCatch")
    private static void createAndUpdateBuild(File basedir) {
        try {
            File gradle = new File(basedir, GRADLE);
            if (gradle.isFile()) {
                String content = FileUtils.read(gradle);
                if (content != null && (content.contains("crossmobile-gradle-plugin:1") || !content.contains("google()")))
                    FileUtils.move(gradle, new File(gradle.getParentFile(), "build.v1.old.gradle"), null);
//            updateVersion(gradle);
            }
            copyTemplateIfMissing(GRADLE, gradle, "Creating missing build.gradle file");
        } catch (Exception ex) {
            ExceptionUtils.throwException(ex);
        }
    }

    private static void updateVersion(File gradle) {
        String orig = FileUtils.read(gradle);
        String updated = pluginversion.matcher(orig.replaceAll(OLD_GRADLE_PLUGIN, GRADLE_PLUGIN)).
                replaceAll("classpath 'org.crossmobile:" + GRADLE_PLUGIN + ":" + CMVersion.VERSION + "'").
                replaceAll("com\\.android\\.tools\\.build:gradle:2\\.2\\.3", "com.android.tools.build:gradle:3.1.0").
                replaceAll("com\\.android\\.tools\\.build:gradle:2\\.3\\.3", "com.android.tools.build:gradle:3.1.0");
        if (!orig.equals(updated)) {
            Log.debug("Will update " + GRADLE_PLUGIN + " to version " + CMVersion.VERSION);
            FileUtils.write(gradle, updated);
        }
    }

    private static void createAndUpdateProperties(File basedir) {
        try {
            copyTemplateIfMissing("gradle.properties", new File(basedir, "gradle.properties"), "Creating missing gradle.properties file");
        } catch (ProjectException ex) {
            ExceptionUtils.throwException(ex);
        }
    }

}