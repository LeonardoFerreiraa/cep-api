package br.com.leonardoferreira.cepapi.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author leonardo.ferreira
 * @version $Revision: $<br/>
 *          $Id: $
 * @since 10/5/17 6:05 PM
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Ip {

    @Id
    private String ip;

    private Long quantity;

}
