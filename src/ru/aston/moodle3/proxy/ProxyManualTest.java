package ru.aston.moodle3.proxy;

public class ProxyManualTest {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        PhotoProxy photo = new PhotoProxy("cat.jpg");
        if (photo.getLoadCount() != 0) {
            throw new AssertionError("Фото не должно загружаться при создании прокси");
        }
        if (!photo.show().equals("Фото: cat.jpg")) {
            throw new AssertionError("Прокси должен передать вызов настоящему фото");
        }
        photo.show();
        if (photo.getLoadCount() != 1) {
            throw new AssertionError("При повторном показе фото не должно загружаться снова");
        }

        System.out.println("Прокси: OK (ленивая загрузка один раз)");
    }
}
