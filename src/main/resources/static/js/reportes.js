$(document).ready(function(){

    console.log($('#vehiculos_cliente_select').has('option').length)
    if( $('#vehiculos_cliente_select').has('option').length > 0 ) {
        var cc = $('#vehiculos_cliente_select').val();
        cargarVehiculosCliente(cc);
    }


    $('#vehiculos_cliente_select').on('change', function() {
        cargarVehiculosCliente(this.value);
    });

    function cargarVehiculosCliente(clienteCC) {
        $.ajax({
            type : "GET",
            contentType : "application/json",
            url : "http://" + $(location).attr('host') + "/reportes/vehiculos_cliente/" + clienteCC,
            dataType : 'json',
            success : function(result) {
                var resulthtml = '';
                result.forEach(function(elem) {
                    resulthtml +=
                        "<tr>" +
                            "<td>" + elem.vehiculo.placa + "</td>" +
                            "<td>" + elem.vehiculo.modelo + "</td>" +
                            "<td>" + elem.vehiculo.marca + "</td>" +
                            "<td>" + elem.vehiculo.anno + "</td>" +
                            "<td>" + elem.vehiculo.serie_carroceria + "</td>" +
                            "<td>" + elem.vehiculo.vr_comercial + "</td>" +
                            "<td>" + elem.vehiculo.clienteCC + "</td>" +
                            "<td>" + (elem.soat ? elem.soat : 0) + "</td>" +
                            "<td>" + (elem.todoRiesgo ? elem.todoRiesgo : 0) + "</td>" +
                        "</tr>";
                })
                $("table#vehiculos_cliente_table tbody tr").remove()
                $("table#vehiculos_cliente_table tbody").append(resulthtml);
            },
            error : function(xhr, ajaxOptions, thrownError) {
                console.log("ERROR: ", xhr.responseText);
            }
        });
    }

});