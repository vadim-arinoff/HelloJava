package ru.aston.moodle3.builder;

public class User {
    private final String name;
    private final short age;
    private final int height;

    private User(Builder builder) {
        name = builder.name;
        age = (short) builder.age;
        height = builder.height;
    }

    public String getName() {
        return name;
    }

    public short getAge() {
        return age;
    }

    public int getHeight() {
        return height;
    }

    public static class Builder {
        private String name;
        private int age;
        private int height;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public Builder height(int height) {
            this.height = height;
            return this;
        }

        public User build() {
            if (name == null || name.isBlank() || age <= 0 || age > Short.MAX_VALUE || height <= 0) {
                throw new IllegalArgumentException("Укажите имя, допустимый возраст и рост");
            }
            return new User(this);
        }
    }
}
