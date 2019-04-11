package red.rock.homework_3.Mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import red.rock.homework_3.Entity.Student;

/**
 * @author 郑煜
 * @Title: StudentMapper
 * @ProjectName homework_3
 * @Description: TODO
 * @date 2019/4/4 14:25
 */
@Repository
@Mapper
public interface StudentMapper {

    /**
     * 插入Student对象
     * @param student
     * @return
     */
    @Insert("INSERT IGNORE INTO student(s_id,s_name) VALUES(#{stuId},#{stuName})")
    void insetStu(Student student);
}
