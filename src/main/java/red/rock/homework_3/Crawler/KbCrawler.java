package red.rock.homework_3.Crawler;


import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;
import red.rock.homework_3.Entity.Course;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author 郑煜
 * @Title: KbCrawler
 * @ProjectName homework_3
 * @Description: 爬取类
 * @date 2019/4/2 21:49
 */

@Component
public class KbCrawler {
    public static KbCrawler instance;

    public static KbCrawler getInstance(){
        if(instance == null){
            synchronized (KbCrawler.class){
                if(instance == null){
                    instance = new KbCrawler();
                }
            }
        }
        return instance;
    }

    /**
     * 获取网页html内容
     * @param url
     * @return String
     */
   public  String getHtml(String url)throws Exception{
       HttpClient client= HttpClients.createDefault();
       HttpGet  httpGet=new HttpGet(url);
       HttpResponse response=client.execute(httpGet);
       HttpEntity entity=response.getEntity();
       String content= EntityUtils.toString(entity,"utf-8");
       return content;
   }

   private static final String MAINTEXT="(?<=[\\s|\\S]</thead><tbody>)<tr >[\\S|\\s]+<td rowspan='1'></td>";
   private static final String TDTEXT="<td(.*?)>(.*?)</td>";
   private static final String ROWSPAN="<td rowspan='(.*?)'></td>";
   private static final String TEXTURL="http://jwzx.cqupt.edu.cn/kebiao/kb_stu.php?xh=2018211391#kbStuTabs-list";

   
   /**
    * 获取课表内容
    * @param url    
    * @return List<Course></Course>
    */
   public  List<Course> getCourseList(String url)throws Exception{
       String html=getHtml(url);
       Pattern firstPattern=Pattern.compile(MAINTEXT);
       String[] trs=new String[30];
       List<Course> courses=new ArrayList<>();
       Matcher firstMatcher=firstPattern.matcher(html);
       if(firstMatcher.find()){
           String text=firstMatcher.group();
           trs=text.split("</tr>");
       }else{
           return null;
       }
       Pattern secondPattern=Pattern.compile(TDTEXT);
       for(int i=0;i<trs.length;i++) {
           Matcher secondMatcher = secondPattern.matcher(trs[i]);
           Pattern thirdPattern=Pattern.compile(ROWSPAN);
           Matcher thirdMatcher =thirdPattern.matcher(trs[i]);
           String classId="";
           String name="";
           String classNum="";
           String type="";
           String teacher="";
           String time="";
           String location="";
           if(thirdMatcher.find()){
               int rowspan=Integer.parseInt(thirdMatcher.group(1));
               for(int j=1;j<=rowspan;j++){
                   //j=0即全部信息的时候
                   if(j==1){
                       for(int k=0;k<10;k++){
                           if(secondMatcher.find()) {
                               switch (k) {
                                   case 1:
                                       String all=secondMatcher.group(2);
                                       classId =all.substring(0,all.indexOf("-"));
                                       name=all.substring(all.indexOf("-")+1);
                                       break;
                                   case 2:
                                       classNum=secondMatcher.group(2);
                                       break;
                                   case 3:
                                       type=secondMatcher.group(2);
                                       break;
                                   case 5:
                                       teacher=secondMatcher.group(2);
                                       break;
                                   case 6:
                                       time=secondMatcher.group(2);
                                       break;
                                   case 7:
                                       location=secondMatcher.group(2);
                                       break;
                                   default:
                                       break;
                               }
                           }
                       }
                       if(!"选修".equals(type)) {
                           Course course = new Course(classId, name, classNum, type, teacher, time, location);
                           courses.add(course);
                       }
                   }
                   //j>1的时候便是教室和时间的重新更新但依然存入一个新的对象中,在教室和时间更新也是在下一个i中重新匹配secondMatter
                   if(j>1){
                       i++;
                       secondMatcher=secondPattern.matcher(trs[i]);
                       for(int k=0;k<3;k++){
                           if(secondMatcher.find()){
                               switch (k){
                                   case 1:
                                       time=secondMatcher.group(2);
                                       break;
                                   case 2:
                                       location=secondMatcher.group(2);
                                       break;
                                   default:
                                       break;
                               }
                           }
                       }
                       if(!"选修".equals(type)) {
                           Course course = new Course(classId, name, classNum, type, teacher,time, location);
                           courses.add(course);
                       }
                   }
               }
           }else{
               return null;
           }

       }
       Collections.sort(courses);
       return courses;
   }


   private static final String STUDENTNAME="<ul><li><a href=(.*?)>(.*?)</a></li>\\s+<li>(.*?)</li>";
   /**
    * 获取学生姓名和学号
    * @param 
    * @return 
    */
   public  String getStu(String stuId,String url)throws Exception{
       String html=getHtml(url);
       Pattern pattern=Pattern.compile(STUDENTNAME);
       Matcher matcher=pattern.matcher(html);
       String stuName="";
       if(matcher.find()){
           String text=matcher.group(3);
           stuName=text.substring(text.lastIndexOf(stuId)+10);
       }
       return stuName;
   }

   
   
   
    public static void main(String[] args)throws Exception {
//        String url="http://jwzx.cqupt.edu.cn/kebiao/kb_stu.php?xh=2018211391#kbStuTabs-list";
//        String text="(?<=[\\s|\\S]</thead><tbody>)<tr >[\\S|\\s]+<td rowspan='1'></td>";
//        String textt="<td(.*?)>(.*?)</td>";
//        String texxxxx="<ul><li><a href=(.*?)>(.*?)</a></li>\\s+<li>(.*?)</li>";
//        Pattern p = Pattern.compile(text);
//        Matcher m=p.matcher(getHtml(url));
//        Pattern ppppp=Pattern.compile(texxxxx);
//        Matcher mam= ppppp.matcher(getHtml(url));
//        if(mam.find()){
//            System.out.println(mam.group(0));
//            System.out.println(mam.group(1));
//            System.out.println(mam.group(2));
//            System.out.println(mam.group(3));
//            System.out.println(mam.group(3).substring(mam.group(3).lastIndexOf("2018211391")+10));
//        }
//        System.out.println(getStu("2018211391",url));
//        String[] list=new String [30];
//        String[] listt=new String [30];
//        System.out.println("start");
//        if(m.find()){
//            String str=m.group();
//            list=str.split("</tr>");
//        }
//        List<Course> list1=getCourseList(TEXTURL);
//        Collections.sort(list1);
//        for(Course course:list1){
//            System.out.println(course.toString());
//        }
    }
}
