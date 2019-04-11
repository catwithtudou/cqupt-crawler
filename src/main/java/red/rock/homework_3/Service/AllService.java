package red.rock.homework_3.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import red.rock.homework_3.Crawler.ThreadCrawler;
import red.rock.homework_3.Entity.CouAndStu;
import red.rock.homework_3.Entity.Course;
import red.rock.homework_3.Entity.Student;
import red.rock.homework_3.Mapper.CouAndStuMapper;
import red.rock.homework_3.Mapper.CourseMapper;
import red.rock.homework_3.Mapper.StudentMapper;

import java.util.List;

/**
 * @author 郑煜
 * @Title: AllService
 * @ProjectName homework_3
 * @Description: TODO
 * @date 2019/4/9 17:12
 */

@Service
public class AllService {
    @Autowired
    private CouAndStuMapper couAndStuMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private ThreadCrawler threadCrawler;

    /**
     * 插入所有学生信息
     * @param
     * @return
     */
    public void AddAllStudent()throws Exception{
        List<Student> students=threadCrawler.getStudents();
        for(Student student:students){
           studentMapper.insetStu(student);
           for(Course course:student.getStuCourses()){
               String flag=courseMapper.testRepeat(course);
               if(flag==null||flag.isEmpty()) {
                   courseMapper.insertCou(course);
               }
               CouAndStu couAndStu = new CouAndStu( student.getStuId(), student.getStuName(),course.getClassNum(),course.getHashDay(),course.getHashLesson(),course.getWeeklist());
               couAndStuMapper.insertCouAndStu(couAndStu);
           }
        }
    }
}
