package ru.seliverstov.userservice.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.seliverstov.userservice.exception.ErrorCode;
import ru.seliverstov.userservice.exception.ServiceException;
import ru.seliverstov.userservice.mapper.VacationPeriodMapper;
import ru.seliverstov.userservice.model.dto.AddVacationPeriodRq;
import ru.seliverstov.userservice.model.dto.AddVacationPeriodRs;
import ru.seliverstov.userservice.model.dto.PutVacationPeriodRq;
import ru.seliverstov.userservice.model.entity.User;
import ru.seliverstov.userservice.model.entity.VacationPeriod;
import ru.seliverstov.userservice.repository.UserRepository;
import ru.seliverstov.userservice.repository.VacationPeriodRepository;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class VacationPeriodService {

    private final VacationPeriodRepository vacationPeriodRepository;
    private final UserRepository userRepository;
    private final VacationPeriodMapper vacationPeriodMapper;

    public AddVacationPeriodRs postAddVocationPeriod(final AddVacationPeriodRq addVacationPeriodRq) {
        final LocalDate dateFromLocalDate = vacationPeriodMapper.toLocalDate(addVacationPeriodRq.getDateFrom());
        final LocalDate dateToLocalDate = vacationPeriodMapper.toLocalDate(addVacationPeriodRq.getDateTo());
        final User user = userRepository.findById(addVacationPeriodRq.getUserId())
            .orElseThrow(() -> new ServiceException(ErrorCode.ERR_CODE_002, addVacationPeriodRq.getUserId()));

        final VacationPeriod vacationPeriod = VacationPeriod.builder()
            .user(user)
            .dateFrom(dateFromLocalDate)
            .dateTo(dateToLocalDate)
            .build();

        vacationPeriodRepository.save(vacationPeriod);

        return vacationPeriodMapper.toAddVacationPeriodRs(vacationPeriod);
    }

    public AddVacationPeriodRs getVocationPeriod(final Long id) {
        final VacationPeriod vacationPeriod = vacationPeriodRepository.findById(id)
            .orElseThrow(() -> new ServiceException(ErrorCode.ERR_CODE_004, id));

        return vacationPeriodMapper.toAddVacationPeriodRs(vacationPeriod);
    }

    public void deleteVacationPeriod(final Long id) {
        vacationPeriodRepository.deleteById(id);
    }

    @Transactional
    public AddVacationPeriodRs putVacationPeriod(final PutVacationPeriodRq request) {
        final VacationPeriod vacationPeriod = vacationPeriodRepository.findById(request.getId())
            .orElseThrow(() -> new ServiceException(ErrorCode.ERR_CODE_004, request.getId()));
        vacationPeriod.setDateFrom(vacationPeriodMapper.toLocalDate(request.getDateFrom()));
        vacationPeriod.setDateTo(vacationPeriodMapper.toLocalDate(request.getDateTo()));
        return vacationPeriodMapper.toAddVacationPeriodRs(vacationPeriod);
    }
}
