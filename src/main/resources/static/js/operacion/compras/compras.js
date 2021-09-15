$(document).ready(function() {
	var formAddProducto			= document.getElementById("addProductoForm");
	
	var btnBuscarProducto 		= document.getElementById("buscarProducto");
	var btnAgregarProducto 		= document.getElementById("agregarProducto");
	var btnSaveInventario		= document.getElementById("saveInventario");
	
	var txtCodigoProducto 		= document.getElementById("codigoProducto");
	var txtPrecioCompra 		= document.getElementById("precioCompra");
	var txtPrecioCompraIVA 		= document.getElementById("precioCompraIVA");
	var txtCantidad 			= document.getElementById("cantidad");
	var txtPrecioVenta 			= document.getElementById("precioVenta");
	var txtIdProducto 			= document.getElementById("idProducto");
	var txtNumeroDocumento 		= document.getElementById("numeroDocumento");
	
	var selProveedor			= document.getElementById("selectProveedor");
	var selTipoDocumento		= document.getElementById("selectTipoDocumento");
	var selAlmacen				= document.getElementById("selectAlmacen");
	
	var dateFechaCompra 		= document.getElementById("datepicker");
	
	var nombreProducto 			= document.getElementById("nombreProducto");
	
	var chkFijarCantidad		= document.getElementById("chkFijarCantidad");
	var chkPrecioCompraConIVA	= document.getElementById("precioCompraConIVA");
	
	var hiddenTxtPreCantidad	= document.getElementById("hiddenTxtPreCantidad");
	
	$("#divPrecioCompra").removeClass('d-none');
 	$("#divPrecioCompraIVA").addClass('d-none');
	
	
	$.datepicker.regional['es'] = {
			 closeText: 		'Cerrar',
			 prevText: 			'< Ant',
			 nextText: 			'Sig >',
			 currentText: 		'Hoy',
			 monthNames: 		['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
			 monthNamesShort: 	['Ene','Feb','Mar','Abr', 'May','Jun','Jul','Ago','Sep', 'Oct','Nov','Dic'],
			 dayNames: 			['Domingo', 'Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado'],
			 dayNamesShort: 	['Dom','Lun','Mar','Mié','Juv','Vie','Sáb'],
			 dayNamesMin: 		['Do','Lu','Ma','Mi','Ju','Vi','Sá'],
			 weekHeader: 		'Sm',
			 dateFormat: 		'dd/mm/yy',
			 firstDay: 			1,
			 isRTL: 			false,
			 showMonthAfterYear: false,
			 yearSuffix: 		'',
			 maxDate:			"+0m +0w"
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
	
	jQuery.validator.addMethod("validaSelect", function(value, element, arg){
  		return arg != value;
 	}, "Seleccione una opción");
 	
 	jQuery.validator.addMethod("validaFecha", function(value, element,arg) {
  		return this.optional( element ) || /^(?=\d)(?:(?:31(?!.(?:0?[2469]|11))|(?:30|29)(?!.0?2)|29(?=.0?2.(?:(?:(?:1[6-9]|[2-9]\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00)))(?:\x20|$))|(?:2[0-8]|1\d|0?[1-9]))([-.\/])(?:1[012]|0?[1-9])\1(?:1[6-9]|[2-9]\d)?\d\d(?:(?=\x20\d)\x20|$))?(((0?[1-9]|1[012])(:[0-5]\d){0,2}(\x20[AP]M))|([01]\d|2[0-3])(:[0-5]\d){1,2})?$/.test( value );
	}, 'Fecha Inválida.');
	
	 jQuery.validator.addMethod("mayorCeroDecimales", function(value, element,arg) {
  		return value >0;
	}, 'Precio Inválido.');
    
    
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
        		mayorCeroDecimales:true,
			},
			precioCompraIVA:{ 
				required: function(element) {
          			return ($("#precioCompraConIVA").is(":checked"));
        		},
				validaPrecio:true,
				mayorCeroDecimales:true,
			},
			cantidad:{
				required:true,
				digits: true,
				min:1
			}
		},
		messages: {
			nombreProducto:{
				required:"Busque un producto",
				existeProducto:"No existe el producto"
			},
			precioCompra: {
				required:"Campo obligatorio",
				validaPrecio:"Precio Inválido",
				mayorCeroDecimales:"El precio debe ser mayor a cero"
			},
			precioCompraIVA: {
				required:"Campo obligatorio",
				validaPrecio:"Precio Inválido",
				mayorCeroDecimales:"El precio debe ser mayor a cero"
			},
			cantidad:{
				required:"Campo obligatorio",
				digits:  "Ingrese una cantidad correcta",
				min:	 "La cantidad debe ser mayor a cero"	
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
					$( element ).addClass( "is-invalid" ).removeClass( "is-valid" );
				},
				unhighlight: function (element, errorClass, validClass) {
					$( element ).addClass( "is-valid" ).removeClass( "is-invalid" );
				}
		});
		
		$("#formInventario").validate({
			  ignore: "",
		rules: {
			idProveedor:		{ validaSelect:"0" },
			numeroDocumento:	{ required:true },
			idTipoDocumento:	{ validaSelect:"0" },
			datepicker:			{ 
									required:true,
									validaFecha:true 
								},
			idAlmacen:			{ validaSelect:"0" },
			table_required:		{
									required: function(element) {
										console.log("any"+(!$('#table_compras').DataTable().data().any()));
          								 return (!$('#table_compras').DataTable().data().any());
          							}
          						} 
		},
		messages: {
			idProveedor:		"Escoge una opción",
			numeroDocumento: 	"Campo obligatorio",
			idTipoDocumento:	"Escoge una opción",
			datepicker:			{
									required: "Campo obligatorio",
									validaFecha: "Fecha Invalida"
								},
			idProveedor:		"Escoge una opción",
			table_required:		"Debes agregar un producto a tu nota o factura"
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
					$( element ).addClass( "is-invalid" ).removeClass( "is-valid" );
				},
				unhighlight: function (element, errorClass, validClass) {
					$( element ).addClass( "is-valid" ).removeClass( "is-invalid" );
				}
		});
	
	btnAgregarProducto.onclick = function agregarProducto(){
		var datosTabla 		= tablaCompras.rows().data();
		var existeProducto 	= false;
		var rowExistente 	= null;
		if( txtPrecioCompra.value == undefined || txtPrecioCompra.value.trim() == "" || txtPrecioCompra.value == null  ){
			txtPrecioCompra.value = (txtPrecioCompraIVA.value / 1.16).toFixed(2);
		}
		
		if( txtPrecioCompraIVA.value == undefined || txtPrecioCompraIVA.value.trim() == "" || txtPrecioCompraIVA.value == null  ){
			txtPrecioCompraIVA.value = (txtPrecioCompra.value * 1.16).toFixed(2);
		}
		
		
		if( $("#addProductoForm").valid() ){
			
			for(var numRow = 0; numRow<datosTabla.length;numRow++){
				if(datosTabla[numRow][0] === txtIdProducto.value){
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
			
			resetAddProductoForm();
			txtPrecioCompra.disabled = false;
			txtPrecioCompraIVA.disabled = true;
 			$("#divPrecioCompra").removeClass('d-none');
 			$("#divPrecioCompraIVA").addClass('d-none');
 			txtPrecioCompra.value = "";
			
		}

		
	}
	
	txtCodigoProducto.onkeyup = function buscarProducto(event){
		if (event.keyCode === 13) {
					$.ajax({
			type: 'GET',
			url: 'buscar/producto',
	        data: {
	        	codigoProducto: document.getElementById('codigoProducto').value.trim()
	        },
	        success: function(producto) {
	        	if(producto.id != null){
	        		document.getElementById("idProducto").value = producto.id;
	        		//document.getElementById("labelNombreProducto").innerHTML = producto.nombre;
	        		document.getElementById("nombreProducto").value = producto.nombre;
	        		document.getElementById("precioCompra").value = producto.precioCompra;
	        		document.getElementById("precioCompraIVA").value = producto.precioCompraIVA;
	        		document.getElementById("precioVenta").value = producto.precioVenta;
	        		if(chkFijarCantidad.checked && (txtCantidad.value.trim() != "")){
						btnAgregarProducto.focus();
					}else{
						txtCantidad.focus();
					}
	        	}else{
	        		//document.getElementById("labelNombreProducto").innerHTML = "No existe producto";
	        		document.getElementById("nombreProducto").value = "No existe producto";
	        	}
	        },
	        error: function (jqXHR) {
	        	$(document.body).text('Error: ' + jqXHR.status);
	        }
	    });			
		}

	}; 
	
	//guarda productos de la nota
	btnSaveInventario.onclick = function saveInventario(){
		var productos = new Array();
		var data = tablaCompras.rows().data();

		console.log("formInventario valid: "+$("#formInventario").valid()); 
		if($("#formInventario").valid()){
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
		
		}else{
			
		}
		
	}
	
	txtPrecioCompra.onchange = function changePrecioCompraIVA(){
		console.log("txtPrecioCompra: " + txtPrecioCompra.value);
		txtPrecioCompraIVA.value = (txtPrecioCompra.value * 1.16).toFixed(2) > 0 ? (txtPrecioCompra.value * 1.16).toFixed(2) : "";
	}
	
	txtPrecioCompraIVA.onchange = function changePrecioCompra(){
		console.log("txtPrecioCompra: " + txtPrecioCompraIVA.value);
		txtPrecioCompra.value = (txtPrecioCompraIVA.value / 1.16).toFixed(2) > 0 ? (txtPrecioCompraIVA.value / 1.16).toFixed(2) : "";
	}
	
	txtCantidad.onchange = function(){
		var validator = $( "#addProductoForm" ).validate();
		if(validator.element( "#cantidad" )){//valida si el elemento cantidad es valido
			if($("#chkFijarCantidad").is(':checked')){
				hiddenTxtPreCantidad.value = txtCantidad.value;
			}
		}else{
			hiddenTxtPreCantidad.value = 0;
		}
	}
	
	txtCantidad.onkeyup = function(event){
		if (event.keyCode === 13) {
		var validator = $( "#addProductoForm" ).validate();
		if(validator.element( "#cantidad" )){//valida si el elemento cantidad es valido
			if($("#chkFijarCantidad").is(':checked')){
				hiddenTxtPreCantidad.value = txtCantidad.value;
			}
			btnAgregarProducto.focus();
		}else{
			hiddenTxtPreCantidad.value = 0;
		}
		}
	}
	
	chkFijarCantidad.onchange = function changePreFijarCantidad(){
		var validator = $( "#addProductoForm" ).validate();
		if(validator.element( "#cantidad" )){//valida si el elemento cantidad es valido
			if($("#chkFijarCantidad").is(':checked')){
				hiddenTxtPreCantidad.value = txtCantidad.value;
			}
		}else{
			hiddenTxtPreCantidad.value = 0;
		}
	}
	
	chkPrecioCompraConIVA.onchange = function(){
		if(document.getElementById("precioCompra").value == undefined || document.getElementById("precioCompra").value.trim() == "" || document.getElementById("precioCompra").value == null  ){
			document.getElementById("precioCompra").value = "";
		}else{
			document.getElementById("precioCompra").value = (document.getElementById("precioCompraIVA").value / 1.16).toFixed(2) > 0 ? (document.getElementById("precioCompraIVA").value / 1.16).toFixed(2) : "";
		}
		
		if(document.getElementById("precioCompraIVA").value == undefined || document.getElementById("precioCompraIVA").value.trim() == "" || document.getElementById("precioCompraIVA").value == null  ){
			document.getElementById("precioCompraIVA").value = "";
		}else{
			document.getElementById("precioCompraIVA").value = (document.getElementById("precioCompra").value * 1.16).toFixed(2) > 0 ? (document.getElementById("precioCompra").value * 1.16).toFixed(2) : "";
		}
	
		if (document.getElementById('precioCompraConIVA').checked) { 
			document.getElementById('precioCompra').disabled = true;
			document.getElementById('precioCompraIVA').disabled = false;
 			$("#divPrecioCompra").addClass('d-none');
 			$("#divPrecioCompraIVA").removeClass('d-none');
		}else{
			document.getElementById('precioCompra').disabled = false;
			document.getElementById('precioCompraIVA').disabled = true;
 			$("#divPrecioCompra").removeClass('d-none');
 			$("#divPrecioCompraIVA").addClass('d-none');
		 }
	}

function resetAddProductoForm(){
	txtCodigoProducto.value = "";
	nombreProducto.value = "";
	chkPrecioCompraConIVA.checked = false;
	txtPrecioCompra.value = "";
	if(chkFijarCantidad.checked){
		txtCantidad.value = hiddenTxtPreCantidad.value;
	}else{
		chkFijarCantidad.checked = false;
		txtCantidad.value = "";
	}
}

});