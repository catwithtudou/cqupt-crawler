package red.rock.homework_3.Crawler;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.apache.ibatis.executor.Executor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import red.rock.homework_3.Entity.Course;
import red.rock.homework_3.Entity.Student;
import red.rock.homework_3.Mapper.CouAndStuMapper;
import red.rock.homework_3.Mapper.CourseMapper;
import red.rock.homework_3.Mapper.StudentMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static java.lang.System.exit;
import static java.lang.System.setOut;


/**
 * @author 郑煜
 * @Title: ThreadCrawler
 * @ProjectName homework_3
 * @Description:
 * @date 2019/4/4 14:56
 */


@Component
public class ThreadCrawler implements Runnable {

    @Autowired
    private CouAndStuMapper couAndStuMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private CourseMapper courseMapper;


    private static KbCrawler kbCrawler=KbCrawler.getInstance();
    private static CopyOnWriteArrayList<String> urllist=new CopyOnWriteArrayList<>();
    private static CopyOnWriteArrayList<String> idlist=new CopyOnWriteArrayList<>();
    private static CopyOnWriteArrayList<Student> students=new CopyOnWriteArrayList<>();
    private static final String URL_KB="http://jwzx.cqupt.edu.cn/kebiao/kb_stu.php?xh=";


    @Override
    public void run() {
        while (true) {
            try {
                synchronized (ThreadCrawler.class) {
                    if (urllist.size() == 0 && idlist.size() == 0) {
                        break;
                    }
                    if (urllist.size() != 0 && idlist.size() != 0) {
                        List<Course> courses = kbCrawler.getCourseList(urllist.get(0));
                        if (courses == null || courses.isEmpty()) {
                            urllist.remove(0);
                            idlist.remove(0);
                            continue;
                        }
                        Student student = new Student(idlist.get(0), kbCrawler.getStu(idlist.get(0), urllist.get(0)), courses);
                        students.add(student);
                        urllist.remove(0);
                        idlist.remove(0);

                    }

                }
            } catch (Exception e) {
                e.printStackTrace();

            }
        }
    }

    public static void getStudent(){
        for (int i = 0; i <= 5; i++) {
            for (int j = 0; j <= 9 ; j++) {
                for (int k = 0; k <= 9 ; k++) {
                    for (int l = 0; l <= 9; l++) {
                        idlist.add("201821" + i + j + k + l );
                        urllist.add(URL_KB+"201821" + i + j + k + l);
                    }
                }
            }
        }

//        for(int i=0;i<2;i++){
//            idlist.add("201821139"+i);
//            urllist.add(URL_KB+"201821139"+i);
//        }
    }

    public List<Student> getStudents ()throws Exception {

        long begin = System.currentTimeMillis();

        getStudent();

        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("demo-pool-%d").build();

        ExecutorService pool = new ThreadPoolExecutor(5, 200,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

        for (int i = 0; i < 100; i++) {
            pool.execute(new ThreadCrawler());
        }
        pool.shutdown();
        while (true) {
            if (pool.isTerminated()) {
                long end = System.currentTimeMillis();
                System.out.println("time:" + (end - begin));
                System.out.println("-------------------");
                System.out.println(students.size());
                for(int i=0;i<students.size();i++){
                    System.out.println(students.get(i).toString());
                }
                return students;
            }
        }
    }
}

