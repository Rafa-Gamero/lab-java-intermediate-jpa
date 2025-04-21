package com.ironhack.labIntermediateJPA.Repository;

import com.ironhack.labIntermediateJPA.models.BillableTask;
import com.ironhack.labIntermediateJPA.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    // Ejemplo de consulta JPQL para encontrar tareas por fecha de vencimiento
    List<Task> findByDueDate(LocalDate dueDate);

    // Ejemplo de consulta JPQL para encontrar tareas completadas
    List<Task> findByStatusTrue();

    // Ejemplo de consulta SQL nativo para encontrar tareas cuyo título contenga una palabra clave (insensible a mayúsculas/minúsculas)
    @Query(value = "SELECT * FROM task t WHERE LOWER(t.title) LIKE LOWER(CONCAT('%', :keyword, '%'))", nativeQuery = true)
    List<Task> findByTitleContainingIgnoreCaseNative(@Param("keyword") String keyword);

    // NUEVAS CONSULTAS

    // JPQL: Encontrar todas las tareas ordenadas por fecha de vencimiento
    @Query("SELECT t FROM Task t ORDER BY t.dueDate")
    List<Task> findAllOrderByDueDate();

    // JPQL: Encontrar el número total de tareas
    @Query("SELECT COUNT(t) FROM Task t")
    long countAllTasks();

    // SQL Nativo: Encontrar las 3 tareas con fechas de vencimiento más próximas
    @Query(value = "SELECT * FROM task ORDER BY due_date ASC LIMIT 3", nativeQuery = true)
    List<Task> findTop3ByDueDateNative();

    // SQL Nativo: Encontrar todas las tareas creadas en un año específico (asumiendo que tienes una columna 'created_at')
    @Query(value = "SELECT * FROM task WHERE YEAR(due_date) = :year", nativeQuery = true)
    List<Task> findByDueDateYearNative(@Param("year") int year);

    // JPQL: Encontrar todas las tareas que son instancias de BillableTask
    @Query("SELECT b FROM BillableTask b")
    List<BillableTask> findAllBillableTasks();
}