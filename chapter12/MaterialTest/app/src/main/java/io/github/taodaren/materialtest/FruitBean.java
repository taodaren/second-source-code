package io.github.taodaren.materialtest;

/**
 * 水果实体类
 */

public class FruitBean {
    private String name;
    private int imgId;

    public FruitBean(String name, int imgId) {
        this.name = name;
        this.imgId = imgId;
    }

    public String getName() {
        return name;
    }

    public int getImgId() {
        return imgId;
    }
}
