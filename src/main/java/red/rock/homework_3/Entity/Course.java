package red.rock.homework_3.Entity;




import lombok.Data;

import javax.swing.border.MatteBorder;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 郑煜
 * @Title: Course
 * @ProjectName homework_3
 * @Description: 课程类
 * @date 2019/4/3 0:15
 */

@Data
public class Course implements Comparable{

    /**
     * 星期x的hash
     */
    private int  hashDay;
    /**
     * 一天的课程数的hash
     */
    private int  hashLesson;
    /**
     * 课程开始节数的hash
     */
    private int  beginLesson;
    /**
     * 星期x
     */
    private String day;
    private String classId;
    private String lesson;
    private String name;
    private String teacher;
    private String classroom;
    private String type;
    private int period=2;
    private String classNum;
    /**
     * 'X-X周'
     */
    private String rawWeek;
    /**
     * 周数的集合
     */
    private String weeklist;
    private List<Integer> week=new ArrayList<>();
    /**
     * 周数的开始
     */
    private int weekBegin=99;
    private int weekEnd=0;
    /**
     * double or all or single
     */
    private String weekModel;

    public Course(){

        weeklist = week.toString();
    }

    public Course(String classId,String name,String classNum,String type,String teacher,String time,String classroom)throws Exception{
        this.classId = classId;
        this.name=name;
        this.classNum = classNum;
        this.type = type;
        this.teacher=teacher;
        lessonTime(time);
        this.classroom = classroom;
        weeklist = week.toString();
    }



    private static final Pattern fPattern=Pattern.compile("星期(\\d).*?第(.*?)节(.*)");
    private static final Pattern sPattern=Pattern.compile(",(\\d+)周|(\\s\\d+)周|(\\d+-\\d+)周");
    private static final Pattern tPattern=Pattern.compile("(\\d+)");

     private void  lessonTime(String text){
       //星期4 第7-8节 1-5周单周,9-15周单周
         Matcher fMatcher=fPattern.matcher(text);
         if(fMatcher.find()){
             int rawDay=Integer.parseInt(fMatcher.group(1));
             this.day=WeekChinese(rawDay);
             this.hashDay=rawDay-1;
             lessonNum(fMatcher.group(2));
             String Weeks=fMatcher.group(3);
             if(Weeks.contains("单周")){
                 this.weekModel="single";
                 this.rawWeek=Weeks.replaceAll("单周","");
             }else if(Weeks.contains("双周")){
                 this.weekModel="double";
                 this.rawWeek=Weeks.replaceAll("双周","");
             }else{
                 this.weekModel="all";
                 this.rawWeek=Weeks;
             }
             if (!this.rawWeek.startsWith(" ")){
                 this.rawWeek = " " +this.rawWeek;
             }
             Matcher sMatcher=sPattern.matcher(this.rawWeek);
             while(sMatcher.find()){
                 String weekStr=sMatcher.group(0);
                 if(weekStr.contains("-")){
                     Matcher tMatcher=tPattern.matcher(weekStr);
                     if(tMatcher.find()){
                         int wB=Integer.parseInt(tMatcher.group(0));
                         if(this.weekBegin>wB){
                             this.weekBegin=wB;
                         }
                         if(tMatcher.find()){
                             int wE=Integer.parseInt(tMatcher.group(0));
                             if(this.weekEnd<wE){
                                 this.weekEnd=wE;
                             }
                             int model=2;
                             if("all".equals(this.weekModel)){
                                  model=1;
                             }
                             for(int i=wB;i<=wE;i+=model){
                                 this.week.add(i);
                             }
                         }
                     }
                 }else{
                     Matcher tMatcher=tPattern.matcher(weekStr);
                     if(tMatcher.find()){
                         int wN=Integer.parseInt(tMatcher.group(0));
                         if(this.weekBegin>=wN){
                             this.weekBegin=wN;
                         }else if(this.weekEnd<=wN){
                             this.weekEnd=wN;
                         }
                         this.week.add(wN);
                     }
                 }

             }
             Collections.sort(this.week);
         }
    }


    /**
     * 匹配星期数
     * @param day
     * @return String
     */
    private String WeekChinese(int  day){
         switch (day){
             case 1:
                 return "星期一";
             case 2:
                 return "星期二";
             case 3:
                 return "星期三";
             case 4:
                 return "星期四";
             case 5:
                 return "星期五";
             default:
                 return null;
         }
    }

    /**
     * 匹配*-*节课
     * @param num
     * @return void
     */

    private void lessonNum(String num){
        switch (num){
            case"1-2":
                this.hashLesson = 0;
                this.beginLesson = 1;
                this.lesson = "一二节";
                break;
            case "3-4":
                this.hashLesson = 1;
                this.beginLesson = 3;
                this.lesson = "三四节";
                break;
            case "5-6":
                this.hashLesson = 2;
                this.beginLesson = 5;
                this.lesson = "五六节";
                break;
            case "7-8":
                this.hashLesson = 3;
                this.beginLesson = 7;
                this.lesson = "七八节";
                break;
            case "9-10":
                this.hashLesson = 4;
                this.beginLesson = 9;
                this.lesson = "九十节";
                break;
            case "11-12":
                this.hashLesson = 5;
                this.beginLesson = 11;
                this.lesson = "十一十二节";
                break;
            default:
                break;
        }
    }



    @Override
    public int compareTo(Object o) {
        if(o instanceof Course){
            Course course=(Course) o;
            if(this.hashDay>course.getHashDay()){
                return 1;
            }
            if(this.hashDay<course.getHashDay()){
                return -1;
            }
            if(this.hashLesson>course.getHashLesson()){
                return 1;
            }
            if(this.hashLesson<course.getHashLesson()){
                return -1;
            }
        }
        return 0;
    }
}
