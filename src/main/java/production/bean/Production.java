package production.bean;

/**
 * Created by JasonFitch on 7/4/2019.
 */
public class Production {

    public static final Production EMPTY = new Production("", "", "");

    private String name;
    private String name_zh;
    private String describe;

    public Production(String name, String name_zh, String describe) {
        this.name = name;
        this.name_zh = name_zh;
        this.describe = describe;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName_zh() {
        return name_zh;
    }

    public void setName_zh(String name_zh) {
        this.name_zh = name_zh;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    @Override
    public String toString() {
        return "{" + "\"name\"=\"" + name + "\",\"name_zh\"=\"" + name_zh + "\",\"describe\"=\"" + describe + "\"}";
    }
}
