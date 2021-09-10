$(document).ready(function() {
	var btnBuscarProducto 	= document.getElementById("buscarProducto");
	var btnAgregarProducto 	= document.getElementById("agregarProducto");
	var btnSaveInventario	= document.getElementById("saveInventario");
	
	var txtCodigoProducto 	= document.getElementById("codigoProducto");
	var txtPrecioCompra 	= document.getElementById("precioCompra");
	var txtPrecioCompraIVA 	= document.getElementById("precioCompraIVA");
	var txtCantidad 		= document.getElementById("cantidad");
	var txtPrecioVenta 		= document.getElementById("precioVenta");
	var txtIdProducto 		= document.getElementById("idProducto");
	var txtNumeroDocumento 	= document.getElementById("numeroDocumento");
	
	var selProveedor		= document.getElementById("selectProveedor");
	var selTipoDocumento	= document.getElementById("selectTipoDocumento");
	var selAlmacen		= document.getElementById("selectAlmacen");
	
	var dateFechaCompra 	= document.getElementById("datepicker");
	
	var nombreProducto 	= document.getElementById("nombreProducto");
	
	$("#divPrecioCompra").removeClass('d-none');
 	$("#divPrecioCompraIVA").addClass('d-none');
	
	
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
    
    jQuery.validator.addMethod("validaPrecio", function(value, element,arg) {
  return this.optional( element ) || /(^[0-9]{1,5}$|^[0-9]{1,2}\.[0-9]{1,2}$)/.test( value );
}, 'Precio Inválido.');

 jQuery.validator.addMethod("existeProducto", function(value, element,arg) {
 return  nombreProducto.value != "No existe producto";
}, 'No existe producto.');
    
    
 				$("#addProductoForm").validate({
		rules: {
			nombreProducto:{
				required:function(element) {
          			return nombreProducto.value == "";
        		},
        		existeProducto:true
			} ,
			precioCompra:{ 
				required: function(element) {
          			return !($("#precioCompraConIVA").is(":checked"));
        		},
				validaPrecio:true,
			},
			precioCompraIVA:{ 
				required: function(element) {
          			return ($("#precioCompraConIVA").is(":checked"));
        		},
				validaPrecio:true,
			},
			cantidad:{
				required:true,
				digits: true
			}
		},
		messages: {
			nombreProducto:{
				required:"Busque un producto",
				existeProducto:"No existe el producto"
			},
			precioCompra: {
				required:"Campo obligatorio",
				validaPrecio:"Precio Inválido"
			},
			precioCompraIVA: {
				required:"Campo obligatorio",
				validaPrecio:"Precio Inválido"
			},
			cantidad:{
				required:"Campo obligatorio",
				digits: "Ingrese una cantidad correcta"
			}
		},
		errorElement: "em",
				errorPlacement: function ( error, element ) {
					// Add the `invalid-feedback` class to the error element
					error.addClass( "invalid-feedback" );

					if ( element.prop( "type" ) === "checkbox" ) {
						error.insertAfter( element.next( "label" ) );
					} else {
						error.insertAfter( element );
					}
				},
				highlight: function ( element, errorClass, validClass ) {
					console.log("element: " + element);
					console.log("errorClass: " + errorClass);
					console.log("validClass: " + validClass);
					$( element ).addClass( "is-invalid" ).removeClass( "is-valid" );
				},
				unhighlight: function (element, errorClass, validClass) {
					console.log("element: " + element);
					console.log("errorClass: " + errorClass);
					console.log("validClass: " + validClass);
					$( element ).addClass( "is-valid" ).removeClass( "is-invalid" );
				}
		});


		
		
		
    
	
	btnAgregarProducto.onclick = function agregarProducto(){
		var datosTabla 		= tablaCompras.rows().data();
		var existeProducto 	= false;
		var rowExistente 	= null;
		
		if($("#addProductoForm").valid()){
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
					nombreProducto.value,
					txtPrecioCompra.value,
					txtPrecioCompraIVA.value,
					txtPrecioVenta.value,
					txtCantidad.value
					]).draw( false );
			}
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
	        		//document.getElementById("labelNombreProducto").innerHTML = producto.nombre;
	        		document.getElementById("nombreProducto").value = producto.nombre;
	        		document.getElementById("precioCompra").value = producto.precioCompra;
	        		document.getElementById("precioCompraIVA").value = producto.precioCompraIVA;
	        		document.getElementById("precioVenta").value = producto.precioVenta;
	        	}else{
	        		//document.getElementById("labelNombreProducto").innerHTML = "No existe producto";
	        		document.getElementById("nombreProducto").value = "No existe producto";
	        	}
	        },
	        error: function (jqXHR) {
	        	$(document.body).text('Error: ' + jqXHR.status);
	        }
	    });
	};
	
	btnSaveInventario.onclick = function saveInventario(){
		var productos = new Array();
		var data = tablaCompras.rows().data();
		
		for (let i = 0; i < data.length; i++) {
  			var producto = {
				"id": 					data[i][0],
				"codigoBarrasTienda": 	data[i][1],
				"descripcion": 			data[i][2],
				"precioCompra": 		data[i][3],
				"precioCompraIVA": 		data[i][4],
				"precioVenta": 			data[i][5],
				"cantidad": 			data[i][6]
			};
			productos.push(producto);
		}
		
		var compra = {
			"idProveedor": 		selProveedor.value,
			"numeroDocumento": 	txtNumeroDocumento.value,
			"idTipoDocumento":	selTipoDocumento.value,
			"fechaCompra":		dateFechaCompra.value,
			"idAlmacen":		selAlmacen.value,
			"productos": 		productos,
		}
		
		$.ajax({
			type: 'POST',
			url: 'save/compras',
	        data: JSON.stringify(compra),
	        dataType: 'json',
	        contentType: 'application/json',
	        success: function(producto) {
	        },
	        error: function (jqXHR) {
	        	$(document.body).text('Error: ' + jqXHR.status);
	        }
	    });
	}
});

function changeStatusPrecioCompraConIVA(){
		 if (document.getElementById('precioCompraConIVA').checked) { 
			 document.getElementById('precioCompra').disabled = true;
			 document.getElementById('precioCompra').value = "";
			 document.getElementById('precioCompraIVA').disabled = false;
 			 $("#divPrecioCompra").addClass('d-none');
 			 $("#divPrecioCompraIVA").removeClass('d-none');
		 } 
		 else {
			 document.getElementById('precioCompra').disabled = false;
			 document.getElementById('precioCompraIVA').disabled = true;
			 document.getElementById('precioCompraIVA').value = "";
 			 $("#divPrecioCompra").removeClass('d-none');
 			  $("#divPrecioCompraIVA").addClass('d-none');
		 }
}