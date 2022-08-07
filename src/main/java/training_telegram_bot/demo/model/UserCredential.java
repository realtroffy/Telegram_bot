package training_telegram_bot.demo.model;

import lombok.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserCredential {

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

    if (o == null || getClass() != o.getClass()) return false;

    UserCredential that = (UserCredential) o;

    return new EqualsBuilder().append(username, that.username).isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(username).toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).append("id", id).append("username", username).toString();
  }
}
