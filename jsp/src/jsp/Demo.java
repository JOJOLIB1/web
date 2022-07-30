package jsp;

/**
 * @className: jsp.Demo
 * @description:
 * @author: 江骏杰
 * @create: 2022-07-30 16:35
 */
public class Demo {
    private String Attribute;

    public Demo(String attribute) {
        Attribute = attribute;
    }

    public String getAttribute() {
        return Attribute;
    }

    public void setAttribute(String attribute) {
        Attribute = attribute;
    }

    public Demo() {
    }


}
