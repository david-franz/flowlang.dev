package dfpp.rt;

public final class Rt {
    private Rt() {}

    public static Object add(Object a, Object b) {
        if (a instanceof String || b instanceof String) {
            return String.valueOf(a) + String.valueOf(b);
        }
        return Integer.valueOf(toInt(a) + toInt(b));
    }
    public static Object sub(Object a, Object b) { return Integer.valueOf(toInt(a)-toInt(b)); }
    public static Object mul(Object a, Object b) { return Integer.valueOf(toInt(a)*toInt(b)); }
    public static Object div(Object a, Object b) { return Integer.valueOf(toInt(a)/toInt(b)); }
    public static Object mod(Object a, Object b) { return Integer.valueOf(toInt(a)%toInt(b)); }

    public static Boolean lt(Object a, Object b) { return toInt(a) <  toInt(b); }
    public static Boolean le(Object a, Object b) { return toInt(a) <= toInt(b); }
    public static Boolean gt(Object a, Object b) { return toInt(a) >  toInt(b); }
    public static Boolean ge(Object a, Object b) { return toInt(a) >= toInt(b); }
    public static Boolean eq(Object a, Object b) {
        if (a==null) return b==null;
        if (a instanceof Integer && b instanceof Integer) return ((Integer)a).intValue()==((Integer)b).intValue();
        return a.equals(b);
    }
    public static Boolean ne(Object a, Object b) { return !eq(a,b); }

    public static Boolean not(Object a)          { return !toBool(a); }

    public static boolean toBool(Object a) {
        if (a instanceof Boolean b) return b;
        if (a instanceof Integer i) return i != 0;
        return a != null;
    }
    public static int toInt(Object a) {
        if (a instanceof Integer i) return i;
        if (a instanceof String s) return Integer.parseInt(s);
        if (a instanceof Boolean b) return b ? 1 : 0;
        throw new IllegalArgumentException("not an int: " + a);
    }

    // Python-like indexing for lists: supports negative indices.
    public static Object index(Object base, Object idx) {
        if (base instanceof java.util.List<?> list) {
            int i = toInt(idx);
            int n = list.size();
            if (i < 0) i = n + i; // negative from end
            if (i < 0 || i >= n) throw new IndexOutOfBoundsException("list index out of range: " + i);
            return list.get(i);
        }
        throw new IllegalArgumentException("indexing only supported on List in v1: " + base);
    }
}
