public final class Something {
    private Something() {
        throw new AssertionError();
    }

    public static String protocol() {
        return "https://";
    }

    public static String subdomain() {
        return "sites.";
    }

    public static String secondleveldomain() {
        return "radford.";
    }

    public static String topleveldomain() {
        return "edu/";
    }

    public static String userdirectory() {
        return "~flo/";
    }

    public static String filename() {
        return "index";
    }

    public static String fileextension() {
        return ".php";
    }
}