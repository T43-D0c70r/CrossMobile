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
package org.crossmobile.build.utils;

public class Versions {

    public static final class ProGuard {

        public static final String GROUP = "net.sf.proguard";
        public static final String ARTIFACT = "proguard-base";
        public static final String VERSION = "6.0.2";

        private ProGuard() {
        }
    }

    public static final class RetroLambda {

        public static final String GROUP = "com.panayotis";
        public static final String ARTIFACT = "retrolambda";
        public static final String VERSION = "2.5.1-2.cm";

        private RetroLambda() {
        }
    }

    public static final class XMLVM {

        public static final String GROUP = "com.panayotis";
        public static final String ARTIFACT = "xmlvm";
        public final static String VERSION = "1.0.4";

        private XMLVM() {
        }
    }
}