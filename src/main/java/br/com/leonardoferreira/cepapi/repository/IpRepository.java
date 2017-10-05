package br.com.leonardoferreira.cepapi.repository;

import br.com.leonardoferreira.cepapi.domain.Ip;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author leonardo.ferreira
 * @version $Revision: $<br/>
 *          $Id: $
 * @since 10/5/17 6:07 PM
 */
@Repository
public interface IpRepository extends CrudRepository<Ip, String> {
}
