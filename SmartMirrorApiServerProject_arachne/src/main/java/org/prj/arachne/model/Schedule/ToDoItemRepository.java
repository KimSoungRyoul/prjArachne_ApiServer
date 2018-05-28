package org.prj.arachne.model.Schedule;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ToDoItemRepository extends JpaRepository<ToDoItem, Long> {

    List<ToDoItem> findByItemOwnerMemberIdOrderByDateAsc(Long itemOwnerMemberId);
}
