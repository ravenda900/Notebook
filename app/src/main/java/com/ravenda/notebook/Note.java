package com.ravenda.notebook;

/**
 * Created by ravenda900 on 7/12/16.
 */
public class Note {
    private String title, message;
    private long noteId, dateCreatedMilli;
    private Category category;

    public enum Category{
        PERSONAL,
        TECHNICAL,
        QUOTE,
        FINANCE;

        public static Category getRandom() {
            return values()[(int) (Math.random() * values().length)];
        }
    }

    public Note(String title, String message, Category category) {
        this.title = title;
        this.message = message;
        this.category = category;
        this.noteId = 0;
        this.dateCreatedMilli = 0;
    }

    public Note(String title, String message, Category category, long noteId, long dateCreatedMilli) {
        this.title = title;
        this.message = message;
        this.category = category;
        this.noteId = noteId;
        this.dateCreatedMilli = dateCreatedMilli;
    }

    public String getTitle() {
        return this.title;
    }

    public String getMessage() {
        return this.message;
    }

    public Category getCategory() {
        return this.category;
    }

    public long getDate() {
        return this.dateCreatedMilli;
    }

    public long getId() {
        return this.noteId;
    }

    public String toString() {
        return "ID: " + noteId + " Title: " + title + " Message: " + message + " iconID: " + category.name()
                + " Date ";
    }

    public int getAssociatedDrawable() {
        return categoryToDrawable(this.category);
    }

    public static int categoryToDrawable(Category noteCategory) {
        switch(noteCategory) {
            case PERSONAL:
                return R.drawable.p;
            case TECHNICAL:
                return R.drawable.t;
            case FINANCE:
                return R.drawable.f;
            case QUOTE:
                return R.drawable.q;
        }

        return R.drawable.p;
    }
}
