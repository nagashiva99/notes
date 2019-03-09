package com.siva.notes.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.siva.notes.model.Note;

@Repository
public interface NoteRepository extends CrudRepository<Note, Long> {

	List<Note> findByCreatorId(long creatorId);
}
