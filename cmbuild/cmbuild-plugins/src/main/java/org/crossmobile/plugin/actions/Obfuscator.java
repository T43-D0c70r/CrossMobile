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
package org.crossmobile.plugin.actions;

import org.crossmobile.utils.JavaCommander;
import org.crossmobile.utils.Log;

import java.io.File;
import java.util.Collection;

import static org.crossmobile.build.utils.DependencyJarResolver.ERROR;

public class Obfuscator {

    private static final String[] JAVA_SYS = {"lib/rt.jar", "lib/jce.jar", "lib/ext/jfxrt.jar",};

    public static void obfuscate(File proguard, File proguardConf, File proguardMap, File unenc, File encjar, Collection<File> libjars, Collection<File> embedjars) {
        if (!unenc.getName().toLowerCase().endsWith(".jar"))
            throw new RuntimeException("Obfuscated file output should have a 'jar' extension");
        proguardMap.getParentFile().mkdirs();

        JavaCommander cmd = new JavaCommander(proguard.getAbsolutePath());
        cmd.
                addArgument("@" + proguardConf.getAbsolutePath()).
                addArgument("-printmapping").addArgument(proguardMap.getAbsolutePath());

        for (String sysjar : JAVA_SYS)
            cmd.addArgument("-libraryjars").addArgument(new File(System.getProperty("java.home"), sysjar).getAbsolutePath());
        for (File libjar : libjars)
            cmd.addArgument("-libraryjars").addArgument(libjar.getAbsolutePath());

        cmd.addArgument("-injars").addArgument(unenc.getAbsolutePath());
        for (File embedjar : embedjars)
            cmd.addArgument("-injars").addArgument(embedjar.getAbsolutePath() + "(!META-INF/MANIFEST.MF)");

        cmd.addArgument("-outjars").addArgument(encjar.getAbsolutePath());

        cmd.setOutListener(Log::info);
        cmd.setErrListener(ERROR);
        Log.info("Encrypt files");
        cmd.exec();
        cmd.waitFor();
        if (cmd.exitValue() != 0)
            throw new RuntimeException("Unable to execute proguard with command: " + cmd.toString());
    }
}