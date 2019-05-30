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
package crossmobile.ios.foundation;

import crossmobile.ios.coregraphics.CGPoint;
import crossmobile.ios.coregraphics.CGSize;
import crossmobile.ios.uikit.UIFont;
import crossmobile.ios.uikit.UIGraphics;
import crossmobile.rt.StrongReference;
import org.crossmobile.bind.graphics.TextHelpers;
import org.crossmobile.bind.system.AbstractLifecycleBridge;
import org.crossmobile.bind.system.i18n.I18NBridge;
import org.crossmobile.bridge.Native;
import org.crossmobile.bridge.ann.CMClass;
import org.crossmobile.bridge.ann.CMLib;
import org.crossmobile.bridge.ann.CMLibTarget;
import org.crossmobile.bridge.ann.CMSelector;

import java.io.*;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.ArrayList;
import java.util.List;

import static crossmobile.ios.coregraphics.$coregraphics.context;
import static crossmobile.ios.coregraphics.$coregraphics.font;
import static crossmobile.ios.uikit.$uikit.cgfont;
import static org.crossmobile.bind.system.SystemUtilities.closeR;
import static org.crossmobile.bind.system.i18n.I18NBridge.I18N_SUPPORT;

/**
 * NSString class defines an object that is the foundation of the textual
 * functionality of the application.
 */
@CMLib(target = CMLibTarget.API_NOUWP)
@CMClass
public class NSString extends NSObject implements NSSecureCoding {

    private NSString() {
    }

    /**
     * Returns an NSData object that represents the given String encoded using
     * the specified NSStringEncoding.
     *
     * @param string           The initial String that is converted to a NSData object.
     * @param NSStringEncoding The String encoding that is used.
     * @return The final NSData object.
     */
    @CMSelector(value = "- (NSData *)dataUsingEncoding:(NSStringEncoding)encoding", staticMapping = true)
    public static NSData dataUsingEncoding(String string, int NSStringEncoding) {
        try {
            if (string == null)
                return null;
            return new NSData(string.getBytes(crossmobile.ios.foundation.NSStringEncoding.convertIntToString(NSStringEncoding)));
        } catch (UnsupportedEncodingException ex) {
        }
        return null;
    }

    /**
     * Returns a String that is the result of reading data from the specified
     * URL using the given encoding.
     *
     * @param url              The URL from which to read data.
     * @param NSStringEncoding The encoding that is used.
     * @param error            The error that occurs in case of failure.NULL when there the
     *                         error description is useless.
     * @return The final String.
     */
    @Deprecated
    @CMSelector("+ (instancetype)stringWithContentsOfURL:(NSURL *)url\n"
            + "                               encoding:(NSStringEncoding)enc\n"
            + "                                  error:(NSError * _Nullable *)error")
    public static String stringWithContentsOfURL(NSURL url, int NSStringEncoding, StrongReference<NSError> error) {
        if (url == null)
            return null;
        return stringWithContentsOfURL(url.absoluteString(), crossmobile.ios.foundation.NSStringEncoding.convertIntToString(NSStringEncoding), error);
    }

    /**
     * Returns a String that is the interpretation of the specified URL.
     *
     * @param url The URL that is interpreted.
     * @return The final String.
     */
    @Deprecated
    @CMSelector("+ (id)stringWithContentsOfURL:(NSURL *)url;")
    public static String stringWithContentsOfURL(NSURL url) {
        if (url == null)
            return null;
        return stringWithContentsOfURL(url.absoluteString(), null, null);
    }

    private static String stringWithContentsOfURL(String url, String encoding, StrongReference<NSError> error) {
        try {
            return stringWithContentsOfInputStream(new URL(url).openStream(), encoding, error);
        } catch (IOException ex) {
            if (error != null)
                ;
            return null;
        }
    }

    /**
     * Returns a String that is the result of reading data from the specified
     * file.
     *
     * @param path The path of the file.
     * @return The final String.
     */
    @Deprecated
    @CMSelector("+ (id)stringWithContentsOfFile:(NSString *)path;")
    public static String stringWithContentsOfFile(String path) {
        return stringWithContentsOfFileImpl(path, null, null);
    }

