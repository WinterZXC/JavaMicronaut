package com.example;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.PageableRepository;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface LogsRepository extends PageableRepository<Logs, Long>{
    Logs save(@NonNull @NotBlank String date,@NonNull @NotBlank String regNumber,@NonNull @NotBlank String ownerName,@NonNull @NotBlank Integer odometerStart,@NonNull @NotBlank Integer odometerEnd,@NonNull @NotBlank String route,@NonNull @NotBlank String description);
    
    void deleteById(Long id);

    int update(@NotNull @Id Long id, @NonNull @NotBlank String date, @NonNull @NotBlank String regNumber, @NonNull @NotBlank String ownerName, @NonNull @NotBlank Integer odometerStart, @NonNull @NotBlank Integer odometerEnd, @NonNull @NotBlank String route, @NonNull @NotBlank String description);

    @Query("SELECT * FROM logs WHERE reg_number = :regNumber ORDER BY date, odometer_start")
    List<Logs> findByRegNumber(String regNumber);

    @Query("SELECT * FROM logs WHERE owner_name = :ownerName ORDER BY date, odometer_start")
    List<Logs> findByName(String ownerName);

    @Query("SELECT * FROM logs WHERE date BETWEEN :date1 AND :date2 ORDER BY date, odometer_start")
    List<Logs> findByDate(String date1, String date2);

    @Query("SELECT * FROM logs WHERE owner_name = :ownerName AND reg_number = :regNumber ORDER BY date, odometer_start")
    List<Logs> findByNameAndRegNumber(String ownerName, String regNumber);

    @Query("SELECT * FROM logs WHERE owner_name = :ownerName AND date BETWEEN :date1 AND :date2 ORDER BY date, odometer_start")
    List<Logs> findByNameAndDate(String ownerName, String date1, String date2);

    @Query("SELECT * FROM logs WHERE reg_number = :regNumber AND date BETWEEN :date1 AND :date2 ORDER BY date, odometer_start")
    List<Logs> findByRegNumberAndDate(String regNumber, String date1, String date2);

    @Query("SELECT * FROM logs WHERE owner_name = :ownerName AND reg_number = :regNumber AND date BETWEEN :date1 AND :date2 ORDER BY date, odometer_start")
    List<Logs> findByNameAndRegNumberAndDate(String ownerName, String regNumber, String date1, String date2);
}