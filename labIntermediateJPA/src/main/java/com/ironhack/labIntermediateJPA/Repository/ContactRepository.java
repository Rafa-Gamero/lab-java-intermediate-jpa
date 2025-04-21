package com.ironhack.labIntermediateJPA.Repository;



import com.ironhack.labIntermediateJPA.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    // Ejemplo de consulta JPQL para encontrar contactos por compañía
    List<Contact> findByCompany(String company);

    // Ejemplo de consulta SQL nativo para encontrar contactos cuyo nombre de pila comience con una letra específica
    @Query(value = "SELECT * FROM contact c JOIN name n ON c.id = n.contact_id WHERE n.first_name LIKE :prefix%", nativeQuery = true)
    List<Contact> findByFirstNameStartingWithNative(@Param("prefix") String prefix);

    // NUEVAS CONSULTAS

    // JPQL: Encontrar todos los contactos ordenados por nombre (apellido luego nombre de pila)
    @Query("SELECT c FROM Contact c ORDER BY c.name.lastName, c.name.firstName")
    List<Contact> findAllOrderByLastNameFirstName();

    // JPQL: Encontrar contactos que trabajen en una compañía específica y tengan un título determinado
    @Query("SELECT c FROM Contact c WHERE c.company = :company AND c.title = :title")
    List<Contact> findByCompanyAndTitle(@Param("company") String company, @Param("title") String title);

    // SQL Nativo: Encontrar el número total de contactos en una compañía específica
    @Query(value = "SELECT COUNT(c.id) FROM contact c WHERE c.company = :company", nativeQuery = true)
    long countByCompanyNative(@Param("company") String company);

    // SQL Nativo: Encontrar los nombres completos (salutation, firstName, middleName, lastName) de todos los contactos
    @Query(value = "SELECT CONCAT(n.salutation, ' ', n.first_name, ' ', COALESCE(n.middle_name, ''), ' ', n.last_name) FROM contact c JOIN name n ON c.id = n.contact_id", nativeQuery = true)
    List<String> findAllFullNamesNative();
}
