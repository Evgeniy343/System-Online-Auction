package by.evgen.authenticationservice.annotation;

import org.mapstruct.Mapping;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.CLASS)
@Mapping(target = "name", ignore = true)
@Mapping(target = "surname", ignore = true)
@Mapping(target = "patronymic", ignore = true)
@Mapping(target = "photo", ignore = true)
@Mapping(target = "phoneNumber", ignore = true)
@Mapping(target = "registrationAddress", ignore = true)
@Mapping(target = "copiesOfDocuments", ignore = true)
@Mapping(target = "identification", ignore = true)
@Mapping(target = "requisitesForReturnOfDeposit", ignore = true)
public @interface ForAuthenticate {
}

