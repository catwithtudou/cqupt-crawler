package red.rock.homework_3.Mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import red.rock.homework_3.Entity.Course;

/**
 * @author 郑煜
 * @Title: CourseMapper
 * @ProjectName homework_3
 * @Description: Course数据库
 * @date 2019/4/4 14:24
 */
@Repository
@Mapper
public interface CourseMapper {


    /**
     * 将Cousrse对象插入
     * @param course
     * @return boolean
     */
    @Insert("INSERT IGNORE INTO course(hashDay,hashLesson,beginClass,day,classId,lesson,name,teacher,classroom,type,classNum,rawWeek,week,weekBegin,weekEnd,weekModel)" +
            "values(#{hashDay},#{hashLesson},#{beginLesson},#{day},#{classId},#{lesson},#{name},#{teacher},#{classroom},#{type},#{classNum},#{rawWeek},#{weeklist},#{weekBegin},#{weekEnd},#{weekModel})")
    void insertCou(Course course);

    /**
     * Course对象是否有重复
     * @param
     * @return
     */
    @Select("SELECT * FROM course WHERE classId=#{classId} AND classNum=#{classNum} AND week=#{weeklist}")
    @Results({
            @Result(property = "classId" ,column ="classId")
    }
    )
    String testRepeat(Course course);



}
