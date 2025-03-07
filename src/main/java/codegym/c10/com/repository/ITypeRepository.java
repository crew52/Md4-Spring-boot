package codegym.c10.com.repository;

import codegym.c10.com.dto.ITypeDTO;
import codegym.c10.com.model.Type;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ITypeRepository extends CrudRepository<Type, Long> {
    @Query(nativeQuery = true,
            value = "SELECT \n" +
                    "    t.id, \n" +
                    "    t.name, \n" +
                    "    COUNT(t.name) AS count\n" +
                    "FROM \n" +
                    "    computer c\n" +
                    "RIGHT JOIN \n" +
                    "    type t\n" +
                    "ON \n" +
                    "    c.type_id = t.id\n" +
                    "GROUP BY \n" +
                    "    t.name,  \n" +
                    "    t.id;")
    Iterable<ITypeDTO> getAllTypes();

    @Query(nativeQuery = true, value = "CALL delete_type(:id)")
    @Transactional
    @Modifying
    void deleteTypeById(@Param("id") Long id);
}
