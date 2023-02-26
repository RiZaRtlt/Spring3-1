package ru.netology.repository;

import ru.netology.model.Post;

import java.util.*;

// Stub
public class PostRepository {
  protected Map<Long, Post> map = new HashMap<>();
  protected long globalId = 0;

  public List<Post> all() {
    List<Post> list = new ArrayList<>(map.values());

    return list;
  }

  public Optional<Post> getById(long id) {
    return Optional.ofNullable(map.get(id));
  }

  public Post save(Post post) {
    long id = post.getId();

    if (id==0) {
      globalId++;
      id = globalId;
      post.setId(id);
    } else {
      if (id > globalId) {globalId = id;}
    }
    map.put(id, post);

    return post;
  }

  public void removeById(long id) {
    if (map.containsKey(id)) {
      map.remove(id);
    }
  }
}
