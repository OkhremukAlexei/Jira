package com.jira.repos;

import com.jira.models.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepo extends CrudRepository<Comment,Integer> {
}
