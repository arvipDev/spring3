package dev.arvip.contentcalendar.repository;

import dev.arvip.contentcalendar.model.Content;
import dev.arvip.contentcalendar.model.Status;
import dev.arvip.contentcalendar.model.Type;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ContentCollectionRepository {

    private final List<Content> contentList = new ArrayList<>();

    public ContentCollectionRepository() {}

    public List<Content> findAll() {
        return contentList;
    }

    public Optional<Content> find(Integer id) {
        return contentList.stream().filter(c -> c.id().equals(id)).findFirst();
    }

    public void add(Content content){
        contentList.removeIf(c -> c.id().equals(content.id()));
        contentList.add(content);
    }

    public void delete(Integer id){
        contentList.removeIf(c -> c.id().equals(id));
    }

    public boolean isAvailable(Integer id) {
        return contentList.stream().filter(c -> c.id().equals(id)).count() == 1;
    }

    @PostConstruct
    private void init(){
        Content content = new Content(
                LocalDateTime.now(),
                null,
                1,
        "First content",
        "Nothing much just a content",
                Status.IDEA,
                Type.ARTICLE,
        "");
        contentList.add(content);
    }
}
