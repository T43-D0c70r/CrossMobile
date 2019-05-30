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
import org.crossmobile.build.ib.Element;
import org.crossmobile.build.ib.helper.XIBList;
import org.crossmobile.build.ib.helper.XibClassStart;
import org.crossmobile.build.ib.i18n.IBParserMeta;
import org.crossmobile.utils.FileUtils;
import org.crossmobile.utils.Log;
import org.crossmobile.utils.XMLWalker;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static org.crossmobile.build.AnnotationConfig.GENERATED_EXT;
import static org.crossmobile.utils.FileUtils.forEach;
import static org.crossmobile.utils.FileUtils.sync;
import static org.crossmobile.utils.TextUtils.plural;

public class IBObjectsCreator {

    public static XIBList parse(File materials, File ann) {
        ann.mkdirs();
        forEach(ann, f -> f.getName().equals(GENERATED_EXT), (name, file) -> file.delete());
        XIBList root = new XIBList(new IBParserMeta(ann));
        parse(root, materials, materials);
        return root;
    }

    private static void parse(XIBList root, File input, File baseDir) {
        if (input.isDirectory())
            for (File child : FileUtils.list(input))
                parse(root, child, baseDir);
        else if (input.getName().endsWith(".xib") || input.getName().endsWith(".storyboard")) {
            root.getMeta().beginFile(input, baseDir);
            XMLWalker walker = XMLWalker.load(input).node("document");
            String type = walker.attribute("type").toLowerCase();
            if (type.endsWith(".storyboard.xib")) {
                XibClassStart classStart = ((XibClassStart) root.addChild("xibclassstart", null, root.getMeta()));

                classStart.setFilename(toFilename(baseDir, input));
                classStart.setInitialViewController(walker.attribute("initialViewController"));
                walker.node("scenes").nodes(node -> construct(root, node));

                root.addChild("xibclassend", null, root.getMeta());
            } else if (type.endsWith(".cocoatouch.xib"))
                walker.nodes(node -> construct(root, node));
            root.getMeta().endFile();
        }
    }

    private static void construct(Element parent, XMLWalker walker) {
        walker.nodes(node -> {
            Element child = parent.addChild(node.name(), node.attribute("key"), parent.getMeta());
            if (child != null) {
                node.attributes(child::setAttribute);
                child.performChecks();
                child.applyLocalizations(parent);
                construct(child, node);
            }
        });
    }

    public static void createJavaSource(XIBList xiblist, File destination, File cache) {
        destination.getParentFile().mkdirs();
        int size = xiblist.countItems();
        if (size > 0) {
            cache.getParentFile().mkdirs();
            try (Writer out = new OutputStreamWriter(new FileOutputStream(cache), StandardCharsets.UTF_8)) {
                out.append(xiblist.toCode());
                Log.info("Added " + size + " Interface Builder resource class" + plural(size, "es"));
            } catch (IOException ex) {
                ExceptionUtils.throwException(ex);
            }
        }
        if (cache.exists())
            sync(cache, destination, false);
    }

    private static String toFilename(File baseDir, File currentFile) {
        return FileUtils.getRelative(baseDir, currentFile)
                .replace("\\", "_")
                .replace("/", "_")
                .replace(".", "_")
                .replace(" ", "_");
    }
}