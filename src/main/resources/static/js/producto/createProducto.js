$(document).ready(function() {
	$("#divPrecioCompra").removeClass('d-none');
 	$("#divPrecioCompraIVA").addClass('d-none');
	document.getElementById('precioCompra').disabled = false;
	document.getElementById('precioCompraIVA').disabled = true;
//jquery validation toma el atributo name, con thymeleaf en los select se toma th:field	
$.validator.addMethod("validaSelect", function(value, element, arg){
  return arg != value;
 }, "Seleccione una opción");
 
jQuery.validator.addMethod("validaPrecio", function(value, element,arg) {
  console.log("regex:"+(/(^[0-9]{1,5}$|^[0-9]{1,2}\.[0-9]{1,2}$)/.test( value )));
  return this.optional( element ) || /(^[0-9]{1,5}$|^[0-9]{1,2}\.[0-9]{1,2}$)/.test( value );
}, 'Precio Inválido.');

	$("#createProducto").validate({
		rules: {
			nombre: "required",
			descripcion: "required",
			idTipoProducto: {validaSelect:"0"},
			idMarca: {validaSelect:"0"},
			idProveedor:{validaSelect:"0"},
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
			precioVenta:{
				required:true,
				validaPrecio:true,
			},
		},
		messages: {
			nombre: "Campo obligatorio",
			descripcion: "Campo obligatorio",
			idTipoProducto: "Escoge una opción",
			idMarca: "Escoge una opción",
			idProveedor: "Escoge una opción",
			precioCompra: {
				required:"Campo obligatorio",
				validaPrecio:"Precio Inválido"
			},
			precioCompraIVA: {
				required:"Campo obligatorio",
				validaPrecio:"Precio Inválido"
			},
			precioVenta: {
				required:"Campo obligatorio",
				validaPrecio:"Precio Inválido"
			},
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
	
//	$.validator.addMethod("selectcheck", function (value,element) {
//	console.log("value:" + value);
//	console.log("element:" + element);
//        return (value != '0');
//    }, "year required");

});

//To show it: $("#myId").removeClass('d-none');
//To hide it: $("#myId").addClass('d-none');
//To toggle it: $("#myId").toggleClass('d-none');

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