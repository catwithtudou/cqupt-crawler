package red.rock.homework_3.Entity;

import lombok.Data;

import java.util.List;

/**
 * @author 郑煜
 * @Title: Student
 * @ProjectName homework_3
 * @Description: 学生类
 * @date 2019/4/3 16:23
 */

@Data
public class Student   {

    private String stuId;
    private String stuName;
    private List<Course>  stuCourses;

    public Student(String stuId,String stuName,List<Course> stuCourses){
        this.stuCourses=stuCourses;
        this.stuName = stuName;
       this.stuId=stuId;
    }

    @Override
    public String toString(){
        return "id:"+stuId+
                "   stuname:"+stuName;
    }
}
