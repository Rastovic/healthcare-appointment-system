package com.main.app.Model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "address_1")
    private String address1;

    @Column(name = "address_2")
    private String address2;

    @Column(name = "hospital_id", nullable = false)
    private Long hospitalId;

    @Column(name = "medical_condition", columnDefinition = "MEDIUMTEXT")
    private String medicalCondition;

    @Column(name = "staff_number", length = 20)
    private String staffNumber;

    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "password", length = 30)
    private String password;

    @Column(name = "password_hash", length = 250)
    private String passwordHash;

    @Column(name = "password_expiry_date")
    @Temporal(TemporalType.DATE)
    private Date passwordExpiryDate;

    @Column(name = "pin", length = 10)
    private String pin;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "known_as")
    private String knownAs;

    @Column(name = "permit_details")
    private String permitDetails;

    @Column(name = "status")
    private Long status;

    @Column(name = "bank_bic")
    private String bankBIC;

    @Column(name = "bank_iban")
    private String bankIBAN;

    @Column(name = "bank_address_1")
    private String bankAddress1;

    @Column(name = "bank_phone")
    private String bankPhone;

    @Column(name = "bank_fax")
    private String bankFax;

    @Column(name = "department_id")
    private Long departmentId;

    @Column(name = "sex_id")
    private Integer sexId;

    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    private Date endDate;

    @Column(name = "was_terminated")
    private Boolean wasTerminated;

    @Column(name = "picture")
    private String picture;

    @Column(name = "tax_file_number")
    private String taxFileNumber;

    @Column(name = "birth_date")
    @Temporal(TemporalType.DATE)
    private LocalDate birthDate;

    @Column(name = "maritial_status_id")
    private Integer maritialStatusId;

    @Column(name = "country_id")
    private Long countryId;

    @Column(name = "passport_number")
    private String passportNumber;

    @Column(name = "kin_phone")
    private String kinPhone;

    @Column(name = "job_title_id")
    private Long jobTitleId;

    @Column(name = "job_title_previous_id")
    private Long jobTitlePreviousId;

    @Column(name = "location_id")
    private Long locationId;

    @Column(name = "doctor_id")
    private Long doctorId;

    @Column(name = "email")
    private String email;

    @Column(name = "room")
    private String room;

    @Column(name = "bank_id")
    private Integer bankId;

    @Column(name = "bank_name")
    private String bankName;

    @Column(name = "bank_code")
    private String bankCode;

    @Column(name = "bank_account_name")
    private String bankAccountName;

    @Column(name = "bank_account_number")
    private String bankAccountNumber;

    @Column(name = "phone")
    private String phone;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "deleted")
    private Boolean deleted;

    @Column(name = "number_of_holidays")
    private Integer numberOfHolidays;

    @Column(name = "number_of_holidays_spent")
    private Integer numberOfHolidaysSpent;

    @Column(name = "hours_per_day")
    private Integer hoursPerDay;

    @Column(name = "security_level_id")
    private Long securityLevelId;

    @Column(name = "pay_type_id")
    private Long payTypeId;

    @Column(name = "permit_required")
    private Boolean permitRequired;

    @Column(name = "pay_frequency_id")
    private Long payFrequencyId;

    @Column(name = "employment_type_id")
    private Long employmentTypeId;

    @Column(name = "pay_method_id")
    private Long payMethodId;

    @Column(name = "reason_for_leaving", columnDefinition = "MEDIUMTEXT")
    private String reasonForLeaving;

    @Column(name = "flexibility", columnDefinition = "MEDIUMTEXT")
    private String flexibility;

    @Column(name = "pos_access_code", length = 25)
    private String posAccessCode;

    @Column(name = "reactivation_date")
    @Temporal(TemporalType.DATE)
    private Date reactivationDate;

    @Column(name = "standard_number_of_hours")
    private Integer standardNumberOfHours;

    @Column(name = "standard_number_of_hours_day")
    private Integer standardNumberOfHoursDay;

    @Column(name = "number_of_certified_sickdays", precision = 11, scale = 2)
    private BigDecimal numberOfCertifiedSickDays;

    @Column(name = "number_of_uncertified_sickdays", precision = 11, scale = 2)
    private BigDecimal numberOfUncertifiedSickDays;

    @Column(name = "contract_issued_date")
    @Temporal(TemporalType.DATE)
    private Date contractIssuedDate;

    @Column(name = "master_person_id")
    private Long masterPersonId;

    @Column(name = "original_person_id")
    private Long originalPersonId;

    @Column(name = "avatar_url", length = 1000)
    private String avatarUrl;

    @Column(name = "external_id")
    private String externalId;
}
