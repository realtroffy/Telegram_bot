package training_telegram_bot.demo.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserCredential{

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_cred_generator")
  @SequenceGenerator(name = "user_cred_generator", sequenceName = "user_cred_sequence", allocationSize = 1)
  @Column
  private Long id;

  @Column
  private String username;

  @Column
  private String password;
}
