package io.github.taodaren.recyclerviewtest;

/**
 * Android 历史版本简介：
 * <p>
 * Android 操作系统是一个由 Google 和开放手持设备联盟共同开发发展的移动设备操作系统，
 * 其最早的一个版本Android 1.0 beta发布于2007年11月5日，至今已经发布了多个更新。
 * 这些更新版本都在前一个版本的基础上修复漏洞并添加新功能。
 * <p>
 * Android操作系统有两个预发布的内部版本，它们分别是：
 *          原子小金刚（Astro）和机器人班亭（Bender，电视动画《Futurama》角色）。
 * 从2009年5月开始，Android操作系统改用甜点来作为版本代号，这些版本按照大写字母的顺序来进行命名：
 *          纸杯蛋糕（Cupcake）、甜甜圈（Donut）、闪电泡芙（Eclair）、冻酸奶（Froyo）、姜饼（Gingerbread）、
 *          蜂巢（Honeycomb）、冰淇淋三明治（Ice Cream Sandwich）、果冻豆（Jelly Bean）、奇巧巧克力（KitKat）、
 *          棒棒糖（Lollipop）、棉花糖（Marshmallow）、牛轧糖（Nougat）。
 */

public class RecyclerBean {
    private String name;
    private int imgId;

    public RecyclerBean(String name, int imgId) {
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
