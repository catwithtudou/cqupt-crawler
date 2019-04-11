package red.rock.homework_3.Mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import red.rock.homework_3.Entity.CouAndStu;

/**
 * @author 郑煜
 * @Title: CouAndStuMapper
 * @ProjectName homework_3
 * @Description: TODO
 * @date 2019/4/4 14:26
 */
@Repository
@Mapper
public interface CouAndStuMapper {
    /**
     * 插入学生姓名和id,课程id和班级id
     */
    @Insert("INSERT  INTO couandstu(s_id,s_name,classNum,hashDay,hashLesson,week) VALUES(#{stuId},#{stuName},#{classNum},#{hashDay},#{hashLesson},#{weeklist})")
    void  insertCouAndStu(CouAndStu couAndStu);



}
