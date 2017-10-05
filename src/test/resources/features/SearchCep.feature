# language: pt

Funcionalidade: busca de cep

  Delineacao do Cenario: que seja realizada a busca de cep
    Quando for solicitada a busca pelo cep "<cep>"
    Entao a resposta deve contar os seguintes "<cep>", "<logradouro>", "<bairro>", "<cidade>" e "<estado>"
    Exemplos:
      | cep       | logradouro                              | bairro                  | cidade              | estado |
      | 01330-000 | Rua Rocha                               | Bela Vista              | São Paulo           | SP     |
      | 85100-000 |                                         |                         | Jordão (Guarapuava) | PR     |
      | 75064-590 | Rua A                                   | Vila Jaiara             | Anápolis            | GO     |
      | 12245-230 | Avenida Tivoli                          | Vila Betânia            | São José dos Campos | SP     |
      | 69908-768 | Rodovia BR-364 (Rio Branco-Porto Velho) | Loteamento Santa Helena | Rio Branco          | AC     |
      | 14801-210 | Avenida São Geraldo                     | Centro                  | Araraquara          | SP     |
      | 14802-510 | Rua Domingos Barbieri                   | Vila Harmonia           | Araraquara          | SP     |
      | 13580-000 |                                         |                         | Ribeirão Bonito     | SP     |
      | 14806-143 | Avenida Doutor Carlos Chagas            | Jardim Adalgisa         | Araraquara          | SP     |
      | 29102-933 | Rua Sete de Junho, 150 Edifício Ubatuba | Coqueiral de Itaparica  | Vila Velha          | ES     |

  Cenario: que seja realizada a busca de um cep inexistente
    Quando for solicitada a busca pelo cep "99999999"
    Entao a resposta deve dizer que o cep não foi encontrado