    /**
     * Returns a String that is the result of reading data from the specified
     * file.
     *
     * @param path             The path of the file.
     * @param NSStringEncoding the file encoding
     * @param error            The error that occurs in case of failure
     * @return The final String.
     */
    @CMSelector("+ (instancetype)stringWithContentsOfFile:(NSString *)path \n"
            + "                                encoding:(NSStringEncoding)enc \n"
            + "                                   error:(NSError * _Nullable *)error;")
    public static String stringWithContentsOfFile(String path, int NSStringEncoding, StrongReference<NSError> error) {
        return stringWithContentsOfFileImpl(path, crossmobile.ios.foundation.NSStringEncoding.convertIntToString(NSStringEncoding), error);
    }

    private static String stringWithContentsOfFileImpl(String path, String encoding, StrongReference<NSError> error) {
        if (path != null)
            try {
                return stringWithContentsOfInputStream(Native.file().getFileStream(path), encoding, error);
            } catch (IOException e) {
                if (error != null)
                    error.set(NSError.errorWithDomain(NSError.Domain.NSPOSIX, 0, AbstractLifecycleBridge.errorFromThrowable(e)));
            }
        return null;
    }

    private static String stringWithContentsOfInputStream(InputStream stream, String charset, StrongReference<NSError> error) {
        BufferedReader in = null;
        StringBuilder out = new StringBuilder();
        try {
            char[] buffer = new char[1000];
            int howmany;
            in = new BufferedReader(charset == null ? new InputStreamReader(stream) : new InputStreamReader(stream, charset));
            while ((howmany = in.read(buffer)) > 0)
                out.append(buffer, 0, howmany);
        } catch (IOException e) {
            if (error != null)
                error.set(NSError.errorWithDomain(NSError.Domain.NSPOSIX, 0, AbstractLifecycleBridge.errorFromThrowable(e)));
            return null;
        } finally {
            closeR(in);
        }
        return out.toString();
    }

    /**
     * Creates and returns a new NSString object from the interpretation of the
     * specified URL replacing all percent escapes with the matching characters
     * using the specified encoding.
     *
     * @param URL              The URL that is used.
     * @param NSStringEncoding The encoding used for the returned string.
     * @return The final String.
     */
    @CMSelector(value = "- (NSString *)stringByReplacingPercentEscapesUsingEncoding:(NSStringEncoding)encoding", staticMapping = true)
    public static String stringByReplacingPercentEscapesUsingEncoding(String URL, int NSStringEncoding) {
        try {
            return URLDecoder.decode(URL, crossmobile.ios.foundation.NSStringEncoding.convertIntToString(NSStringEncoding));
        } catch (UnsupportedEncodingException ex) {
            return null;
        }
    }

    /**
     * Creates and returns a new NSString object by adding percent escapes in
     * order to convert it to a legal URL.
     *
     * @param URL              The URL that is used.
     * @param NSStringEncoding The encoding used for the returned string.
     * @return The final String.
     */
    @CMSelector(value = "- (NSString *)stringByAddingPercentEscapesUsingEncoding:(NSStringEncoding)encoding", staticMapping = true)
    public static String stringByAddingPercentEscapesUsingEncoding(String URL, int NSStringEncoding) {
        try {
            return URLEncoder.encode(URL, crossmobile.ios.foundation.NSStringEncoding.convertIntToString(NSStringEncoding)).replace("+", "%20");
        } catch (UnsupportedEncodingException ex) {
            return null;
        }
    }

