package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.priority.Priority;
import seedu.address.model.priority.Priority.Level;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Remark remark;
    private final Set<Tag> tags = new HashSet<>();
    private final Priority priority;

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, Remark remark, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, remark, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.remark = remark;
        this.tags.addAll(tags);
        this.priority = new Priority(Priority.NONE_PRIORITY_KEYWORD);
    }

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address,
                  Remark remark, Set<Tag> tags, Priority priority) {
        requireAllNonNull(name, phone, email, address, remark, tags, priority);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.remark = remark;
        this.tags.addAll(tags);
        this.priority = priority;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public Remark getRemark() {
        return remark;
    }

    public Priority getPriority() {
        return priority;

    }

    public Level getPriorityLevel() {
        return priority.getPriorityLevel();
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns the number of tags assigned to this person.
     */
    public int getTagsCount() {
        return tags.size();
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    /**
     * Creates and returns a {@code Person} with details of {@code source}, adding tags in {@code tagsToAdd} and
     * removing tags in {@code tagsToDelete}.
     */
    public static Person createPersonWithUpdatedTags(Person source,
            Collection<Tag> tagsToAdd,
            Collection<Tag> tagsToDelete) {
        requireAllNonNull(source, tagsToAdd, tagsToDelete);

        Set<Tag> updatedTags = new HashSet<>(source.tags);
        updatedTags.removeAll(tagsToDelete);
        updatedTags.addAll(tagsToAdd);

        return new Person(source.name, source.phone, source.email, source.address,
                source.remark, updatedTags, source.priority);
    }

    /**
     * Creates and returns a {@code Person} with details of {@code source}, assigning priority of
     * {@code newPriority}.
     */
    public static Person createPersonWithUpdatedPriority(Person personToUpdate, Priority newPriority) {
        requireAllNonNull(personToUpdate, newPriority);
        return new Person(personToUpdate.name, personToUpdate.phone, personToUpdate.email, personToUpdate.address,
                personToUpdate.remark, personToUpdate.tags, newPriority);
    }

    /**
     * Creates and returns a {@code Person} with details of {@code personToEdit} edited with
     * {@code editPersonDescriptor}.
     */
    public static Person createPersonWithEditedInformation(Person personToEdit,
                                                            EditPersonDescriptor editPersonDescriptor) {
        requireAllNonNull(personToEdit, editPersonDescriptor);

        Name newName = editPersonDescriptor.getName().orElse(personToEdit.name);
        Phone newPhone = editPersonDescriptor.getPhone().orElse(personToEdit.phone);
        Email newEmail = editPersonDescriptor.getEmail().orElse(personToEdit.email);
        Address newAddress = editPersonDescriptor.getAddress().orElse(personToEdit.address);
        Remark remark = personToEdit.remark;
        Set<Tag> tags = personToEdit.tags;
        Priority priority = personToEdit.priority;

        return new Person(newName, newPhone, newEmail, newAddress, remark, tags, priority);
    }

    /**
     * Returns true is the Person has the same priority as {@code priority}.
     */
    public boolean hasSamePriority(Priority priority) {
        return this.priority.equals(priority);
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return name.equals(otherPerson.name)
                && phone.equals(otherPerson.phone)
                && email.equals(otherPerson.email)
                && address.equals(otherPerson.address)
                && remark.equals(otherPerson.remark)
                && tags.equals(otherPerson.tags)
                && priority.equals(otherPerson.priority);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, remark, tags, priority);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("address", address)
                .add("remark", remark)
                .add("tags", tags)
                .add("priority", priority)
                .toString();
    }
}
