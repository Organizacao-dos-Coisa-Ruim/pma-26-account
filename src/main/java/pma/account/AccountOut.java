package pma.account;

import lombok.Builder;

@Builder
public record AccountOut(

    String id,
    String name,
    String email,
    /** Papel da conta: "user" ou "admin". O Auth coloca esse valor no JWT. */
    String role

) {

  public static final String ROLE_USER = "user";
  public static final String ROLE_ADMIN = "admin";

}
