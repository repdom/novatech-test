package repositories;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import entities.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {
    @Inject
    EntityManager entityManager;

    Faker faker = new Faker();
    List<User> users = new ArrayList<>();

    @Transactional
    public List<User> saveAll(List<models.User> users) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<User> userToPersist = objectMapper.convertValue(users, new TypeReference<List<User>>(){});
        for (User u: userToPersist) {
            var userOptional = this.findByIdOptional(u.getId());
            if(userOptional.isPresent()) {
                userOptional.get().setStatus(u.getStatus());
                userOptional.get().setGender(u.getGender());
                userOptional.get().setEmail(u.getEmail());
                userOptional.get().setName(u.getName());
                this.entityManager.merge(u);
            } else {
                this.entityManager.persist(u);
            }
        }
        return userToPersist;
    }

}
