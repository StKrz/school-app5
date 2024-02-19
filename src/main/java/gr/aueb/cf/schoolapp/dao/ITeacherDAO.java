package gr.aueb.cf.schoolapp.dao;

import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.model.Teacher;

import java.util.List;

/**
 * DAO (Data Access Object) gives an API for the
 * client's requests.
 */
public interface ITeacherDAO {
    Teacher insert(Teacher teacher) throws TeacherDAOException;
    Teacher update(Teacher teacher) throws  TeacherDAOException;
    void delete(Integer id) throws TeacherDAOException;
    Teacher getById(Integer id) throws TeacherDAOException;

    /** Instead of:
     *  Teacher getByLastname(String lastname) throws TeacherDAOException;
     *  it returns a list of teachers with the same lastname.
     */
    List<Teacher> getByLastname(String lastname) throws TeacherDAOException;

}
