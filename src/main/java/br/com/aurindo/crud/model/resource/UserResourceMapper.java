package br.com.aurindo.crud.model.resource;

import br.com.aurindo.crud.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserResourceMapper {

    @Autowired
    private final EntityLinks entityLinks;
    private static final String UPDATE = "update";
    private static final String DELETE = "delete";

    public UserResourceMapper(EntityLinks entityLinks) {
        this.entityLinks = entityLinks;
    }

    public User toResource(User user) {
        User resource = new User(user.getResourceId(), user.getName(), user.getEmail());
        final Link selfLink = entityLinks.linkToSingleResource(User.class, user.getResourceId());
        resource.add(selfLink.withSelfRel());
        resource.add(selfLink.withRel(UPDATE));
        resource.add(selfLink.withRel(DELETE));
        return resource;
    }

    public List<User> toResourceCollection(Collection<User> domainObjects) {
        return domainObjects.stream()
                .map(t -> toResource(t))
                .collect(Collectors.toList());
    }

}
