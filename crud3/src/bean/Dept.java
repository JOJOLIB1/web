package bean;

import java.io.Serializable;
import java.util.Objects;

/**
 * @className: bean.Dept
 * @description:
 * @author: 江骏杰
 * @create: 2022-07-23 20:40
 */
public class Dept implements Serializable {
    public static final long serialVersionUID = 100L;

    private int id;
    private int deptNo;
    private String deptName;
    private String deptLoc;

    public Dept(int id, String deptLoc) {
        this.id = id;
        this.deptLoc = deptLoc;
    }

    public Dept() {

    }

    public Dept(int id, int deptNo, String deptName, String deptLoc) {
        this.id = id;
        this.deptNo = deptNo;
        this.deptName = deptName;
        this.deptLoc = deptLoc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(int deptNo) {
        this.deptNo = deptNo;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptLoc() {
        return deptLoc;
    }

    public void setDeptLoc(String deptLoc) {
        this.deptLoc = deptLoc;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "id=" + id +
                ", deptNo=" + deptNo +
                ", deptName='" + deptName + '\'' +
                ", deptLoc='" + deptLoc + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dept dept = (Dept) o;
        return id == dept.id && deptNo == dept.deptNo && Objects.equals(deptName, dept.deptName) && Objects.equals(deptLoc, dept.deptLoc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, deptNo, deptName, deptLoc);
    }
}
