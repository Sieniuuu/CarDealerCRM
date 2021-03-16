package pl.artursienkowski.service.search;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pl.artursienkowski.model.Customer;
import pl.artursienkowski.model.User;
import pl.artursienkowski.repository.UserRepository;

import java.util.Collections;
import java.util.List;

@Service
public class UserSeacherServis {

    private UserRepository userRepository;

    public UserSeacherServis(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<User> executeQuery(String searchMode, String query) {
            if (StringUtils.isEmpty(searchMode)) {
                return userRepository.reversUserList();
            }

            if(StringUtils.isEmpty(query)) {
                return userRepository.reversUserList();
            }

            switch (searchMode) {
                case "firstName":
                    return userRepository.findUserByFirstNamePart(query);
                case "lastName":
                    return userRepository.findUserByLastNamePart(query);
                case "pesel":
                    return userRepository.findUserByPESELPart(query);
                case "branch":
                    return userRepository.findUserByBranch(query);
                case "type":
                    return userRepository.findUserByType(query);
            }

            return Collections.emptyList();
        }


}
