package com.example.car.industry.util;

import com.example.car.industry.entity.Cars;
import com.example.car.industry.entity.Users;
import com.example.car.industry.exception.EmailDoublingException;
import com.example.car.industry.exception.PassportIdDoublingException;
import com.example.car.industry.exception.PhoneNumberDoublingException;
import com.example.car.industry.exception.RecordNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public final class VerUtil {

    public static boolean login(List<Users> users, String email, BCryptPasswordEncoder encoder, String rowPassword) throws RecordNotFoundException {
        if (isEmailUnique(users, email)) {
            return false;
        } else {
            Users userForAuthentication = null;
            for (Users user : users) {
                if (user.getEmail().equalsIgnoreCase(email)) {
                    userForAuthentication = user;
                    break;
                }
            }
            return encoder.matches(rowPassword, Objects.requireNonNull(userForAuthentication).getPassword());
        }
    }

    public static boolean isUserSaveOk(List<Users> users, String email, String phoneNumber, String passportId) throws PassportIdDoublingException, PhoneNumberDoublingException, EmailDoublingException {
        if (isEmailUnique(users, email)) {
            if (isPhoneNumberUnique(users, phoneNumber)) {
                if (isPassportIdUnique(users, passportId)) {
                    return true;
                } else {
                    throw new PassportIdDoublingException("Existing passport id!");
                }
            } else {
                throw new PhoneNumberDoublingException(String.format("Phone number %s is already used!", phoneNumber));
            }
        } else {
            throw new EmailDoublingException(String.format("Email %s is already reserved!", email));
        }
    }

    public static boolean isReservationSaveOk(List<Users> users, List<Cars> cars, String userEmail, Long carID) {
        boolean isUserOk = false;
        boolean isCarOk = false;
        for (Users user : users) {
            if (user.getEmail().equals(userEmail)) {
                isUserOk = true;
                break;
            }
        }
        for (Cars car : cars) {
            if (car.getId().equals(carID)) {
                isCarOk = true;
                break;
            }
        }
        return isUserOk && isCarOk;
    }

    public static boolean isEmailUnique(List<Users> users, String email) {
        for (Users user : users) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isPassportIdUnique(List<Users> users, String passportId) {
        for (Users user : users) {
            if (user.getPassportId().equals(passportId)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isPhoneNumberUnique(List<Users> users, String phoneNumber) {
        for (Users user : users) {
            if (user.getPhoneNumber().equals(phoneNumber)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isDateFormatOk(String date) {
        return Pattern.compile("^(?:(?:31(\\/)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/)(?:0?[13-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$")
                .matcher(date).matches();
    }
}
