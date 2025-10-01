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
    public static Object neg(Object a)           { return Integer.valueOf(-toInt(a)); }

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
            if (i < 0 || i >= n) throw new IndexOutOfBoundsException("list index out of range: " + i + " (size=" + n + ")");
            return list.get(i);
        }
        throw new IllegalArgumentException("indexing only supported on List in v1; got: " + (base==null?"null":base.getClass().getSimpleName()));
    }


    public static Object slice(Object base, Object startOpt, Object endOpt, Object stepOpt) {
        if (!(base instanceof java.util.List<?> list)) {
            throw new IllegalArgumentException("slicing only supported on List in v1; got: " + (base==null?"null":base.getClass().getSimpleName()));
        }
        int n = list.size();
        int step = (stepOpt==null) ? 1 : toInt(stepOpt);
        if (step == 0) throw new IllegalArgumentException("slice step cannot be zero");
        int start, end;
        if (step > 0) {
            start = (startOpt==null) ? 0 : toInt(startOpt);
            end   = (endOpt==null)   ? n : toInt(endOpt);
            if (start < 0) start = n + start;
            if (end   < 0) end   = n + end;
            if (start < 0) start = 0;
            if (end < 0) end = 0;
            if (start > n) start = n;
            if (end > n) end = n;
        } else {
            start = (startOpt==null) ? (n-1) : toInt(startOpt);
            end   = (endOpt==null)   ? -1    : toInt(endOpt);
            if (start < 0) start = n + start;
            if (end   < 0) end   = n + end;
            if (start >= n) start = n-1;
            if (end >= n) end = n-1;
        }
        java.util.ArrayList<Object> out = new java.util.ArrayList<>();
        if (step > 0) {
            for (int i = start; i < end; i += step) out.add(list.get(i));
        } else {
            for (int i = start; i > end; i += step) out.add(list.get(i));
        }
        return out;
    }

}
