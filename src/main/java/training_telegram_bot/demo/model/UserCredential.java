package training_telegram_bot.demo.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "USER_CREDENTIAL")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserCredential{

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_credential_id_generator")
  @SequenceGenerator(name = "user_credential_id_generator", sequenceName = "user_cred_id_seq", allocationSize = 1)
  @Column(name = "ID")
  private Long id;

  @Column(name = "USERNAME", length = 1024)
  private String username;

  @Column(name = "PASSSWORD", length = 1024)
  private String password;
}