    /**
     * Compares the given Strings using the specified options.
     *
     * @param from                   The first String to be compared.
     * @param with                   The second String to be compared.
     * @param NSStringCompareOptions The option for searching the Strings.
     * @return The result of comparing the two Strings.
     * @see crossmobile.ios.foundation.NSOrdered
     */
    @CMSelector(value = "- (NSComparisonResult)compare:(NSString *)aString options:(NSStringCompareOptions)mask", staticMapping = true)
    public static int compare(String from, String with, int NSStringCompareOptions) {
        if (with == null && from == null)
            return NSOrdered.Same;
        if (with == null)
            return NSOrdered.Descending;
        if (from == null)
            return NSOrdered.Ascending;
        int order;

        if ((NSStringCompareOptions & crossmobile.ios.foundation.NSStringCompareOptions.NSNumericSearch) != 0) {
            double fromD = stringToRelaxedDouble(from);
            double withD = stringToRelaxedDouble(with);
            order = fromD == withD ? 0 : (fromD < withD ? -1 : 1);
        } else {
            if ((NSStringCompareOptions & crossmobile.ios.foundation.NSStringCompareOptions.NSCaseInsensitiveSearch) != 0) {
                from = from.toLowerCase();
                with = with.toLowerCase();
            }
            if ((NSStringCompareOptions & crossmobile.ios.foundation.NSStringCompareOptions.NSDiacriticInsensitiveSearch) != 0) {
                from = Normalizer.normalize(from, Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
                with = Normalizer.normalize(with, Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
            }
            order = from.compareTo(with);
        }
        return order < 0 ? NSOrdered.Ascending : (order > 0 ? NSOrdered.Descending : NSOrdered.Same);
    }

    private static double stringToRelaxedDouble(String dirtyDouble) {
        try {
            return Double.parseDouble(dirtyDouble);
        } catch (NumberFormatException ex) {
        }

        StringBuilder out = new StringBuilder();
        dirtyDouble = dirtyDouble.trim();
        boolean hasdot = false;
        for (int i = 0; i < dirtyDouble.length(); i++) {
            char current = dirtyDouble.charAt(i);
            switch (current) {
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    out.append(current);
                    break;
                case '-':
                    if (out.length() == 0)
                        out.append(current);
                    break;
                case '.':
                    if (!hasdot)
                        out.append(current);
                    hasdot = true;
                    break;
                default:
            }
        }
        return Double.parseDouble(out.toString());
    }

    /**
     * Returns a list that contains the substrings of the specified String
     * divided by the given separator.
     *
     * @param stringtodivide The initial String.
     * @param separator      The separator String.
     * @return The list that contains the substrings of the specified String
     * divided by the given separator.
     */
    @CMSelector(value = "- (NSArray<NSString *> *)componentsSeparatedByString:(NSString *)separator", staticMapping = true)
    public static List<String> componentsSeparatedByString(String stringtodivide, String separator) {
        List<String> parts = new ArrayList<>();
        int loc;
        while (true) {
            loc = stringtodivide.indexOf(separator);
            if (loc < 0) {
                parts.add(stringtodivide);
                break;
            } else {
                parts.add(stringtodivide.substring(0, loc));
                stringtodivide = stringtodivide.substring(loc + separator.length());
            }
        }
        return parts;
    }

    /**
     * Returns a String  that is encoded representation of the given
     * NSData object of the specified encoding.
     *
     * @param data             The NSData object to be encoded.
     * @param NSStringEncoding The encoding used.
     * @return The new String object.
     */
    @CMSelector(value = "- (instancetype)initWithData:(NSData *)data encoding:(NSStringEncoding)encoding", staticMapping = true)
    public static String initWithData(NSData data, int NSStringEncoding) {
        try {
            return new String(data.bytes(), crossmobile.ios.foundation.NSStringEncoding.convertIntToString(NSStringEncoding));
        } catch (UnsupportedEncodingException ex) {
        }
        return null;
    }

    /**
     * Create a String based on the provided format
     *
     * @param format The iOS-type formatting
     * @param args   The possible arguments
     * @return The formatted String
     */
    @CMSelector(value = "- (instancetype)initWithFormat:(NSString *)format, ...;", staticMapping = true,
            swiftVarArgMethod = "NSString.init(format: format as String, arguments:va_array)")
    public static String initWithFormat(String format, Object... args) {
        return initWithFormat(format, null, args);
    }

    /**
     * Get a localized version of a formatted String
     *
     * @param format The iOS-type formatting
     * @param args   The possible arguments
     * @return A localized version of the formatted String
     */
    @CMSelector(value = "- (instancetype)initWithFormat:(NSString *)format \n" +
            "                        locale:(id)locale, ...;", staticMapping = true,
            swiftVarArgMethod = "NSString.init(format: format as String, locale:locale, arguments:va_array)")
    public static String initWithFormat(String format, NSLocale loc, Object... args) {
        if (I18N_SUPPORT)
            format = I18NBridge.retrieveFormat(format, loc != null ? loc.few : null, loc != null ? loc.many : null, args);
        format = objcToJavaFormat(format);
        try {
            return loc == null
                    ? String.format(format, args)
                    : String.format(format, loc.loc, args);
        } catch (Exception e) {
            return format;
        }
    }

    private static String objcToJavaFormat(String format) {
        return format.
                replaceAll("%C", "%c").
                replaceAll("%lld", "%d").
                replaceAll("%llο", "%o").
                replaceAll("%llx", "%x").
                replaceAll("%llX", "%X").
                replaceAll("%@", "%s");
    }

    /**
     * Draws the specified string at the specified point using the specified
     * font.
     *
     * @param texttodisplay The string that is drawn.
     * @param point         The benchmark of the text drawing.
     * @param font          The font that is used.
     * @return The size of the string.
     */
    @Deprecated
    @CMSelector(value = "- (CGSize)drawAtPoint:(CGPoint)point withFont:(UIFont *)font;", staticMapping = true)
    public static CGSize drawAtPoint(String texttodisplay, CGPoint point, UIFont font) {
        context(UIGraphics.getCurrentContext()).setFont(font(cgfont(font)));
        UIGraphics.getCurrentContext().showTextAtPoint(point.getX(), point.getY(), texttodisplay);
        return sizeWithFont(texttodisplay, font);
    }

    /**
     * Returns the size that the specified string would have if it was depicted
     * with the specified font.
     *
     * @param text The String for which the size is requested.
     * @param font The font that the String would have.
     * @return The size of the String.
     */
    @Deprecated
    @CMSelector(value = "- (CGSize)sizeWithFont:(UIFont *)font;", staticMapping = true)
    public static CGSize sizeWithFont(String text, UIFont font) {
        return context(UIGraphics.getCurrentContext()).stringSizeWithFont(text, font(cgfont(font)));
    }

    /**
     * Returns the size that the specified string would have if it was depicted
     * with the specified font and line attributes on a single line.
     *
     * @param text            The String for which the size is requested.
     * @param font            The font that the String would have.
     * @param size            The initial size of the String.
     * @param NSLineBreakMode The line break options in order to compute the new
     *                        size.
     * @return The size that the String would have.
     */
    @Deprecated
    @CMSelector(value = "- (CGSize)sizeWithFont:(UIFont *)font\n"
            + "     constrainedToSize:(CGSize)size\n"
            + "         lineBreakMode:(NSLineBreakMode)lineBreakMode ;", staticMapping = true)
    public static CGSize sizeWithFont(String text, UIFont font, CGSize size, int NSLineBreakMode) {
        return TextHelpers.splitStringWithFontAndSize(text, cgfont(font), size.getWidth(), 0, NSLineBreakMode).size;
    }

    /**
     * Writes the specified content to the specified file using the given
     * encoding.
     *
     * @param content          The content that will be written to the file.
     * @param path             The path of the file to which the content will be written.
     * @param atomically       FALSE if the content was written directly, TRUE if
     *                         there was an auxiliary file.
     * @param NSStringEncoding The string encoding.
     * @param error            The error that occurs in case of failure.NULL when there the
     *                         error description is useless.
     * @return TRUE if the operation was successful.
     */
    @CMSelector(value = "- (BOOL)writeToFile:(NSString *)path\n"
            + "         atomically:(BOOL)useAuxiliaryFile\n"
            + "           encoding:(NSStringEncoding)enc\n"
            + "              error:(NSError * _Nullable *)error", staticMapping = true)
    public static boolean writeToFile(String content, String path, boolean atomically, int NSStringEncoding, StrongReference<NSError> error) {
        Writer out = null;
        String outpath = atomically ? path + Math.random() : path;
        try {
            out = new OutputStreamWriter(new FileOutputStream(outpath), crossmobile.ios.foundation.NSStringEncoding.convertIntToString(NSStringEncoding));
            out.write(content);
        } catch (IOException ex) {
            return false;
        } finally {
            closeR(out);
        }
        if (atomically)
            return new File(outpath).renameTo(new File(path));
        return true;
    }
}