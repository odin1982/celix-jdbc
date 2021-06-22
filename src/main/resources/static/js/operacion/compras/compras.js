$(document).ready(function() {
	var btnBuscarProducto = document.getElementById("buscarProducto");
	$.datepicker.regional['es'] = {
			 closeText: 'Cerrar',
			 prevText: '< Ant',
			 nextText: 'Sig >',
			 currentText: 'Hoy',
			 monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
			 monthNamesShort: ['Ene','Feb','Mar','Abr', 'May','Jun','Jul','Ago','Sep', 'Oct','Nov','Dic'],
			 dayNames: ['Domingo', 'Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado'],
			 dayNamesShort: ['Dom','Lun','Mar','Mié','Juv','Vie','Sáb'],
			 dayNamesMin: ['Do','Lu','Ma','Mi','Ju','Vi','Sá'],
			 weekHeader: 'Sm',
			 dateFormat: 'dd/mm/yy',
			 firstDay: 1,
			 isRTL: false,
			 showMonthAfterYear: false,
			 yearSuffix: ''
			 };
			 $.datepicker.setDefaults($.datepicker.regional['es']);
	 
	$( "#datepicker" ).datepicker();
	
	$('#table_compras').DataTable({
	  	 "columnDefs": [
		    { "targets": 0, "orderable":false },
		    { "targets": 1, "orderable":false },
		    { "targets": 2, "orderable":false },
		    { "targets": 3, "orderable":false },
		    { "targets": 4, "orderable":false },
		    { "targets": 5, "orderable":false },
		    { "targets": 6, "orderable":false },
		    { "targets": 7, "orderable":false } 
		  ],
		  "language": {
		        url: '../../DataTables/es-mx.json'
		    }
	});
	
	btnBuscarProducto.onclick = function buscarProducto(){
		$.ajax({
			type: 'GET',
			url: 'buscar/producto',
	        data: {
	        	codigoProducto: document.getElementById('codigoProducto').value
	        },
	        success: function(producto) {
	        	if(producto.id != null){
	        		document.getElementById("labelNombreProducto").innerHTML = producto.nombre;
	        		document.getElementById("precioCompra").value = producto.precioCompra;
	        		document.getElementById("precioCompraIVA").value = producto.precioCompraIVA;
	        	}else{
	        		document.getElementById("labelNombreProducto").innerHTML = "No existe producto";
	        	}
	        },
	        error: function (jqXHR) {
	        	$(document.body).text('Error: ' + jqXHR.status);
	        }
	    });
	}
	
	
})