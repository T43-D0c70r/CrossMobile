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
package org.crossmobile.bridge.system;

import com.eclipsesource.json.*;
import com.eclipsesource.json.JsonObject.Member;
import org.crossmobile.bridge.ann.CMLib;
import org.crossmobile.bridge.ann.CMLibTarget;

import java.util.*;

@CMLib(target = CMLibTarget.APIJAVA)
public class JsonHelper {

    public static Object decode(String json) {
        return json.isEmpty() ? null : toCollection(Json.parse(json));
    }

    public static String encode(Object source, boolean pretty) throws Exception {
        JsonValue json = fromCollection(source);
        try {
            if (!(json.isArray() || json.isObject()))
                json = null;
        } catch (Exception ex) {
            json = null;
        }
        return json == null ? null : json.toString(pretty ? WriterConfig.PRETTY_PRINT : WriterConfig.MINIMAL);
    }

    private static class JMap extends AbstractMap<String, Object> {

        private final JsonObject obj;

        private JMap(JsonObject obj) {
            this.obj = obj;
        }

        private JMap(String data) {
            obj = Json.parse(data).asObject();
        }

        @Override
        public Set<Map.Entry<String, Object>> entrySet() {
            return new JSet(obj);
        }

        public String toString(boolean pretty) {
            return obj.toString();
        }

        private static class JSet extends AbstractSet<Map.Entry<String, Object>> {

            private JsonObject obj;

            public JSet(JsonObject obj) {
                this.obj = obj;
            }

            @Override
            public Iterator<Map.Entry<String, Object>> iterator() {
                return new JSONIterator(obj);
            }

            @Override
            public int size() {
                return obj.size();
            }

            private static class JSONIterator implements Iterator<Map.Entry<String, Object>> {

                private final JsonObject obj;
                private final Iterator<Member> it;

                private JSONIterator(JsonObject obj) {
                    this.obj = obj;
                    this.it = obj.iterator();
                }

                @Override
                public boolean hasNext() {
                    return it.hasNext();
                }

                @Override
                public void remove() {
                    it.remove();
                }

                @Override
                public Map.Entry<String, Object> next() {
                    Member entry = it.next();
                    return new AbstractMap.SimpleEntry<>(entry.getName(), toCollection(entry.getValue()));
                }
            }
        }
    }

    private static class JList extends AbstractList<Object> {

        private final JsonArray array;

        private JList(JsonArray array) {
            this.array = array;
        }

        private JList(String data) {
            array = Json.parse(data).asArray();
        }

        @Override
        public Object get(int index) {
            return toCollection(array.get(index));
        }

        @Override
        public int size() {
            return array.size();
        }

        public String toString(boolean pretty) {
            return array.toString();
        }

    }

    private static Object toCollection(JsonValue value) {
        if (value == null || value.isNull())
            return null;
        else if (value.isObject())
            return new JMap(value.asObject());
        else if (value.isArray())
            return new JList(value.asArray());
        else if (value.isBoolean())
            return value.isTrue();
        else if (value.isString())
            return value.asString();
        else
            try {
                return value.asInt();
            } catch (Exception e_i) {
                try {
                    return value.asLong();
                } catch (Exception e_l) {
                    try {
                        return value.asFloat();
                    } catch (Exception e_f) {
                        try {
                            return value.asDouble();
                        } catch (Exception e_d) {
                            return null;
                        }
                    }
                }
            }
    }

    private static JsonValue fromCollection(Object value) throws Exception {
        if (value == null)
            return Json.NULL;
        else if (value instanceof Boolean)
            return (Boolean) value ? Json.TRUE : Json.FALSE;
        else if (value instanceof Byte || value instanceof Short || value instanceof Integer)
            return Json.value(((Number) value).intValue());
        else if (value instanceof Long)
            return Json.value((Long) value);
        else if (value instanceof Float)
            return Json.value((Float) value);
        else if (value instanceof Double)
            return Json.value((Double) value);
        else if (value instanceof Map) {
            Map<String, Object> map = (Map<String, Object>) value;
            JsonObject json = new JsonObject();
            for (String key : map.keySet())
                json.add(key, fromCollection(map.get(key)));
            return json;
        } else if (value instanceof List) {
            List list = (List) value;
            JsonArray json = new JsonArray();
            for (Object item : list)
                json.add(fromCollection(item));
            return json;
        } else if (value instanceof CharSequence)
            return Json.value(value.toString());
        else
            throw new Exception();
    }
}