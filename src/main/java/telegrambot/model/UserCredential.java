package telegrambot.model;

import lombok.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserCredential implements Serializable {

  private static final long serialVersionUID = 5682610754719606155L;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_cred_generator")
  @SequenceGenerator(
      name = "user_cred_generator",
      sequenceName = "user_cred_sequence",
      allocationSize = 1)
  @Column
  private Long id;

  @Column private String username;

  @Column private String password;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;

    if (!(o instanceof UserCredential)) return false;

    UserCredential that = (UserCredential) o;

    return new EqualsBuilder().append(id, that.id).append(username, that.username).append(password, that.password).isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(id).append(username).append(password).toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).append("id", id).append("username", username).toString();
  }
}
