$(document).ready(function() {
	var btnBuscarProducto 	= document.getElementById("buscarProducto");
	var btnAgregarProducto 	= document.getElementById("agregarProducto");
	
	var txtCodigoProducto 	= document.getElementById("codigoProducto");
	var txtPrecioCompra 	= document.getElementById("precioCompra");
	var txtPrecioCompraIVA 	= document.getElementById("precioCompraIVA");
	var txtCantidad 		= document.getElementById("cantidad");
	var txtPrecioVenta 		= document.getElementById("precioVenta");
	var txtIdProducto 		= document.getElementById("idProducto");
	
	var lblNombreProducto 	= document.getElementById("labelNombreProducto");
	
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
	
	var tablaCompras = $('#table_compras').DataTable({
	  	 "columnDefs": [
	  		{ "targets": 0, "visible": false },
		    { "targets": 1, "orderable":false, "width": "18%" },
		    { "targets": 2, "orderable":false, "width": "30%" },
		    { "targets": 3, "orderable":false,"className": "text-right" },
		    { "targets": 4, "orderable":false,"className": "text-right" },
		    { "targets": 5, "orderable":false,"className": "text-right" },
		    { "targets": 6, "orderable":false,"className": "text-right" },
		    { "targets": 7, "orderable":false,"className": "text-center", "render": function ( data, type, row, meta ) { return '<button type="button" id="eliminarFila'+meta.row+'" class="btn btn-light" ><i class="fas fa-trash"></i></button>';} }
		    ],
		  "language": {
		        url: '../../DataTables/es-mx.json'
		    }
	});
	
	$('#table_compras tbody').on( 'click', 'button', function () {
		tablaCompras
        .row( $(this).parents('tr') )
        .remove()
        .draw();
    } );
	
	btnAgregarProducto.onclick = function agregarProducto(){
		var datosTabla 		= tablaCompras.rows().data();
		var existeProducto 	= false;
		var rowExistente 	= null;
		for(var numRow = 0; numRow<datosTabla.length;numRow++){
			if(datosTabla[numRow][0] === txtIdProducto.value ){
				console.log("Ya existe el producto");
				existeProducto = true;
				rowExistente = numRow;
			}
		}
		
		if(existeProducto){
			var row = tablaCompras.row( rowExistente ).data() ;
			row[6]	= parseInt(datosTabla[rowExistente][6]) + parseInt(txtCantidad.value);
			tablaCompras.row(rowExistente).data(row).draw();
		}else{
			tablaCompras.row.add([
				txtIdProducto.value,
				txtCodigoProducto.value,
				lblNombreProducto.textContent,
				txtPrecioCompra.value,
				txtPrecioCompraIVA.value,
				txtPrecioVenta.value,
				txtCantidad.value
				]).draw( false );
		}
		
	}
	
	btnBuscarProducto.onclick = function buscarProducto(){
		$.ajax({
			type: 'GET',
			url: 'buscar/producto',
	        data: {
	        	codigoProducto: document.getElementById('codigoProducto').value
	        },
	        success: function(producto) {
	        	if(producto.id != null){
	        		document.getElementById("idProducto").value = producto.id;
	        		document.getElementById("labelNombreProducto").innerHTML = producto.nombre;
	        		document.getElementById("precioCompra").value = producto.precioCompra;
	        		document.getElementById("precioCompraIVA").value = producto.precioCompraIVA;
	        		document.getElementById("precioVenta").value = producto.precioVenta;
	        	}else{
	        		document.getElementById("labelNombreProducto").innerHTML = "No existe producto";
	        	}
	        },
	        error: function (jqXHR) {
	        	$(document.body).text('Error: ' + jqXHR.status);
	        }
	    });
	};
	
	
	
	
})