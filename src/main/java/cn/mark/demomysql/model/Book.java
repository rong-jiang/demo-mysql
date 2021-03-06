package cn.mark.demomysql.model;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;
@Data
//@TableName("t_book")
public class Book implements Serializable{
    private Integer id;

    //导出
    @ExcelField(title = "名字")
    //导入
    @ExcelProperty(value = "姓名")
    //swagger上Model的注释
    @ApiModelProperty(value = "姓名")
    private String name;

    @ExcelField(title = "性别")
    @ExcelProperty(value = "性别")
    @ApiModelProperty(value = "性别")
    private String setb;

    @ExcelField(title = "年龄")
    @ExcelProperty(value = "年龄")
    @ApiModelProperty(value = "年龄")
    private Integer age;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Book(Integer id, String name, String setb, Integer age) {
        this.id = id;
        this.name = name;
        this.setb = setb;
        this.age = age;
    }

    public Book(){
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSetb() {
        return setb;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setSetb(String setb) {

        this.setb = setb;
    }
}