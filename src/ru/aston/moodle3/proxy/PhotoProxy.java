package ru.aston.moodle3.proxy;

interface Photo {
    String show();
}

class RealPhoto implements Photo {
    private final String fileName;

    public RealPhoto(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String show() {
        return "Фото: " + fileName;
    }
}

// Заместитель создаёт настоящий объект только при первом обращении.
public class PhotoProxy implements Photo {
    private final String fileName;
    private RealPhoto realPhoto;
    private int loadCount;

    public PhotoProxy(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String show() {
        if (realPhoto == null) {
            realPhoto = new RealPhoto(fileName);
            loadCount++;
        }
        return realPhoto.show();
    }

    public int getLoadCount() {
        return loadCount;
    }
}
