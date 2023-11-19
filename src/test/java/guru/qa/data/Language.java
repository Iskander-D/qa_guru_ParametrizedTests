package guru.qa.data;

public enum Language {

   ENGLISH ("It’s like having 40 addresses all around the world!"),
    العربية("الأمر أشبه بامتلاك عناوين في جميع أنحاء العالم");

    public final String description;

    Language(String description) {
        this.description = description;
    }
}
