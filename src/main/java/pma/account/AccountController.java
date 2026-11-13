package pma.account;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Contrato do serviço Account.
 *
 * - O account-service implementa esta interface (AccountResource).
 * - Os outros serviços (ex.: Auth) usam esta mesma interface como cliente Feign.
 */
@FeignClient(name = "account", url = "${pma.account.url:http://account:8080}")
public interface AccountController {

  /** Cria uma conta. 201 com a conta criada; 409 se o email já existe; 400 se faltar campo. */
  @PostMapping("/accounts")
  public ResponseEntity<AccountOut> create(
      @RequestBody AccountIn in);

  @GetMapping("/accounts")
  public ResponseEntity<List<AccountOut>> findAll();

  /** 200 com a conta; 404 se não existe. */
  @GetMapping("/accounts/{id}")
  public ResponseEntity<AccountOut> findById(
      @PathVariable("id") String id);

  /** 204; 404 se não existe. */
  @DeleteMapping("/accounts/{id}")
  public ResponseEntity<Void> delete(
      @PathVariable("id") String id);

  /**
   * Verifica credenciais (usado pelo Auth no login).
   * 200 com a conta se email e senha conferem; 401 caso contrário.
   */
  @PostMapping("/accounts/login")
  public ResponseEntity<AccountOut> login(
      @RequestBody LoginIn in);

  @GetMapping("/accounts/health-check")
  public ResponseEntity<Void> healthCheck();

}
