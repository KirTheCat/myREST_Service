package org.example.model.enums;

public enum RatingEnum {
    ZERO_STAR(" (Приемлемо)"),
    ONE_STAR("★ (Ужасно)"),
    TWO_STARS("★★ (Плохо)"),
    THREE_STARS("★★★ (Неплохо)"),
    FOUR_STARS("★★★★ (Круто)"),
    FIVE_STARS("★★★★★ (Супер!)");

    private final String rating;

    RatingEnum(String rating) {
        this.rating = rating;
    }

    public String getRating() {
        return this.rating;
    }
}