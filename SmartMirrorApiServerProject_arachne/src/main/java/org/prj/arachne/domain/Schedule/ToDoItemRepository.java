package org.prj.arachne.domain.Schedule;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ToDoItemRepository extends JpaRepository<ToDoItem, Long> {

    List<ToDoItem> findByIdOrderByDateAsc(Long id);
}
