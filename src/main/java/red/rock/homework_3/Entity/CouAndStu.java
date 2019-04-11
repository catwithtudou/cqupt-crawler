package red.rock.homework_3.Entity;

import lombok.Data;

/**
 * @author 郑煜
 * @Title: CouAndStu
 * @ProjectName homework_3
 * @Description: TODO
 * @date 2019/4/4 14:20
 */

@Data
public class CouAndStu {

    private String stuId;
    private String stuName;
    private String classNum;
    private int hashDay;
    private int hashLesson;
    private String weeklist;


    public CouAndStu(String stuId,String stuName,String classNum,int hashDay,int hashLesson,String weeklist){
        this.hashDay=hashDay;
        this.stuName=stuName;
        this.classNum=classNum;
        this.stuId=stuId;
        this.hashLesson=hashLesson;
        this.weeklist=weeklist;
    }

}
