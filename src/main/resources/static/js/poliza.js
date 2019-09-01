$(document).ready(function(){

    console.log($('select#cc_cliente').has('option').length);
    if( $('select#cc_cliente').has('option').length > 0 ) {
        var cc = $('select#cc_cliente').val();
        cargarPlacas(cc);
    }

    $('table#partes_table').hide()
    $('#poliza_tipo_select').on('change', function() {
        var container = $('table#partes_table')
        if (this.value == 1) {
            container.hide()
        } else if(this.value == 2) {
            container.show()
        }
    });

    $('select#cc_cliente').on('change', function() {
        cargarPlacas(this.value);
    });

    function cargarPlacas(clienteCC) {
        $.ajax({
            type : "GET",
            contentType : "application/json",
            url : "http://" + $(location).attr('host') + "/vehiculos/cliente/" + clienteCC,
            dataType : 'json',
            success : function(result) {
                var resulthtml = '';
                result.forEach(function(elem) {
                    resulthtml += "<option value='" + elem.placa+ "'>" + elem.placa + "</option>"
                })
                $("select#select_placa_poliza option").remove()
                $("select#select_placa_poliza").append(resulthtml);
            },
            error : function(xhr, ajaxOptions, thrownError) {
                console.log("ERROR: ", xhr.responseText);
            }
        });
    }

});