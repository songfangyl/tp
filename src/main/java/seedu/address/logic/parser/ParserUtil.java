package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.priority.Priority.isValidPriority;

import java.util.*;
import java.util.function.Predicate;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.insurance.Insurance;
import seedu.address.model.person.predicate.InsuranceContainsKeywordsPredicate;
import seedu.address.model.person.*;
import seedu.address.model.priority.Priority;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        if (address == null) {
            return EmptyAddress.getEmptyAddress();
        }

        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!NonEmptyAddress.isValidAddress(trimmedAddress)) {
            throw new ParseException(NonEmptyAddress.MESSAGE_CONSTRAINTS);
        }
        return new NonEmptyAddress(trimmedAddress);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parse a {@code String insurance} into a {@code Insurance}
     * Leading and trailing whitespaces will be trimmed.
     *
     */
    private static Insurance parseInsurance(String insurance) throws ParseException {
        requireNonNull(insurance);

        String trimmed = insurance.trim();

        if (!Insurance.isValidInsuranceName(trimmed)) {
            throw new ParseException(Insurance.MESSAGE_CONSTRAINTS);
        }

        return new Insurance(trimmed);
    }

    /**
     * Parses {@code Collection<String> insurances} into a {@code Set<Insurance>}.
     */
    public static Set<Insurance> parseInsurances(Collection<String> insurances) throws ParseException {
        requireNonNull(insurances);
        Set<Insurance> insuranceSet = new HashSet<>();
        for (String i : insurances) {
            insuranceSet.add(parseInsurance(i));
        }

        return insuranceSet;
    }

    /**
     * Parses a {@code String priority} into a {@code Priority}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code priority} is invalid.
     */
    public static Priority parsePriority(String priority) throws ParseException {
        requireNonNull(priority);
        String trimmedPriority = priority.trim();
        if (!isValidPriority(trimmedPriority)) {
            throw new ParseException((Priority.MESSAGE_CONSTRAINTS));
        }
        return new Priority(trimmedPriority);
    }

    /**
     * Parses a {@code String priority} into a {@code Priority}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code priority} is invalid.
     */
    public static Remark parseRemark(String remark) throws ParseException {
        requireNonNull(remark);
        String trimmedRemark = remark.trim();
        if (!Remark.isValidRemark(trimmedRemark)) {
            throw new ParseException((Remark.MESSAGE_CONSTRAINTS));
        }
        return new Remark(trimmedRemark);
    }

    public static Predicate<Person> parseNameKeywords(String keyword) {
        requireNonNull(keyword);
        String trimmedKeywords = keyword.trim();
        String[] keywords = trimmedKeywords.split("\\s+");
        return new NameContainsKeywordsPredicate(Arrays.asList(keywords));
    }

    public static Predicate<Person> parseInsuranceKeywords(String keyword) {
        requireNonNull(keyword);
        String trimmedKeywords = keyword.trim();
        String[] keywords = trimmedKeywords.split("\\s+");
        return new InsuranceContainsKeywordsPredicate(Arrays.asList(keywords));
    }

}
