package org.example.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum RatingEnum {
    ZERO_STAR("(Приемлемо)"),
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

    @JsonCreator
    public static RatingEnum fromString(String value) {
        for (RatingEnum ratingEnum : RatingEnum.values()) {
            if (ratingEnum.name().equalsIgnoreCase(value)) {
                return ratingEnum;
            }
        }
        throw new IllegalArgumentException("Invalid rating value: " + value);
    }
}