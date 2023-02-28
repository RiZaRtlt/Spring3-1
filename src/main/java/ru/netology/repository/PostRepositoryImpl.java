package ru.netology.repository;

import org.springframework.stereotype.Repository;
import ru.netology.model.Post;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

// Stub
public class PostRepositoryImpl implements PostRepository {
  protected Map<Long, Post> map = new ConcurrentHashMap<>();
  protected AtomicLong globalId = new AtomicLong(0);

  public List<Post> all() {
     return new ArrayList<>(map.values());
  }

  public Optional<Post> getById(long id) {
    return Optional.ofNullable(map.get(id));
  }

  public Post save(Post post) {
    long id = post.getId();

    if (id==0) {
      globalId.incrementAndGet();
      id = globalId.get();
      post.setId(id);
    } else {
      if (id > globalId.get()) {globalId.set(id);}
    }
    map.put(id, post);

    return post;
  }

  public void removeById(long id) {
    map.remove(id);
  }
}
