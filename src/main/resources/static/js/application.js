$(document).ready(function() {
  $(".btn-consultar").on('click', () => {
    var infoModal = $('#modalData');

    $.ajax({
      type: "GET",
      url: '/' + $('#consulta').val(),
      dataType: 'JSON',
      success: function(data){
        if (data.naoEncontrado == true)
          var htmlData = '<ul><li>CEP: ' + data.cep + '</li><li>Não encontrado: ' + data.naoEncontrado + '</li> </ul>'
        else
          var htmlData = '<ul><li>Cidade: '+data.cidade+'</li><li>Endereço: '+data.logradouro+'</li><li>Bairro: '+data.bairro+'</li><li>Bairro Normalizado: '+data.bairroNormalizado+'</li><li>Cep: '+data.cep+'</li><li>Tipo Endereço: '+data.tipoLogradouro+'</li><li>Tipo Endereço Normalizado: '+data.tipoLogradouroNormalizado+'</li><li>Cidade Normalizada: '+data.cidadeNormalizada+'</li><li>Endereço Normalizado: '+data.logradouroNormalizado+'</li><li>Estado: '+data.uf+'</li></ul>';

        infoModal.find('code').html(htmlData);
        $('#modalData').modal({show:true});
      }
    });
    return false;
  });

  $('#consulta').mask('99999-999');
});
