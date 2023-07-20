package com.bosonit.formacion.block7crudvalidation.student.repository;

import com.bosonit.formacion.block7crudvalidation.persona.domain.Persona;
import com.bosonit.formacion.block7crudvalidation.student.controller.dto.StudentOutputDto;
import com.bosonit.formacion.block7crudvalidation.student.controller.mapper.StudentMapper;
import com.bosonit.formacion.block7crudvalidation.student.domain.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Repository
public class StudentRepositoryImpl {
    @PersistenceContext
    EntityManager entityManager;

    public List<StudentOutputDto> getCustomQuery(
            HashMap<String, Object> conditions,
            String orderBy,
            int pageNumber,
            int pageSize) {
        StudentMapper mapper = Mappers.getMapper(StudentMapper.class);

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Student> query = cb.createQuery(Student.class);
        Root<Student> root = query.from(Student.class);
        Join<Student, Persona> personaJoin = root.join("persona", JoinType.INNER);

        List<Predicate> predicates = new ArrayList<>();

        String user = "usuario";
        String name = "name";

        conditions.forEach((field, value) -> {
            switch (field) {
                case "usuario":
                    predicates.add(cb.like(personaJoin.get(field),
                            "%" + value + "%"));
                    break;
                case "name":
                    predicates.add(cb.like(personaJoin.get(field),
                            "%" + value + "%"));
                    break;
                case "surname":
                    predicates.add(cb.like(personaJoin.get(field),
                            "%" + value + "%"));
                    break;
                case "createdDateGreat":
                    predicates.add(cb.greaterThan(personaJoin.get("createdDate"),
                            (Date) value));
                    break;
                case "createdDateLess":
                    predicates.add(cb.lessThan(personaJoin.get("createdDate"),
                        (Date) value));
                    break;
                default:
                    break;
            }
        });
        query.select(root)
                .where(predicates.toArray(new Predicate[predicates.size()]));
        if(orderBy != null) {
            if (orderBy.equals(user)) {
                query.orderBy(cb.asc(personaJoin.get(user)));
            } else if (orderBy.equals(name)) {
                query.orderBy(cb.asc(personaJoin.get(name)));
            }
        }
        TypedQuery<Student> typedQuery = entityManager.createQuery(query);
        typedQuery.setFirstResult((pageNumber - 1) * pageSize);
        typedQuery.setMaxResults(pageSize);

        return typedQuery.getResultList()
                .stream()
                .map(mapper::studentToStudentOutputDto)
                .toList();
    }

}
