package ru.seliverstov.userservice.support;

import lombok.experimental.UtilityClass;
import org.springframework.beans.factory.annotation.Autowired;
import ru.seliverstov.userservice.model.entity.User;
import ru.seliverstov.userservice.model.entity.VacationPeriod;
import ru.seliverstov.userservice.repository.RoleRepository;
import ru.seliverstov.userservice.repository.VacationPeriodRepository;

import java.time.LocalDate;

@UtilityClass
public class DataProvider {
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    VacationPeriodRepository vacationPeriodRepository;

    public static User.UserBuilder<?, ?> prepareUser() {
        //Role role = roleRepository.findByRoleName("BackDev");
        return User.builder()
            //.role(role)
            .fio("user1");
    }

    public static VacationPeriod.VacationPeriodBuilder<?, ?> prepareVacationPeriod() {
        return VacationPeriod.builder()
            .dateFrom(LocalDate.of(2024, 5, 1))
            .dateTo(LocalDate.of(2024, 5, 31));
    }

}
