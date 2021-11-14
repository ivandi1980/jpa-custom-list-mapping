package dev.fullstackcode.jpa.mapping.entity;

import com.vladmihalcea.hibernate.type.array.ListArrayType;
import com.vladmihalcea.hibernate.type.array.StringArrayType;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import dev.fullstackcode.jpa.mapping.entity.converters.ListToJsonConverter;
import dev.fullstackcode.jpa.mapping.entity.converters.ListToStringConverter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name="users")

@NamedNativeQueries(value = { @NamedNativeQuery(name = "User.findAllUsersSortByUsertype", query = "SELECT id, first_Name, last_Name,disabled ,salary,user_type from Users ORDER BY array_position(ARRAY['PLATINUM', 'GOLD', 'SILVER'] \\:\\: varchar[], user_type)", resultClass = User.class)  })
@TypeDefs({
		@TypeDef(
				name = "string-array",
				typeClass = StringArrayType.class
		),
		@TypeDef(
				name = "list-array",
				typeClass = ListArrayType.class
		),
		@TypeDef(name = "json", typeClass = JsonBinaryType.class)
})
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(nullable=false)
	private String firstName;

	@Column(nullable=false)
	private String lastName;

	@Column
	private boolean disabled;

	@Column(precision = 2)
	private Double salary;

	@Enumerated(EnumType.STRING)
	@Column
	private UserType userType;


	@Column(columnDefinition = "json")
	@Type(type = "json")
	private List<String> preferences;

	@Column(columnDefinition = "json")
	@Convert(converter = ListToJsonConverter.class)
	private List<String> orgPreferences;

	@Column
	@Convert(converter = ListToStringConverter.class)
	private List<String> phoneNumbers;

    @Column(columnDefinition = "text[]")
	@Type(type = "dev.fullstackcode.jpa.mapping.entity.customtype.CustomStringArrayType")
	private String[] roles;

	@Type( type = "string-array" )
	@Column(
			name = "emails",
			columnDefinition = "text[]"
	)
	private String[] emails;

	@Type(type = "list-array")
	@Column(
			name = "hobbies",
			columnDefinition = "text[]"
	)
	private List<String> hobbies;




	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public List<String> getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(List<String> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	public String[] getRoles() {
		return roles;
	}

	public void setRoles(String[] roles) {
		this.roles = roles;
	}

	public String[] getEmails() {
		return emails;
	}

	public void setEmails(String[] emails) {
		this.emails = emails;
	}

	public List<String> getHobbies() {
		return hobbies;
	}

	public void setHobbies(List<String> hobbies) {
		this.hobbies = hobbies;
	}

	public List<String> getPreferences() {
		return preferences;
	}

	public void setPreferences(List<String> preferences) {
		this.preferences = preferences;
	}

	public List<String> getOrgPreferences() {
		return orgPreferences;
	}

	public void setOrgPreferences(List<String> orgPreferences) {
		this.orgPreferences = orgPreferences;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		User user = (User) o;
		return disabled == user.disabled && id.equals(user.id) && firstName.equals(user.firstName) && lastName.equals(user.lastName) && Objects.equals(salary, user.salary) && userType == user.userType && Objects.equals(preferences, user.preferences) && Objects.equals(orgPreferences, user.orgPreferences) && Objects.equals(phoneNumbers, user.phoneNumbers) && Arrays.equals(roles, user.roles) && Arrays.equals(emails, user.emails) && Objects.equals(hobbies, user.hobbies);
	}

	@Override
	public int hashCode() {
		int result = Objects.hash(id, firstName, lastName, disabled, salary, userType, preferences, orgPreferences, phoneNumbers, hobbies);
		result = 31 * result + Arrays.hashCode(roles);
		result = 31 * result + Arrays.hashCode(emails);
		return result;
	}
}
