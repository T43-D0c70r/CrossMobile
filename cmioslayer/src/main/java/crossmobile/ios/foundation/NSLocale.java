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

import org.crossmobile.bind.system.i18n.I18NSelf;
import org.crossmobile.bridge.ann.CMClass;
import org.crossmobile.bridge.ann.CMConstructor;
import org.crossmobile.bridge.ann.CMGetter;
import org.crossmobile.bridge.ann.CMSelector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * NSLocale class defines an object that represents the locale that is used. The
 * locale incorporates languageCode,region and technological user's preferences.
 */
@CMClass
public class NSLocale extends NSObject implements NSSecureCoding {

    private static NSLocale system;
    private static NSLocale current;

    final Locale loc;

    final I18NSelf.NumberTest few;
    final I18NSelf.NumberTest many;

    /**
     * Returns a list with the preferred user's languages in a descending order.
     *
     * @return User's preferred languages in a descending order.
     */
    @CMSelector("+ (NSArray<NSString *> *)preferredLanguages")
    public static List<String> preferredLanguages() {
        List<String> result = new ArrayList<>();
        result.add(currentLocale().languageCode());
        return result;
    }

    /**
     * Returns system's default locale.
     *
     * @return System's default locale.
     */
    @CMSelector("+ (NSLocale *)systemLocale")
    public static NSLocale systemLocale() {
        if (system == null)
            system = new NSLocale(Locale.ROOT);
        return system;
    }

    /**
     * Returns user's current logical locale.
     *
     * @return User's current logical locale.
     */
    @CMSelector("+ (NSLocale *)currentLocale")
    public static NSLocale currentLocale() {
        if (current == null)
            current = new NSLocale(Locale.getDefault());
        return current;
    }

    @CMConstructor("- (instancetype)initWithLocaleIdentifier:(NSString *)string;")
    public NSLocale(String identifier) {
        this(getLocaleFromIdentifier(identifier));
    }

    private NSLocale(Locale loc) {
        this.loc = loc;
        this.few = null;
        this.many = null;
    }

    /**
     * Get the locale's identifier.
     *
     * @return The locale's id.
     */
    @CMGetter(" @property(readonly, copy) NSString *localeIdentifier ")
    public String localeIdentifier() {
        return getNullIfEmpty(loc.toString());
    }

    /**
     * Get the country's code
     *
     * @return The country code
     */
    @CMGetter("@property(readonly, copy) NSString *countryCode;")
    public String countryCode() {
        return getNullIfEmpty(loc.getCountry());
    }

    /**
     * Get the language's code
     *
     * @return The language code
     */
    @CMGetter("@property(readonly, copy) NSString *languageCode;")
    public String languageCode() {
        return getNullIfEmpty(loc.getLanguage());
    }

    /**
     * Get the locale's variant code
     *
     * @return The variant code
     */
    @CMGetter("@property(readonly, copy) NSString *variantCode;")
    public String variantCode() {
        return getNullIfEmpty(loc.getVariant());
    }

    private static String getNullIfEmpty(String input) {
        return input == null || input.isEmpty() ? null : input;
    }

    private static Locale getLocaleFromIdentifier(String identifier) {
        if (identifier == null)
            return Locale.ROOT;
        identifier = identifier.trim();
        if (identifier.isEmpty())
            return Locale.ROOT;
        List<String> parts = new ArrayList<>(Arrays.asList(identifier.split("_")));
        if (parts.size() > 1 && !parts.get(1).toUpperCase().equals(parts.get(1))) {
            parts.set(0, parts.get(0) + "_" + parts.get(1));
            parts.remove(1);
        }
        if (parts.size() > 2)
            return new Locale(parts.get(0), parts.get(1), parts.get(2));
        else if (parts.size() > 1)
            return new Locale(parts.get(0), parts.get(1));
        else
            return new Locale(parts.get(0));
    }
}